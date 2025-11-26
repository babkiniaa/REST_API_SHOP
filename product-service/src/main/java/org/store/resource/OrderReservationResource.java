package org.store.resource;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.store.models.entity.OrderEntity;
import org.store.service.OrderReservationService;

import java.time.Duration;
import java.util.Map;


@Path("/api/orders")
@Slf4j
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderReservationResource {

    @Inject
    OrderReservationService reservationService;

    @POST
    @Path("{orderId}/reserve")
    @Blocking
    public Response reserveOrderProducts(@PathParam("orderId") Long orderId) {
        log.info("Резервирование заказа: {}", orderId);

        try {
            OrderEntity order = OrderEntity.findById(orderId);
            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(Map.of("error", "Заказ не найден: " + orderId))
                        .build();
            }

            Map<String, Object> result = reservationService.reserveOrderProducts(order)
                    .await().atMost(Duration.ofSeconds(30));

            return Response.ok(result).build();

        } catch (Exception e) {
            log.error("Ошибка резервирования: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }
}
