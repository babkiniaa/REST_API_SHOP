package org.store.models.dtos.request.email;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailInfoRequest {
    private String emailAddress;
    private Long productId;
    private Long orderId;
}
