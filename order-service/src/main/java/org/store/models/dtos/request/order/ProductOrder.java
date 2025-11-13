package org.store.models.dtos.request.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductOrder {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;

}
