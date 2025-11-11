package org.store.models.dtos.request.order;

import java.util.List;

public class SaveOrderRequest {
    private String customerEmail;
    private String customerName;
    private List<ProductOrder> items;
    private String shippingAddress;
    private String phoneNumber;
    private String comment;
}
