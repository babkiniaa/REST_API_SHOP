package org.store.models.dtos.request;

import java.util.List;

public class SaveOrderRequest {
    private String customerEmail;
    private String customerName;
    private List<OrderItemDto> items;
    private String shippingAddress;
    private String phoneNumber;
    private String comment;
}
