package org.store.models.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.store.models.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResponseDto {
    private String orderId;
    private String customerEmail;
    private String customerName;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private Integer itemsCount;
    private Boolean documentsAvailable;
}
