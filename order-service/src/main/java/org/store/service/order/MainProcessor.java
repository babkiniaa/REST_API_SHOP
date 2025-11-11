package org.store.service.order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.store.models.dtos.request.client.ClientCreate;
import org.store.models.dtos.request.order.SaveOrderRequest;
import org.store.service.validate.OrderValidate;

@ApplicationScoped
public class MainProcessor {

    @Inject
    SaveOrderService service;
    OrderValidate orderValidate;

    public MainProcessor(SaveOrderService service, OrderValidate orderValidate) {
        this.service = service;
        this.orderValidate = orderValidate;
    }

    public void createOrder(SaveOrderRequest saveOrderRequest) {
        orderValidate.validOrder(saveOrderRequest);
        service.saveOrder(saveOrderRequest);
        //резерв заказа обновление статуса
        //отправка в печатный сервис
        //отправка на почту
        //Логика выкупа заказа
    }

    public void createUser(ClientCreate clientCreate) {

    }
}
