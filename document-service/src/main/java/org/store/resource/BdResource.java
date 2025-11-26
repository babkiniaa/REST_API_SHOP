package org.store.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.store.models.entity.OrderEntity;
import org.store.models.entity.ProductEntity;
import org.store.models.entity.UserEntity;

import java.util.List;

@Path("/debug")
public class BdResource {

    @GET
    @Path("/users")
    public List<UserEntity> getUsers() {
        return UserEntity.listAll();
    }

    @GET
    @Path("/products")
    public List<ProductEntity> getProducts() {
        return ProductEntity.listAll();
    }

    @GET
    @Path("/orders")
    public List<OrderEntity> getOrders() {
        return OrderEntity.listAll();
    }
}
