package org.store.service;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.store.models.entity.OrderEntity;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Slf4j
public class DocumentService {
    @Inject
    ReceiptService receiptService;

    @Inject
    InvoiceService invoiceService;

    @Inject
    DocumentStorageService storageService;

    @NonBlocking
    public Uni<String> generateReceiptAsync(String orderId) {
        log.info("Асинхронная генерация чека для заказа: {}", orderId);

        return Uni.createFrom().item(orderId)
                .onItem().transform(this::fetchOrderData)
                .onItem().transform(order -> {
                    String filePath = storageService.generateFilePath("receipt", order.orderNumber);
                    return receiptService.generateReceipt(order, filePath);
                })
                .onFailure().recoverWithItem(throwable -> {
                    log.error("Ошибка генерации чека для заказа {}: {}", orderId, throwable.getMessage());
                    return "ERROR: " + throwable.getMessage();
                });
    }

    @NonBlocking
    public Uni<Map<String, String>> generateOrderDocumentsAsync(String orderId) {
        log.info("Асинхронная генерация всех документов для заказа: {}", orderId);

        return Uni.combine().all().unis(
                generateReceiptAsync(orderId),
                generateInvoiceAsync(orderId)
        ).asTuple().onItem().transform(tuple -> {
            Map<String, String> result = new HashMap<>();
            result.put("receipt", tuple.getItem1());
            result.put("invoice", tuple.getItem2());
            result.put("message", "Все документы сгенерированы асинхронно!");
            return result;
        });
    }

    private Uni<String> generateInvoiceAsync(String orderId) {
        return Uni.createFrom().item(orderId)
                .onItem().transform(this::fetchOrderData)
                .onItem().transform(order -> {
                    String filePath = storageService.generateFilePath("invoice", order.orderNumber);
                    return invoiceService.generateInvoice(order, filePath);
                });
    }

    private OrderEntity fetchOrderData(String orderId) {
        OrderEntity order = OrderEntity.findById(Long.valueOf(orderId));
        if (order == null) {
            throw new RuntimeException("Order not found: " + orderId);
        }
        return order;
    }
}