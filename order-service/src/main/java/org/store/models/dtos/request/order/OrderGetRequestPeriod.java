package org.store.models.dtos.request.order;

import org.store.models.enums.OrderStatus;

import java.time.LocalDate;

public class OrderGetRequestPeriod  {

    private String customerEmail;
    private LocalDate startDate;
    private LocalDate endDate;
    private OrderStatus status;
    private Integer page;
    private Integer size;
}
