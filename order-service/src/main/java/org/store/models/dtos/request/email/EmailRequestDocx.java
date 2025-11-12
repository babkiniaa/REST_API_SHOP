package org.store.models.dtos.request.email;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class EmailRequestDocx {
    private String orderId;
    private String customerName;
    private String customerEmail;
    private String documentType;
    private String templateName;
    private Map<String, Object> variables;
    private boolean sendEmail;
    private String language;
}
