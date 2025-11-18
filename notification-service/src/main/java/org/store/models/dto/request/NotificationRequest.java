package org.store.models.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.store.models.enums.TypeNotification;

import java.lang.reflect.Type;

@Getter
@Setter
public class NotificationRequest {
    private String emailAddress;
    private TypeNotification type;
    private Long orderId;
}
