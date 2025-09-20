package iuh.fit.se;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/numbers")
public class NumberResource {
    // Endpoint: /api/v1/numbers/{value} ->Path param
    // Endpoint: /api/v1/numbers/{value}?key1=value1 ->/Path param?query param
    @GET
    @Path("/{value}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkEven(@PathParam("value") int value) {
        if (value % 2 == 0) {
            return Response.ok("Value is even").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Not an even number").build();
    }

    @GET
    @Path("/sum/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sum(@DefaultValue("0") @PathParam("a") int a,
                        @DefaultValue("0") @PathParam("b") int b) {
        return Response.ok(String.valueOf(a + b)).build();
    }

    @GET
    @Path("/multiply")
    @Produces(MediaType.TEXT_PLAIN)
    public Response multiply(@DefaultValue("1") @QueryParam("a") int a,
                             @DefaultValue("1") @QueryParam("b") int b) {
        return Response.ok(String.valueOf(a * b)).build();
    }
}
