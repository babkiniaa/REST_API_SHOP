package org.store.client;


import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.store.models.dtos.request.payment.PaymentRequest;

import java.util.concurrent.CompletableFuture;

@Path("/payment")
@RegisterRestClient(baseUri = "http://localhost:8050")
public interface paymentServiceClient {

    @POST
    CompletableFuture<String> reservedProduct(PaymentRequest request);
}
