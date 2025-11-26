package org.store.service.validate;

import jakarta.enterprise.context.ApplicationScoped;
import org.store.models.dtos.request.order.SaveOrderRequest;

@ApplicationScoped
public class OrderValidate {

    public void validOrder(SaveOrderRequest saveOrderRequest) throws RuntimeException {
        if(saveOrderRequest.getCustomerName() == null || saveOrderRequest.getCustomerName()== null){
            throw new RuntimeException("Информация о пользователе пуста");
        }
        if(saveOrderRequest.getItems()== null || saveOrderRequest.getItems().isEmpty()){
            throw new RuntimeException("Список заказов пуст");
        }
        if(saveOrderRequest.getShippingAddress()== null){
            throw new RuntimeException("Отсутствует адресс доставки");
        }
        if(saveOrderRequest.getPhoneNumber()==null){
            throw new RuntimeException("Номер заказчика отсутствует");
        }
    }
}
