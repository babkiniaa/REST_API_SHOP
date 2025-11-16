package org.store.models.dtos.request.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class OrderByPeriod {
    private String email;
    private LocalDate dateStart;
    private LocalDate dateEnd;
}
