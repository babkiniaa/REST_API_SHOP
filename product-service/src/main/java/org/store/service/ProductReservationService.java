package org.store.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.store.models.dto.request.ProductReservationRequest;
import org.store.models.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Slf4j
public class ProductReservationService {

    @Inject
    ProductAvailabilityService availabilityService;

    @Transactional
    public Uni<String> reserveProducts(List<ProductReservationRequest> reservationRequests, Long orderId) {
        log.info("Резервирование товаров для заказа: {}", orderId);

        return availabilityService.checkProductsAvailability(reservationRequests)
                .onItem().transformToUni(allAvailable -> {
                    if (!allAvailable) {
                        return Uni.createFrom().failure(
                                new RuntimeException("Не все товары доступны для резервирования")
                        );
                    }
                    return performReservation(reservationRequests, orderId);
                });
    }

    private Uni<String> performReservation(List<ProductReservationRequest> requests, Long orderId) {
        return Uni.createFrom().item(() -> {
            for (ProductReservationRequest request : requests) {
                reserveSingleProduct(request);
            }

            String reservationId = "RESERVE-" + orderId + "-" + System.currentTimeMillis();
            log.info("Резервирование завершено. ID: {}", reservationId);
            return reservationId;
        });
    }

    private void reserveSingleProduct(ProductReservationRequest request) {
        ProductEntity product = ProductEntity.findById(request.getProductId());

        int newStock = product.stockQuantity - request.getQuantity();
        product.stockQuantity = newStock;
        product.updatedAt = LocalDateTime.now();

        product.persist();

        log.info("Зарезервирован товар: {} (количество: {}, остаток: {})",
                product.name, request.getQuantity(), newStock);
    }

    @Transactional
    public Uni<Void> cancelReservation(String reservationId, List<ProductReservationRequest> requests) {
        log.info("Отмена резервирования: {}", reservationId);

        return Uni.createFrom().item(() -> {
            for (ProductReservationRequest request : requests) {
                cancelSingleProductReservation(request);
            }

            log.info("Резервирование отменено: {}", reservationId);
            return null;
        });
    }

    private void cancelSingleProductReservation(ProductReservationRequest request) {
        ProductEntity product = ProductEntity.findById(request.getProductId());

        if (product != null) {
            int newStock = product.stockQuantity + request.getQuantity();
            product.stockQuantity = newStock;
            product.updatedAt = LocalDateTime.now();
            product.persist();

            log.info("Возврат товара: {} (количество: {}, новый остаток: {})",
                    product.name, request.getQuantity(), newStock);
        }
    }
}
