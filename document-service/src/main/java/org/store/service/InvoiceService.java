package org.store.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.store.models.entity.OrderEntity;

@ApplicationScoped
@Slf4j
public class InvoiceService {

    @Inject
    PdfGenerationService pdfService;

    public String generateInvoice(OrderEntity order, String filePath) {
        log.info("Генерация счета для заказа: {}", order.orderNumber);

        return pdfService.createPdfDocument(filePath, path -> {
            buildInvoiceContent(order);
        });
    }

    private void buildInvoiceContent(OrderEntity order) {
        pdfService.addHeader("Счет № " + order.orderNumber);
        pdfService.addCustomerInfo(order.customer);
        pdfService.addProductsTable(order.productEntity);
        pdfService.addFooter("Счет действителен 5 дней");

        log.info("Счет для заказа {} готов!", order.orderNumber);
    }
}
