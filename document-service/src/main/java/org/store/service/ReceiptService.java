package org.store.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.store.models.entity.OrderEntity;

@Slf4j
@ApplicationScoped
public class ReceiptService {

    @Inject
    PdfGenerationService pdfService;

    public String generateReceipt(OrderEntity order, String filePath) {
        log.info("Генерация чека для заказа: {}", order.orderNumber);

        return pdfService.createPdfDocument(filePath, path -> {
            buildReceiptContent(order);
        });
    }

    private void buildReceiptContent(OrderEntity order) {
        pdfService.addHeader("Чек заказа № " + order.orderNumber);
        pdfService.addCustomerInfo(order.customer);
        pdfService.addProductsTable(order.productEntity);
        pdfService.addFooter("Спасибо за заказ!");

        log.info("Чек для заказа {} готов!", order.orderNumber);
    }
}