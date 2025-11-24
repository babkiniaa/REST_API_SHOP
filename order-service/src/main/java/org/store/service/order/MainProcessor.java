package org.store.service.order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.store.client.emailSendServiceClient;
import org.store.client.printServiceClient;
import org.store.client.productServiceClient;
import org.store.mappers.EmailMapper;
import org.store.mappers.OrderMapper;
import org.store.mappers.PrintServiceMapper;
import org.store.mappers.ReservedMapper;
import org.store.models.dtos.request.client.ClientCreate;
import org.store.models.dtos.request.email.EmailInfoRequest;
import org.store.models.dtos.request.email.EmailRequestDocx;
import org.store.models.dtos.request.order.*;
import org.store.models.dtos.response.OrderResponseDto;
import org.store.models.entities.OrderEntity;
import org.store.service.user.SaveUserService;
import org.store.service.validate.OrderValidate;
import org.store.service.validate.UserValidate;

import java.util.List;

@ApplicationScoped
@Slf4j
public class MainProcessor {

    SaveUserService userService;
    UserValidate userValidate;
    SaveOrderService orderService;
    OrderValidate orderValidate;
    ReservedMapper reservedMapper;
    PrintServiceMapper printServiceMapper;
    EmailMapper emailMapper;
    OrderMapper orderMapper;
    printServiceClient printServiceClient;
    emailSendServiceClient emailSendServiceClient;
    productServiceClient productServiceClient;
    ManagedExecutor managedExecutor;

    @Inject
    public MainProcessor(SaveUserService userService,
                         UserValidate userValidate,
                         SaveOrderService orderService,
                         OrderValidate orderValidate,
                         ReservedMapper reservedMapper,
                         PrintServiceMapper printServiceMapper,
                         EmailMapper emailMapper,
                         OrderMapper orderMapper,
                         ManagedExecutor managedExecutor,
                         @RestClient org.store.client.printServiceClient printServiceClient,
                         @RestClient org.store.client.emailSendServiceClient emailSendServiceClient,
                         @RestClient org.store.client.productServiceClient productServiceClient) {
        this.userService = userService;
        this.userValidate = userValidate;
        this.orderService = orderService;
        this.emailMapper = emailMapper;
        this.orderValidate = orderValidate;
        this.reservedMapper = reservedMapper;
        this.printServiceMapper = printServiceMapper;
        this.orderMapper = orderMapper;
        this.printServiceClient = printServiceClient;
        this.managedExecutor = managedExecutor;
        this.emailSendServiceClient = emailSendServiceClient;
        this.productServiceClient = productServiceClient;
    }


    public SaveOrderRequest createOrder(SaveOrderRequest saveOrderRequest) {
        log.info("создание заказа для пользователя " + saveOrderRequest.getCustomerName());
        orderValidate.validOrder(saveOrderRequest);
        OrderEntity orderEntity = orderService.saveOrder(saveOrderRequest);
        managedExecutor.submit(() -> {
                    ReservedOrderRequest reservedOrderRequest = reservedMapper.createReserveRequest(orderEntity.orderId, saveOrderRequest);
                    productServiceClient.reservedProduct(reservedOrderRequest.getOrderId());
                    EmailRequestDocx emailRequestDocx = printServiceMapper.createDocxRequest(orderEntity.orderId, saveOrderRequest);
                    printServiceClient.sendGenerateDocx(emailRequestDocx);
                    EmailInfoRequest emailInfoRequest = emailMapper.createEmailRequest(orderEntity.orderId, saveOrderRequest);
                    emailSendServiceClient.sendInfo(emailInfoRequest);
                }
        );

        return saveOrderRequest;
    }

    public OrderPrdInfo getPdfForOrder(OrderPrdInfo orderPrdInfo) throws RuntimeException {
        OrderEntity order = OrderEntity.findById(orderPrdInfo.getOrderId());
        EmailRequestDocx emailRequestDocx;

        if (order == null) {
            throw new RuntimeException("Заказ для печати не найден");
        }
        emailRequestDocx = printServiceMapper.createDocxRequest(order, orderPrdInfo);
        printServiceClient.sendGenerateDocx(emailRequestDocx);

        return orderPrdInfo;
    }

    public OrderStatusDto getOrderInfo(Long orderId) {
        OrderStatusDto orderStatusDto;
        OrderEntity orderEntity = OrderEntity.findById(orderId);

        orderStatusDto = orderMapper.mapOrder(orderEntity);

        return orderStatusDto;
    }

    public ClientCreate createUser(ClientCreate clientCreate) throws RuntimeException {
        log.info("создание пользователя " + clientCreate.getFullName());
        userValidate.validUser(clientCreate);
        userService.saveUser(clientCreate);

        return clientCreate;
    }

    public List<OrderResponseDto> getListFromPeriod(OrderByPeriod orderByPeriod) {
        List<OrderEntity> orders = OrderEntity.find("dateCreate > ?1 and dateCreate < ?2 and USERID.EMAIL = ?3", orderByPeriod.getDateStart(), orderByPeriod.getDateEnd(), orderByPeriod.getEmail()).list();
        return orderMapper.mapListDto(orders);
    }
}
