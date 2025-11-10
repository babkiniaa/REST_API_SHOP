package org.store.resource.clients;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("client")
public class ClientResource {

    @POST
    @Path("createClient")
    public Response createClient() {
        return Response.ok().build();
    }

    @POST
    @Path("getInfoClient")
    public Response getInfoClient() {
        return Response.ok().build();
    }

}
