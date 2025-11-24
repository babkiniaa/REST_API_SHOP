package org.store.validate;

import jakarta.enterprise.context.ApplicationScoped;
import org.store.models.dto.request.NotificationRequest;

@ApplicationScoped
public class Validator {

    public void validateNotificationRequest(NotificationRequest request) {
        if (request.getType() == null) {
            throw new RuntimeException("Тип уведомления не указан");
        }
        if (request.getOrderId() == null) {
            throw new RuntimeException("Не указаны данные заказа");
        }
        if(request.getEmailAddress() == null){
            throw new RuntimeException("Не указана почта для отправки");
        }

    }
}
