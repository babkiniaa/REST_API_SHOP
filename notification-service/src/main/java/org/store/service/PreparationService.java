package org.store.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.store.models.dto.request.NotificationRequest;

import java.util.concurrent.TimeUnit;

@Slf4j
@ApplicationScoped
public class PreparationService {

    public String prepareTemplate(NotificationRequest request) throws InterruptedException {
        sleep(800);
        return switch (request.getType()) {
            case EMAIL -> "email_template.html";
            case SMS -> "sms_template.txt";
            case PUSH -> "push_template.json";
            case MULTI_CHANNEL -> "multi_channel_template";
        };
    }

    public String fillTemplateVariables(String template, NotificationRequest request) throws InterruptedException {
        sleep(400);
        return template + "_filled_with_variables";
    }

    public void saveToHistory(NotificationRequest request, String result) throws InterruptedException {
        sleep(200);
        log.info("Сохранено в историю: {} - {}", request.getType(), result);
    }

    private void sleep(long millis) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(millis);
    }

}
