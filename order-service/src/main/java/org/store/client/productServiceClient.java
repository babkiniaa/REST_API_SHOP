package org.store.client;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.store.models.dtos.request.order.ReservedOrderRequest;

import java.util.concurrent.CompletableFuture;

@RegisterRestClient
@Path("localhost:8070/product")
public interface productServiceClient {

    @POST
    @Path("/reserved")
    CompletableFuture<String> reservedProduct(ReservedOrderRequest request);
}
