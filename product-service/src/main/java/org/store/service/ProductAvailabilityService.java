package org.store.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.store.models.dto.request.ProductReservationRequest;
import org.store.models.entity.ProductEntity;

import java.util.List;

@ApplicationScoped
@Slf4j
public class ProductAvailabilityService {

    public Uni<Boolean> checkProductsAvailability(List<ProductReservationRequest> reservationRequests) {
        log.info("Проверка доступности {} товаров", reservationRequests.size());

        return Uni.createFrom().item(() -> {
            for (ProductReservationRequest request : reservationRequests) {
                boolean isAvailable = checkSingleProductAvailability(request);
                if (!isAvailable) {
                    log.warn("Товар недоступен для резервирования: {}", request.getProductId());
                    return false;
                }
            }
            log.info("Все товары доступны для резервирования");
            return true;
        });
    }

    private boolean checkSingleProductAvailability(ProductReservationRequest request) {
        ProductEntity product = ProductEntity.findById(request.getProductId());

        if (product == null) {
            log.warn("Товар не найден: {}", request.getProductId());
            return false;
        }

        if (product.stockQuantity == null || product.stockQuantity < request.getQuantity()) {
            log.warn("Недостаточно товара: {} (доступно: {}, требуется: {})",
                    product.name, product.stockQuantity, request.getQuantity());
            return false;
        }

        if (!"ACTIVE".equals(product.status)) {
            log.warn("Товар недоступен: {} (статус: {})", product.name, product.status);
            return false;
        }

        log.debug("Товар доступен: {} (количество: {})", product.name, request.getQuantity());
        return true;
    }
}