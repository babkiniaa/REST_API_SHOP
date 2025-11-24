package org.store.mappers;

import org.store.models.dtos.request.email.EmailInfoRequest;
import org.store.models.dtos.request.order.SaveOrderRequest;

public class EmailMapper {

    public EmailInfoRequest createEmailRequest(Long orderId, SaveOrderRequest saveOrderRequest) {
        EmailInfoRequest request = new EmailInfoRequest();
        request.setEmailAddress(saveOrderRequest.getCustomerEmail());
        request.setOrderId(orderId);

        if (!saveOrderRequest.getItems().isEmpty()) {
            request.setProductId(saveOrderRequest.getItems().get(0).getProductId());
        }

        return request;
    }
}
