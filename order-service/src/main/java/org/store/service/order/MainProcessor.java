package org.store.service.order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.store.models.dtos.request.client.ClientCreate;
import org.store.models.dtos.request.order.SaveOrderRequest;
import org.store.service.user.SaveUserService;
import org.store.service.validate.OrderValidate;
import org.store.service.validate.UserValidate;

import static io.vertx.codegen.CodeGenProcessor.log;

@ApplicationScoped
public class MainProcessor {

    SaveUserService userService;
    SaveOrderService orderService;
    OrderValidate orderValidate;
    UserValidate userValidate;

    @Inject
    public MainProcessor(SaveOrderService orderService, OrderValidate orderValidate, UserValidate userValidate) {
        this.orderService = orderService;
        this.orderValidate = orderValidate;
        this.userValidate = userValidate;
    }


    public SaveOrderRequest createOrder(SaveOrderRequest saveOrderRequest) {
        log.info("создание заказа для пользователя " + saveOrderRequest.getCustomerName());
        orderValidate.validOrder(saveOrderRequest);
        new Thread(() -> {
            orderService.saveOrder(saveOrderRequest);
            //резерв заказа обновление статуса
            //отправка в печатный сервис
            //отправка на почту
        }
        ).start();
        return saveOrderRequest;
    }


    public void createUser(ClientCreate clientCreate) {
        log.info("создание пользователя " + clientCreate.getFullName());
        userValidate.validUser(clientCreate);
        userService.saveUser(clientCreate);
    }
}
