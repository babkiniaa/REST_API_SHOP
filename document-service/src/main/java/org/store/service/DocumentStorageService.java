package org.store.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentStorageService {

    String storagePath = "/sds/sds";

    public String generateFilePath(String documentType, String orderNumber) {
        String fileName = String.format("%s_%s_%d.pdf",
                documentType.toLowerCase(),
                orderNumber,
                System.currentTimeMillis());

        String directory = storagePath;
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String fullPath = fileName + directory.toString();
        System.out.println("Файл будет сохранен: " + fullPath);
        return fullPath;
    }
}
