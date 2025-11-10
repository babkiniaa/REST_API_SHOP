package org.store.resource.order;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.store.models.dtos.request.*;
import org.store.models.dtos.response.OrderResponseDto;
import org.store.service.order.MainProcessor;

import java.util.List;

@Path("order")
public class OrderResource {

    MainProcessor mainProcessor;

    @Inject
    public OrderResource(MainProcessor mainProcessor) {
        this.mainProcessor = mainProcessor;
    }

    @POST
    @Path("createOrder")
    public Response createOrder(SaveOrderRequest saveOrderRequest) {
        mainProcessor.createOrder(saveOrderRequest);
        return Response.ok().build();
    }

    @POST
    @Path("getPdfOrder")
    public Response getPdFromOrder(OrderPrdInfo orderPrdInfo) {
        return Response.ok().build();
    }

    @GET
    @Path("getOrder")
    public OrderStatusDto getOrdersById(OrderGetRequest request) {
        return new OrderStatusDto();
    }

    @GET
    @Path("orderByPeriodAndName")
    public List<OrderResponseDto> getOrdersByPeriod(OrderGetRequestPeriod request) {
        return List.of(new OrderResponseDto());
    }

}
