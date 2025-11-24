package org.store.service.order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.store.models.dtos.request.order.ProductOrder;
import org.store.models.dtos.request.order.SaveOrderRequest;
import org.store.models.entities.OrderEntity;
import org.store.models.entities.ProductEntity;
import org.store.models.entities.UserEntity;
import org.store.models.enums.OrderStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SaveOrderService {

    @Transactional
    public OrderEntity saveOrder(SaveOrderRequest saveOrderRequest) {
        OrderEntity order = new OrderEntity();

        order.orderNumber = generateOrderNumber();
        order.dateCreate = LocalDateTime.now();
        order.orderStatus = OrderStatus.PROCESSED.name();
        order.addressOrder = saveOrderRequest.getShippingAddress();
        order.awaitDateDeliver = LocalDateTime.now().plusDays(3);
        order.customer = findOrCreateUser(saveOrderRequest.getCustomerEmail());
        order.productEntity = findProductsByIds(saveOrderRequest.getItems());

        order.persist();
        return order;
    }

    private String generateOrderNumber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String random = String.valueOf((int) (Math.random() * 1000));
        return "ORD-" + timestamp + "-" + random;
    }

    private List<ProductEntity> findProductsByIds(List<ProductOrder> items) {
        if (items == null || items.isEmpty()) {
            return new ArrayList<>();
        }

        List<Long> productIds = items.stream()
                .map(ProductOrder::getProductId)
                .collect(Collectors.toList());
        List<ProductEntity> products = ProductEntity.find("productId in ?1", productIds).list();

        return products;
    }

    private UserEntity findOrCreateUser(String ourEmail) {
        return UserEntity.findByEmail(ourEmail);
    }
}
