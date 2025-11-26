package org.store.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.store.models.dtos.request.email.EmailInfoRequest;
import org.store.models.dtos.request.order.SaveOrderRequest;

@ApplicationScoped
public class EmailMapper {

    public EmailInfoRequest createEmailRequest(Long orderId, SaveOrderRequest saveOrderRequest) {
        EmailInfoRequest request = new EmailInfoRequest();
        request.setOrderId(orderId);

        return request;
    }
}
