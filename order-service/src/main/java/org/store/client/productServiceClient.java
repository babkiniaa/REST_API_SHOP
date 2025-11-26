package org.store.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.store.models.dtos.request.order.ReservedOrderRequest;

import java.util.concurrent.CompletableFuture;

@Path("/api/orders")
@RegisterRestClient(baseUri = "http://localhost:8070")
public interface productServiceClient {

    @POST
    @Path("/{orderId}/reserve")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CompletableFuture<String> reservedProduct(@PathParam("orderId") Long orderId);
}
