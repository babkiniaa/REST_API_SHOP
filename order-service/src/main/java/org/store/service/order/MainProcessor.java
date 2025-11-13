package org.store.service.order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.store.client.emailSendServiceClient;
import org.store.client.paymentServiceClient;
import org.store.client.printServiceClient;
import org.store.client.productServiceClient;
import org.store.mappers.EmailMapper;
import org.store.mappers.PrintServiceMapper;
import org.store.mappers.ReservedMapper;
import org.store.models.dtos.request.client.ClientCreate;
import org.store.models.dtos.request.email.EmailInfoRequest;
import org.store.models.dtos.request.email.EmailRequestDocx;
import org.store.models.dtos.request.order.ReservedOrderRequest;
import org.store.models.dtos.request.order.SaveOrderRequest;
import org.store.models.entities.OrderEntity;
import org.store.service.user.SaveUserService;
import org.store.service.validate.OrderValidate;
import org.store.service.validate.UserValidate;

import static io.vertx.codegen.CodeGenProcessor.log;

@ApplicationScoped
public class MainProcessor {

    SaveUserService userService;
    UserValidate userValidate;
    SaveOrderService orderService;
    OrderValidate orderValidate;
    ReservedMapper reservedMapper;
    PrintServiceMapper printServiceMapper;
    EmailMapper emailMapper;
    printServiceClient printServiceClient;
    emailSendServiceClient emailSendServiceClient;
    paymentServiceClient paymentServiceClient;
    productServiceClient productServiceClient;

    @Inject
    public MainProcessor(SaveUserService userService,
                         UserValidate userValidate,
                         SaveOrderService orderService,
                         OrderValidate orderValidate,
                         ReservedMapper reservedMapper,
                         PrintServiceMapper printServiceMapper,
                         EmailMapper emailMapper,
                         @RestClient org.store.client.printServiceClient printServiceClient,
                         @RestClient org.store.client.emailSendServiceClient emailSendServiceClient,
                         @RestClient org.store.client.paymentServiceClient paymentServiceClient,
                         @RestClient org.store.client.productServiceClient productServiceClient) {
        this.userService = userService;
        this.userValidate = userValidate;
        this.orderService = orderService;
        this.emailMapper = emailMapper;
        this.orderValidate = orderValidate;
        this.reservedMapper = reservedMapper;
        this.printServiceMapper = printServiceMapper;
        this.printServiceClient = printServiceClient;
        this.emailSendServiceClient = emailSendServiceClient;
        this.paymentServiceClient = paymentServiceClient;
        this.productServiceClient = productServiceClient;
    }


    public SaveOrderRequest createOrder(SaveOrderRequest saveOrderRequest) {
        log.info("создание заказа для пользователя " + saveOrderRequest.getCustomerName());
        orderValidate.validOrder(saveOrderRequest);
        new Thread(() -> {
            OrderEntity orderEntity = orderService.saveOrder(saveOrderRequest);
            ReservedOrderRequest reservedOrderRequest = reservedMapper.createReserveRequest(orderEntity.orderId, saveOrderRequest);
            productServiceClient.reservedProduct(reservedOrderRequest);
            EmailRequestDocx emailRequestDocx = printServiceMapper.createDocxRequest(orderEntity.orderId, saveOrderRequest);
            printServiceClient.sendGenerateDocx(emailRequestDocx);
            EmailInfoRequest emailInfoRequest = emailMapper.createEmailRequest(orderEntity.orderId, saveOrderRequest);
            emailSendServiceClient.sendInfo(emailInfoRequest);
        }
        ).start();
        return saveOrderRequest;
    }


    public ClientCreate createUser(ClientCreate clientCreate) throws RuntimeException {
        log.info("создание пользователя " + clientCreate.getFullName());
        userValidate.validUser(clientCreate);
        userService.saveUser(clientCreate);
        return clientCreate;
    }
}
