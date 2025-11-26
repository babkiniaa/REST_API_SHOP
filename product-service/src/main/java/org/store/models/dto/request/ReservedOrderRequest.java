package org.store.models.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.store.models.dto.ProductOrder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservedOrderRequest {
    private Long orderId;
    private String reservationId;
    private LocalDateTime reserveUntil;
    private List<ProductOrder> products;
    private Long customerId;

}
