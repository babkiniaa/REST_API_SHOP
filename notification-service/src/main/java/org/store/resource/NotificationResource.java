package org.store.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.store.models.dto.request.NotificationRequest;
import org.store.service.NotificationService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/email")
public class NotificationResource {

    NotificationService notificationService;

    @Inject
    public NotificationResource(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @POST
    @Path("/send/info")
    @Asynchronous
    public CompletionStage<String> sendNotification(NotificationRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            return notificationService.processNotification(request);
        });
    }
}
