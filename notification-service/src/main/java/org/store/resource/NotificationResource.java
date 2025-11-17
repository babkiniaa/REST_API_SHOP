package org.store.resource;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.store.models.dto.request.NotificationRequest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/email")
public class NotificationResource {
    @POST
    @Path("/send/info")
    @Asynchronous
    public CompletionStage<Response> sendNotification(NotificationRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            return processNotification(request);
        });
    }
}
