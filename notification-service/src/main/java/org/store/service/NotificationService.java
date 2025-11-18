package org.store.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.store.models.dto.request.NotificationRequest;
import org.store.models.entity.OrderEntity;
import org.store.models.entity.UserEntity;
import org.store.validate.Validator;

@ApplicationScoped
@Slf4j
public class NotificationService {

    Validator validate;
    PreparationService preparationService;
    SendMessageService sendMessageService;

    @Inject
    public NotificationService(Validator validateNotificationRequest, PreparationService preparationService, SendMessageService sendMessageService) {
        this.validate = validateNotificationRequest;
        this.preparationService = preparationService;
        this.sendMessageService = sendMessageService;
    }


    public String processNotification(NotificationRequest request) {
        log.info("Начало обработки уведомления типа: {}", request.getType());
        long startTime = System.currentTimeMillis();

        try {
            validate.validateNotificationRequest(request);
            UserEntity userEntity = getUser(request);
            String template = preparationService.prepareTemplate(request);
            String filledTemplate = preparationService.fillTemplateVariables(template, request);

            String sendResult = sendNotificationByType(userEntity.phoneNumber, request, filledTemplate);

            log.info("Сохранение в историю");
            preparationService.saveToHistory(request, filledTemplate);

            long totalTime = System.currentTimeMillis() - startTime;
            log.info("Уведомление обработано за {} мс. Результат: {}", totalTime, sendResult);

            return "SUCCESS: " + sendResult + " через " + totalTime + "мс";

        } catch (Exception e) {
            log.error("Ошибка обработки уведомления: {}", e.getMessage());
            return "FAILED: " + e.getMessage();
        }
    }

    private String sendNotificationByType(String phoneUser, NotificationRequest request, String template) {
        return switch (request.getType()) {
            case EMAIL -> sendMessageService.sendEmailNotification(template, request);
            case SMS -> sendMessageService.sendSmsNotification(phoneUser, request);
            case PUSH -> sendMessageService.sendPushNotification(template, request);
            case MULTI_CHANNEL -> sendMessageService.sendMultiChannelNotification(template, request);
        };
    }

    private UserEntity getUser(NotificationRequest request) throws RuntimeException {
        OrderEntity order = OrderEntity.findById(request.getOrderId());
        if (order == null) {
            throw new RuntimeException("Заказ не найден");
        }
        return order.customer;
    }

}
