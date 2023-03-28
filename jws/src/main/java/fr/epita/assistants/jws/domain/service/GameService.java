package fr.epita.assistants.jws.domain.service;

import com.google.inject.Inject;
import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.model.PlayerModel;
import fr.epita.assistants.jws.data.repository.GameModelRepository;
import fr.epita.assistants.jws.data.repository.PlayerModelRepository;
import fr.epita.assistants.jws.domain.entity.GameEntity;
import fr.epita.assistants.jws.domain.entity.PlayerEntity;
import fr.epita.assistants.jws.presentation.rest.response.CreateGameResponse;
import fr.epita.assistants.jws.presentation.rest.response.CreateGameResponse;
import fr.epita.assistants.jws.presentation.rest.response.GetAllGamesResponse;
import fr.epita.assistants.jws.utils.PlayerInfo;
import fr.epita.assistants.jws.converter.Converter;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static fr.epita.assistants.jws.converter.Converter.*;
@Transactional
@ApplicationScoped
public class GameService {

    GameModelRepository gamemodelrepo = new GameModelRepository();
    PlayerModelRepository playermodelrepo = new PlayerModelRepository();
  /*  @Transactional
    public GameEntity creategame(Timestamp starttime, String state)
    {
        GameEntity newgame = new GameEntity(starttime, state);
        gamemodelrepo.persist(newgame);
        return newgame;
    }*/
    @Transactional
    public List<GetAllGamesResponse> getGames()
    {
        List<GameEntity> result = new ArrayList<>();
        List<GameModel> issou = gamemodelrepo.listAll();
        List<GetAllGamesResponse> resp = new ArrayList<>();
        for (GameModel game: issou
             ) {
            result.add(toEntity(game, playermodelrepo));

        }
        for (GameEntity ent: result)
        {
            resp.add(toresponse(ent));
        }
        return resp;
    }
    @Transactional
    public List<PlayerInfo> getplayers(GameModel gm)
    {
        List<PlayerEntity> result = new ArrayList<>();
        List<PlayerModel> issou = playermodelrepo.list("game_id", gm.getId());
        List<PlayerInfo> resp = new ArrayList<>();
        for (PlayerModel game: issou
             ) {
            result.add(toEntityplayer(game));

        }
        for (PlayerEntity ent: result)
        {
            resp.add(fromEntitytoInfo(ent));
        }
        return resp;
    }
    @Transactional
    public CreateGameResponse CreatingGame(String name)
    {
        GameModel newgame = new GameModel();
        PlayerModel newplayer = new PlayerModel();
        newgame.setState("STARTING");
        newgame.setStarttime(Timestamp.valueOf(LocalDateTime.now()));
        newplayer.setPosx(1);
        List<String> map ;
          try {
            map = Files.readAllLines(Path.of(System.getenv("JWS_MAP_PATH")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
          newgame.setMap(map);
        newplayer.setPosy(1);
        gamemodelrepo.persist(newgame);
        newplayer.setGame_id(newgame.getId());
        newplayer.setName(name);
        newplayer.setLives(3);
        playermodelrepo.persist(newplayer);
        CreateGameResponse finalgame =  tocreategame(toEntity(newgame, playermodelrepo), toEntityplayer(newplayer), playermodelrepo, newgame);

        return finalgame;


    }
    @Transactional
    public CreateGameResponse  GetGameById(Long gameId)
    {
        GameModel game = gamemodelrepo.findById(gameId);
        if (game == null)
            return null;
        GameEntity entity = toEntity(game, playermodelrepo);

        List<PlayerInfo> info = new ArrayList<>();
        for (PlayerEntity zobi : entity.getPlayers()
             ) {
            info.add(fromEntitytoInfo(zobi));
        }
        CreateGameResponse fin = new CreateGameResponse(entity.getStarttime(), entity.getState(), info, entity.getMap(), entity.getId());
        return fin;

    }
    @Transactional
    public CreateGameResponse JoinGame(Long gameId, String name)
    {
        GameModel game = gamemodelrepo.findById(gameId);
        if (game == null)
            return null;
        GameEntity entity = toEntity(game, playermodelrepo);
        PlayerModel newplayer = new PlayerModel();
        if (entity.getPlayers().size() == 1)
        {
            newplayer.setPosx(15);
            newplayer.setPosy(1);
        }
        else if (entity.getPlayers().size() == 2)
        {
            newplayer.setPosx(15);
            newplayer.setPosy(13);
        }
        else if (entity.getPlayers().size() == 3)
        {
            newplayer.setPosx(1);
            newplayer.setPosy(13);
        }
        else{
            return new CreateGameResponse(null, "tomuch", null, null, null);
        }
        if(game.getState().equals("RUNNING") || game.getState().equals("FINISHED")){
            return new CreateGameResponse(null, "tomuch", null, null, null );
        }
        newplayer.setGame_id(gameId);
        newplayer.setName(name);
        newplayer.setLives(3);
         playermodelrepo.persist(newplayer);
        GameModel newgame = gamemodelrepo.findById(gameId);
        GameEntity entit = toEntity(game, playermodelrepo);

        List<PlayerInfo> info = new ArrayList<>();
        for (PlayerEntity zobi : entit.getPlayers()
             ) {
            info.add(fromEntitytoInfo(zobi));
        }
        CreateGameResponse fin = new CreateGameResponse(entit.getStarttime(), entit.getState(), info, entit.getMap(), entit.getId());
        return fin;
    }
    @Transactional
    public CreateGameResponse startGame(long gameId)
    {
        GameModel game = gamemodelrepo.findById(gameId);
        if (game == null)
            return null;
        GameEntity test = toEntity(game, playermodelrepo);
        if (game.getState().equals("FINISHED"))
            return null;
        if (game.getState().equals("RUNNING"))
            return null;
        if (test.getPlayers().size() == 1)
            game.setState("FINISHED");
        else
            game.setState("RUNNING");
        GameEntity entity = toEntity(game, playermodelrepo);
        List<PlayerInfo> info = new ArrayList<>();
        for (PlayerEntity zobi : entity.getPlayers()
        ) {
            info.add(fromEntitytoInfo(zobi));
        }
        CreateGameResponse fin = new CreateGameResponse(entity.getStarttime(), entity.getState(), info, entity.getMap(), entity.getId());
        return fin;

    }
    public CreateGameResponse movePlayer(long gameId, Integer posX, Integer posY, long playerId)
    {
        GameModel game = gamemodelrepo.findById(gameId);
        if (game == null)
            return null;
        PlayerModel player = playermodelrepo.findById(playerId);
        if (player == null)
            return null;
        if (player.getLastmovement() != null) {
              long TimeDiff = player.getLastmovement().getTime() - Timestamp.valueOf(LocalDateTime.now()).getTime();
              if (TimeDiff < Long.parseLong(System.getenv("JWS_TICK_DURATION")) *Long.parseLong(System.getenv("JWS_DELAY_MOVEMENT"))) {
                  return new CreateGameResponse(null, "long", null, null, null);
              }
          }
        if (posX == null || posY == null)
            return new CreateGameResponse(null, "tomuch", null, null, null );
         GameEntity test = toEntity(game, playermodelrepo);
         if (!player.getPosx().equals(posX) && !player.getPosy().equals(posY))
             return new CreateGameResponse(null, "tomuch", null, null, null );
         if (!player.getPosx().equals(posX - 1) && !player.getPosy().equals(posY -1))
             return new CreateGameResponse(null, "tomuch", null, null, null );
          if(game.getState().equals("STARTING") || game.getState().equals("FINISHED") || player.getLives() == 0){
            return new CreateGameResponse(null, "tomuch", null, null, null );
        }

          player.setPosx(posX);
          player.setPosy(posY);
          GameEntity entity = toEntity(game, playermodelrepo);
          player.setLastmovement(Timestamp.from(Instant.now()));

          List<PlayerInfo> info = new ArrayList<>();
        for (PlayerEntity zobi : entity.getPlayers()
        ) {
            info.add(fromEntitytoInfo(zobi));
        }
          return new CreateGameResponse(entity.getStarttime(), entity.getState(), info, entity.getMap(), entity.getId());

    }
    public CreateGameResponse PutABomb(long gameId, Integer posX, Integer posY, long playerId) {
        GameModel game = gamemodelrepo.findById(gameId);
        if (game == null)
            return null;
        PlayerModel player = playermodelrepo.findById(playerId);
        if (player == null)
            return null;
        if (posX == null || posY == null)
             return new CreateGameResponse(null, "tomuch", null, null, null );
        GameEntity test = toEntity(game, playermodelrepo);
        if (!game.getState().equals("RUNNING")) {
            return new CreateGameResponse(null, "tomuch", null, null, null);
        }
         if (player.getPosx().equals(posX) && player.getPosy().equals(posY))
             return new CreateGameResponse(null, "tomuch", null, null, null );
         if (!player.getPosx().equals(posX - 1) && !player.getPosy().equals(posY -1))
             return new CreateGameResponse(null, "tomuch", null, null, null );
        return new CreateGameResponse(null, "tomuch", null, null, null);
    }

}
