package org.store.models.dtos.request.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveOrderRequest {
    private String customerEmail;
    private String customerName;
    private List<ProductOrder> items;
    private String shippingAddress;
    private String phoneNumber;
    private String comment;
}
