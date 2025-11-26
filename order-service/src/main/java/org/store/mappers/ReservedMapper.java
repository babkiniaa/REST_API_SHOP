package org.store.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.store.models.dtos.request.order.ReservedOrderRequest;
import org.store.models.dtos.request.order.SaveOrderRequest;
import org.store.models.entities.UserEntity;

import java.time.LocalDateTime;

@ApplicationScoped
public class ReservedMapper {
    public ReservedOrderRequest createReserveRequest(Long orderId, SaveOrderRequest saveOrderRequest) {
        ReservedOrderRequest request = new ReservedOrderRequest();
        request.setOrderId(orderId);
        request.setReserveUntil(LocalDateTime.now().plusHours(24));
        request.setProducts(saveOrderRequest.getItems());
        UserEntity userEntity = UserEntity.findByEmail(saveOrderRequest.getCustomerEmail());
        request.setCustomerId(userEntity.userId);

        return request;
    }
}
