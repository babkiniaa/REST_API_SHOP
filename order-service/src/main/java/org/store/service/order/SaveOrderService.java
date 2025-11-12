package org.store.service.order;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.store.models.dtos.request.order.SaveOrderRequest;

import java.beans.Transient;

@ApplicationScoped
public class SaveOrderService {


    @Transactional
    public void saveOrder(SaveOrderRequest saveOrderRequest){

    }
}
