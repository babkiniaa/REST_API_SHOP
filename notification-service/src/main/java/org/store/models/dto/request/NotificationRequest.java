package org.store.models.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
    private String emailAddress;
    private Long productId;
    private Long orderId;
}
