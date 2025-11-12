package org.store.models.dtos.request.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ReservedOrderRequest {
    private String orderId;
    private String reservationId;
    private LocalDateTime reserveUntil;
    private List<ProductOrder> products;
    private String customerId;

}
