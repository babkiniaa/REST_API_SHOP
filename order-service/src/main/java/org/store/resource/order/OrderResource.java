package org.store.resource.order;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.store.models.dtos.request.order.OrderPrdInfo;
import org.store.models.dtos.request.order.OrderStatusDto;
import org.store.models.dtos.request.order.SaveOrderRequest;
import org.store.models.dtos.response.OrderResponseDto;
import org.store.service.order.MainProcessor;

import java.util.List;

@Path("/order")
public class OrderResource {

    MainProcessor mainProcessor;

    @Inject
    public OrderResource(MainProcessor mainProcessor) {
        this.mainProcessor = mainProcessor;
    }

    @POST
    @Path("create")
    public Response createOrder(SaveOrderRequest saveOrderRequest) {
        return Response.ok(mainProcessor.createOrder(saveOrderRequest)).build();
    }

    @POST
    @Path("get/pgf")
    public Response getPdFromOrder(OrderPrdInfo orderPrdInfo) {
        return Response.ok().build();
    }

    @POST
    @Path("get/status/reserved")
    public Response getStatusOrder(OrderStatusRequest orderStatusRequest) {
        return Response.ok().build();
    }

    @GET
    @Path("get/order/{id}")
    public OrderStatusDto getOrdersById(@PathParam("id") Long id) {
        return new OrderStatusDto();
    }

    @GET
    @Path("get/order/{userName}")
    public List<OrderResponseDto> getOrdersByPeriod(@PathParam("userName") String userName) {
        return List.of(new OrderResponseDto());
    }


}
