package org.store.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.store.models.dtos.request.email.EmailRequestCheck;
import org.store.models.dtos.request.email.EmailRequestDocx;

import java.util.concurrent.CompletionStage;

@Path("/print")
@RegisterRestClient(baseUri = "http://localhost:8060")
public interface printServiceClient {

    @POST
    @Path("/docx")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CompletionStage<String> sendGenerateDocx(EmailRequestDocx request);


    @POST
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CompletionStage<String> sendGenerateCheck(EmailRequestCheck request);

}
