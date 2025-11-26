package org.store.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.store.models.dtos.request.email.EmailRequestDocx;
import org.store.models.dtos.request.order.OrderPrdInfo;
import org.store.models.dtos.request.order.SaveOrderRequest;
import org.store.models.entities.OrderEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class PrintServiceMapper {

    public EmailRequestDocx createDocxRequest(Long orderId, SaveOrderRequest saveOrderRequest) {
        EmailRequestDocx request = new EmailRequestDocx();
        Map<String, Object> variables = new HashMap<>();

        request.setOrderId(orderId);
        request.setCustomerName(saveOrderRequest.getCustomerName());
        request.setCustomerEmail(saveOrderRequest.getCustomerEmail());
        request.setDocumentType("INVOICE");
        request.setTemplateName("счет.docx");
        request.setSendEmail(true);
        request.setLanguage("ru");

        variables.put("orderNumber", orderId);
        variables.put("customerName", saveOrderRequest.getCustomerName());
        variables.put("shippingAddress", saveOrderRequest.getShippingAddress());
        variables.put("orderDate", LocalDateTime.now());
        request.setVariables(variables);

        return request;
    }

    public EmailRequestDocx createDocxRequest(OrderEntity orderEntity, OrderPrdInfo orderPrdInfo){
        EmailRequestDocx request = new EmailRequestDocx();
        Map<String, Object> variables = new HashMap<>();


        request.setOrderId(orderPrdInfo.getOrderId());
        request.setCustomerName(orderEntity.customer.fullName);
        request.setCustomerEmail(orderEntity.customer.email);
        request.setDocumentType("INVOICE");
        request.setTemplateName("счет.docx");
        request.setSendEmail(true);
        request.setLanguage("ru");

        variables.put("orderNumber", orderPrdInfo.getOrderId());
        variables.put("customerName", orderPrdInfo.getCustomEmail());
        variables.put("shippingAddress", orderEntity.addressOrder);
        variables.put("orderDate", LocalDateTime.now());
        request.setVariables(variables);

        return request;
    }

}
