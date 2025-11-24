package org.store.resource.clients;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.store.models.dtos.request.client.ClientCreate;
import org.store.service.order.MainProcessor;

@Path("client")
public class ClientResource {

    MainProcessor mainProcessor;

    @Inject
    public ClientResource(MainProcessor mainProcessor) {
        this.mainProcessor = mainProcessor;
    }


    @POST
    @Path("/create")
    public Response createClient(ClientCreate clientCreate) {
        return Response.ok(mainProcessor.createUser(clientCreate)).build();
    }

    @GET
    @Path("/get/client/{name}")
    public Response getInfoClient(@PathParam("name") String name) {
        return Response.ok().build();
    }

}
