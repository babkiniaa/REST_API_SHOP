package org.store.mappers;

import org.store.models.dtos.request.email.EmailRequestDocx;
import org.store.models.dtos.request.order.SaveOrderRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PrintServiceMapper {

    public EmailRequestDocx createDocxRequest(Long orderId, SaveOrderRequest saveOrderRequest) {
        EmailRequestDocx request = new EmailRequestDocx();
        request.setOrderId(orderId);
        request.setCustomerName(saveOrderRequest.getCustomerName());
        request.setCustomerEmail(saveOrderRequest.getCustomerEmail());
        request.setDocumentType("INVOICE");
        request.setTemplateName("standard-invoice.docx");
        request.setSendEmail(true);
        request.setLanguage("ru");

        Map<String, Object> variables = new HashMap<>();
        variables.put("orderNumber", orderId);
        variables.put("customerName", saveOrderRequest.getCustomerName());
        variables.put("shippingAddress", saveOrderRequest.getShippingAddress());
        variables.put("orderDate", LocalDateTime.now());
        request.setVariables(variables);

        return request;
    }

}
