package org.store.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.store.models.entity.ProductEntity;
import org.store.models.entity.UserEntity;

import java.util.List;
import java.util.function.Consumer;


@Slf4j
@ApplicationScoped
public class PdfGenerationService {


    public String createPdfDocument(String filePath, Consumer<String> documentBuilder) {
        log.info("Начинаем создание PDF документа: {}", filePath);

        simulateHeavyWork();
        documentBuilder.accept(filePath);

        log.info("PDF документ создан: {}", filePath);
        return filePath;
    }

    public void addHeader(String title) {
        log.debug("Добавляем заголовок: {}", title);
        simulateLightWork();
    }

    public void addProductsTable(List<ProductEntity> products) {
        log.debug("Добавляем таблицу товаров ({} позиций)",
                products != null ? products.size() : 0);
        simulateHeavyWork();
    }

    public void addCustomerInfo(UserEntity customer) {
        if (customer != null) {
            log.debug("Добавляем информацию о клиенте: {}", customer.fullName);
            simulateLightWork();
        }
    }

    public void addFooter(String text) {
        log.debug("Добавляем подвал: {}", text);
        simulateLightWork();
    }

    private void simulateHeavyWork() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void simulateLightWork() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}