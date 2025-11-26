package org.store.models.dtos.request.payment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequest {
    private BigDecimal sumPay;
    private Long orderId;
}
