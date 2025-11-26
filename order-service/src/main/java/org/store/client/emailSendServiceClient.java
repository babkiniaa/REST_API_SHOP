package org.store.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.store.models.dtos.request.email.EmailInfoRequest;

import java.util.concurrent.CompletableFuture;


@Path("/email")
@RegisterRestClient(baseUri = "http://localhost:8040")
public interface emailSendServiceClient {

    @POST
    @Path("/send/info")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CompletableFuture<String> sendInfo(EmailInfoRequest emailInfoRequest);
}
