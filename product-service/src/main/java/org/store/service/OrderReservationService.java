package org.store.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.store.models.dto.ProductOrder;
import org.store.models.dto.request.ProductReservationRequest;
import org.store.models.dto.request.ReservedOrderRequest;
import org.store.models.entity.OrderEntity;
import org.store.models.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ApplicationScoped
public class OrderReservationService {

    @Inject
    ProductReservationService productReservationService;

    @Inject
    ExternalProductService externalProductService;

    @Transactional
    public Uni<Map<String, Object>> reserveOrderProducts(OrderEntity order) {
        log.info("Начало резервирования товаров для заказа: {}", order.orderNumber);

        List<ProductReservationRequest> reservationRequests = createReservationRequests(order);

        return productReservationService.reserveProducts(reservationRequests, order.orderId)
                .onItem().transformToUni(reservationId -> {
                    ReservedOrderRequest externalRequest = createExternalRequest(order, reservationId);
                    return externalProductService.reserveProductsInExternalService(externalRequest)
                            .onItem().transform(externalResponse -> {
                                return createReservationResponse(reservationId, externalResponse, order);
                            });
                })
                .onFailure().recoverWithUni(throwable -> {
                    log.error("Ошибка резервирования: {}", throwable.getMessage());
                    return Uni.createFrom().item(createErrorResponse(throwable.getMessage(), order));
                });
    }

    private List<ProductReservationRequest> createReservationRequests(OrderEntity order) {
        List<ProductReservationRequest> requests = new ArrayList<>();

        if (order.productEntity != null) {
            for (ProductEntity product : order.productEntity) {
                ProductReservationRequest request = new ProductReservationRequest();
                request.setProductId(product.productId);
                request.setQuantity(product.stockQuantity != null ? product.stockQuantity : 1);
                request.setOrderId(order.orderId);
                requests.add(request);
            }
        }

        return requests;
    }

    private ReservedOrderRequest createExternalRequest(OrderEntity order, String reservationId) {
        ReservedOrderRequest externalRequest = new ReservedOrderRequest();
        externalRequest.setOrderId(order.orderId);
        externalRequest.setReservationId(reservationId);
        externalRequest.setReserveUntil(LocalDateTime.now().plusHours(24));
        externalRequest.setCustomerId(order.customer != null ? order.customer.userId : null);

        List<ProductOrder> productOrders = new ArrayList<>();
        if (order.productEntity != null) {
            for (ProductEntity product : order.productEntity) {
                ProductOrder po = new ProductOrder();
                po.setProductId(product.productId);
                po.setQuantity(product.stockQuantity != null ? product.stockQuantity : 1);
                productOrders.add(po);
            }
        }
        externalRequest.setProducts(productOrders);

        return externalRequest;
    }

    private Map<String, Object> createReservationResponse(String reservationId, String externalResponse, OrderEntity order) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("reservationId", reservationId);
        response.put("externalConfirmation", externalResponse);
        response.put("orderId", order.orderId);
        response.put("reservedUntil", LocalDateTime.now().plusHours(24));
        response.put("message", "Товары успешно зарезервированы");

        log.info("Резервирование завершено для заказа: {}", order.orderNumber);
        return response;
    }

    private Map<String, Object> createErrorResponse(String error, OrderEntity order) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", error);
        response.put("orderId", order.orderId);
        return response;
    }
}
