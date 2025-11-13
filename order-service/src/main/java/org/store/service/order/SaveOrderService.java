package org.store.service.order;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.store.models.dtos.request.order.SaveOrderRequest;
import org.store.models.entities.OrderEntity;

import java.beans.Transient;

@ApplicationScoped
public class SaveOrderService {


    @Transactional
    public OrderEntity saveOrder(SaveOrderRequest saveOrderRequest){

    }
}
