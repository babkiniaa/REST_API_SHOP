package org.store.models.dtos.request.order;

import lombok.Getter;
import lombok.Setter;
import org.store.models.enums.OrderStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderStatusDto {
    private String orderId;
    private OrderStatus status;
    private String currentStep;
    private LocalDateTime createdAt;
    private LocalDateTime estimatedDelivery;
    private String trackingNumber;
}
