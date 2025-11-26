package org.store.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.store.models.dto.request.ReservedOrderRequest;

@Slf4j
@ApplicationScoped
public class ExternalProductService {
    
    public Uni<String> reserveProductsInExternalService(ReservedOrderRequest request) {
        log.info("Имитация запроса на резервирование во внешний сервис");
        log.info("Данные для резервирования: orderId={}, products={}",
                request.getOrderId(),
                request.getProducts().size());

        return Uni.createFrom().item(() -> {

                    try {
                        Thread.sleep(1000 + (long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Прервано ожидание внешнего сервиса");
                    }

                    if (Math.random() > 0.1) {
                        String reservationId = "EXT-RESERVE-" + request.getOrderId() + "-" + System.currentTimeMillis();
                        log.info("Внешний сервис подтвердил резервирование: {}", reservationId);
                        return reservationId;
                    } else {
                        log.error("Внешний сервис вернул ошибку");
                        throw new RuntimeException("Внешний сервис недоступен или вернул ошибку");
                    }
                })
                .onFailure().recoverWithUni(throwable -> {
                    log.error("Ошибка при работе с внешним сервисом: {}", throwable.getMessage());
                    return Uni.createFrom().failure(
                            new RuntimeException("Ошибка резервирования во внешнем сервисе: " + throwable.getMessage())
                    );
                });
    }
}