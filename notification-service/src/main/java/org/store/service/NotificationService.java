package org.store.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.store.models.dto.request.NotificationRequest;

import java.util.concurrent.TimeUnit;

import static org.store.models.enums.TypeNotification.*;

@ApplicationScoped
@Slf4j
public class NotificationService {

    public String processNotification(NotificationRequest request) {
        log.info("Начало обработки уведомления");
        long startTime = System.currentTimeMillis();

        try {
            validateNotificationRequest(request);
            String template = prepareTemplate(request);
            String filledTemplate = fillTemplateVariables(template, request);

            log.info("Сохранение в историю");
            saveToHistory(request, filledTemplate);

            long totalTime = System.currentTimeMillis() - startTime;
            log.info("Уведомление обработано за {} мс", totalTime);

            return "SUCCESS: Уведомление отправлено через " + totalTime + "мс";

        } catch (Exception e) {
            log.error("Ошибка обработки уведомления: {}", e.getMessage());
            return "FAILED: " + e.getMessage();
        }
    }

    public String sendEmailNotification(String template, NotificationRequest request) {
        log.info("Начало отправки email...");

        try {

            sleep(500);
            log.info("SMTP соединение установлено");

            sleep(1200);
            log.info("Данные отправлены на SMTP сервер");

            sleep(800);
            log.info("Email успешно доставлен на {}", request.getRecipientEmail());

            return "EMAIL_SENT";

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Отправка email прервана");
        }
    }

    public String sendSmsNotification(String template, NotificationRequest request) {
        log.info("Начало отправки SMS...");

        try {
            sleep(300);
            log.info("Подключение к SMS шлюзу...");

            sleep(1500);
            log.info("SMS отправлено через API");

            sleep(400);
            log.info("SMS доставлено на {}", request.getPhoneNumber());

            return "SMS_SENT";

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Отправка SMS прервана");
        }
    }

    public String sendPushNotification(String template, NotificationRequest request) {
        log.info("Начало отправки Push-уведомления...");

        try {
            sleep(200);
            log.info("Подключение к push-сервису...");

            sleep(600);
            log.info("Push отправлен в сервис");

            sleep(300);
            log.info("Push уведомление отправлено");

            return "PUSH_SENT";

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Отправка Push прервана");
        }
    }

    public String sendMultiChannelNotification(String template, NotificationRequest request) {
        log.info("Многоканальная отправка уведомления...");

        try {
            String emailResult = sendEmailNotification(template, request);
            String smsResult = sendSmsNotification(template, request);
            String pushResult = sendPushNotification(template, request);

            log.info("Многоканальная отправка завершена: Email={}, SMS={}, Push={}",
                    emailResult, smsResult, pushResult);

            return "MULTI_CHANNEL_SENT";

        } catch (Exception e) {
            throw new RuntimeException("Ошибка многоканальной отправки: " + e.getMessage());
        }
    }

    private String prepareTemplate(NotificationRequest request) throws InterruptedException {
        sleep(800);
        return switch (request.getType()) {
            case EMAIL -> "email_template.html";
            case SMS -> "sms_template.txt";
            case PUSH -> "push_template.json";
            case MULTI_CHANNEL -> "multi_channel_template";
        };
    }

    private String fillTemplateVariables(String template, NotificationRequest request) throws InterruptedException {
        sleep(400);
        return template + "_filled_with_variables";
    }

    private void saveToHistory(NotificationRequest request, String result) throws InterruptedException {
        sleep(200);
        log.info("Сохранено в историю: {} - {}", request.getType(), result);
    }

    private void sleep(long millis) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(millis);
    }

}
