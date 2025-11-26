package org.store.models.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductReservationRequest {
    private Long productId;
    private Integer quantity;
    private Long orderId;
}
