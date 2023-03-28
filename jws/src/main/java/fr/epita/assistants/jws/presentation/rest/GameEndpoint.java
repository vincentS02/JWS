package fr.epita.assistants.jws.presentation.rest;

import fr.epita.assistants.jws.data.repository.GameModelRepository;
import fr.epita.assistants.jws.domain.entity.GameEntity;
import fr.epita.assistants.jws.presentation.rest.request.MoveRequest;
import fr.epita.assistants.jws.presentation.rest.response.CreateGameResponse;
import fr.epita.assistants.jws.presentation.rest.response.CreateGameResponse;
import fr.epita.assistants.jws.presentation.rest.response.GetAllGamesResponse;
import fr.epita.assistants.jws.domain.service.GameService;
import fr.epita.assistants.jws.presentation.rest.request.Request;
import io.restassured.authentication.PreemptiveAuthProvider;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/games")
public class GameEndpoint {
   /* @GET @Path("/")
    public String helloWorld() {
        return "Hello World!";
    }

    @GET @Path("/{name}")
    public String helloWorld(@PathParam("name") String name) {
        return "Hello " + name + "!";

    }*/
    @Inject GameService service;
    @GET
    @Path("/")
    public Response getgamesrequest()
    {
     List<GetAllGamesResponse> resp =  service.getGames();
     return Response.status(200).entity(resp).build();

    }
    @POST

    @Path("/")
    public Response initgame(Request request)
    {
      String name = request.getName();
      if (name == null)
          return Response.status(400).build();
      CreateGameResponse newgame = service.CreatingGame(name);
      return Response.status(200).entity(newgame).build();
    }
    @GET
    @Path("/{gameId}")
    public Response getinfo(@PathParam("gameId") long gameId)
    {
        CreateGameResponse newgame = service.GetGameById(gameId);
        if (newgame == null)
            return Response.status(404).build();
        return Response.status(200).entity(newgame).build();
    }
    @POST
    @Path("/{gameId}")
    public Response join(@PathParam("gameId") long gameId, Request request)
    {
        String name = request.getName();
        if (request == null || name == null)
        {
            return Response.status(400).build();
        }
        CreateGameResponse newgame = service.JoinGame(gameId, name);
        if (newgame == null)
            return Response.status(404).build();
        if (newgame.getState().equals("tomuch"))
            return Response.status(400).build();
        return Response.status(200).entity(newgame).build();


    }
    @PATCH
    @Path("/{gameId}/start")
    public Response start(@PathParam("gameId") long gameId)
    {
        CreateGameResponse newgame = service.startGame(gameId);
        if (newgame == null)
            return Response.status(404).build();
        return Response.status(200).entity(newgame).build();
    }
    @POST
    @Path("/{gameId}/players/{playersId}/move")
    public Response move(@PathParam("gameId") long gameId, @PathParam("playersId") long playersId, MoveRequest request)
    {
        CreateGameResponse newgame = service.movePlayer(gameId, request.getPosX(), request.getPosY(), playersId);
        if (newgame == null)
            return Response.status(404).build();
        if (newgame.getState().equals("long"))
            return Response.status(429).build();
        if (request.getPosX() == null || request.getPosY() == null)
             return Response.status(400).build();
         if (newgame.getState().equals("tomuch"))
            return Response.status(400).build();
        return Response.status(200).entity(newgame).build();
    }
    @POST
    @Path("/{gameId}/players/{playerId}/bomb")
    public Response addBomb(@PathParam("gameId") long gameId, @PathParam("playerId") long playerId, MoveRequest request)
    {
        CreateGameResponse newgame = service.PutABomb(gameId, request.getPosX(), request.getPosY(), playerId);
        if (newgame == null)
            return Response.status(404).build();
        if (request.getPosY() == null || request.getPosX() == null)
        {
            return Response.status(400).build();
        }
         if (newgame.getState().equals("tomuch"))
            return Response.status(400).build();
        return Response.status(400).entity(newgame).build();
    }




}
