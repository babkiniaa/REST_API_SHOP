package org.store.client;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.store.models.dtos.request.order.ReservedOrderRequest;

import java.util.concurrent.CompletableFuture;

@Path("/api/orders")
@RegisterRestClient(baseUri = "http://localhost:8070")
public interface productServiceClient {

    @POST
    @Path("/{orderId}/reserve")
    CompletableFuture<String> reservedProduct(@PathParam("orderId") Long orderId);
}
