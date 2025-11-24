package org.store.models.dtos.request.email;

import lombok.Getter;
import lombok.Setter;
import org.store.models.dtos.request.order.ProductOrderEmail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EmailRequestCheck {
    private String orderId;
    private String customerName;
    private String customerEmail;
    private List<ProductOrderEmail> items;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private BigDecimal finalAmount;
    private String paymentMethod;
    private String currency;
    private LocalDateTime orderDate;

}
