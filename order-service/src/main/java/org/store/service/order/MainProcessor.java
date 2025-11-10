package org.store.service.order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.store.models.dtos.request.SaveOrderRequest;
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

    }
}
