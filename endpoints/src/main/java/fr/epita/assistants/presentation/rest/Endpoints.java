package fr.epita.assistants.presentation.rest;
import fr.epita.assistants.presentation.rest.request.ReverseRequest;
import fr.epita.assistants.presentation.rest.response.HelloResponse;
import fr.epita.assistants.presentation.rest.response.ReverseResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
@Path("/")
public class Endpoints {
    @GET
    @Path("/hello/{content}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(@PathParam("content") String content) {
        return Response.ok(new HelloResponse("hello " + content)).build();
    }
    @POST
  @Path("/reverse")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response reverse(ReverseRequest request) {
    return Response.ok(new ReverseResponse(request.reverse)).build();
  }
}