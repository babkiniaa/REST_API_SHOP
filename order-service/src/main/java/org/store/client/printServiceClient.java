package org.store.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.store.models.dtos.request.email.EmailRequestCheck;
import org.store.models.dtos.request.email.EmailRequestDocx;

import java.util.concurrent.CompletionStage;

@Path("/api/documents")
@RegisterRestClient(baseUri = "http://localhost:8060")
public interface printServiceClient {

    @POST
    @Path("/receipt/async")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CompletionStage<String> sendGenerateDocx(EmailRequestDocx request);


    @POST
    @Path("/receipt/async")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CompletionStage<String> sendGenerateCheck(EmailRequestCheck request);

    @GET
    @Path("/receipt/{orderId}/status")
    CompletionStage<String> getGeneratorCheck(@PathParam("orderId") String orderId);

}
