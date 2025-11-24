package org.store.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.store.models.dto.request.DocumentGenerateRequest;
import org.store.service.DocumentService;

import java.util.Map;

@Path("/api/documents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocumentResource {

    @Inject
    DocumentService documentService;

    @POST
    @Path("/receipt/async")
    public Uni<Response> generateReceiptAsync(DocumentGenerateRequest request) {
        return documentService.generateReceiptAsync(request.getOrderId())
                .onItem().transform(filePath -> Response.ok(
                        Map.of(
                                "status", "success",
                                "orderId", request.getOrderId()
                        )
                ).build());
    }

    @GET
    @Path("/receipt/{orderId}/status")
    public Uni<Response> getGenerationStatus(@PathParam("orderId") String orderId) {
        return Uni.createFrom().item(Response.ok(
                Map.of("orderId", orderId, "status", "completed")
        ).build());
    }
}
