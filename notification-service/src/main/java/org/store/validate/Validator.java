package org.store.validate;

import org.store.models.dto.request.NotificationRequest;

public class Validator {

    public void validateNotificationRequest(NotificationRequest request) {
        if (request.getType() == null) {
            throw new RuntimeException("Тип уведомления не указан");
        }
        if (request.getOrderId() == null) {
            throw new RuntimeException("Не указаны данные заказа");
        }
        if(request.get)

    }
}
