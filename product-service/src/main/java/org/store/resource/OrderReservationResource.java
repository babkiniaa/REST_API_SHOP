package org.store.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.store.models.entity.OrderEntity;
import org.store.service.OrderReservationService;

import java.util.Map;

@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderReservationResource {

    @Inject
    OrderReservationService reservationService;

    @POST
    @Path("/{orderId}/reserve")
    public Uni<Response> reserveOrderProducts(@PathParam("orderId") Long orderId) {
        return Uni.createFrom().item(() -> OrderEntity.findById(orderId))
                .onItem().ifNull().failWith(() -> new RuntimeException("Order not found: " + orderId))
                .onItem().transformToUni(order -> reservationService.reserveOrderProducts((OrderEntity) order))
                .onItem().transform(result -> Response.ok(result).build())
                .onFailure().recoverWithItem(throwable ->
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity(Map.of("error", throwable.getMessage()))
                                .build()
                );
    }
}
