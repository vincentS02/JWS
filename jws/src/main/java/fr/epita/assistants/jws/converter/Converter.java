package fr.epita.assistants.jws.converter;

import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.model.PlayerModel;
import fr.epita.assistants.jws.data.repository.GameModelRepository;
import fr.epita.assistants.jws.data.repository.PlayerModelRepository;
import fr.epita.assistants.jws.domain.entity.GameEntity;
import fr.epita.assistants.jws.domain.entity.PlayerEntity;
import fr.epita.assistants.jws.domain.service.GameService;
import fr.epita.assistants.jws.presentation.rest.response.CreateGameResponse;
import fr.epita.assistants.jws.presentation.rest.response.CreateGameResponse;
import fr.epita.assistants.jws.presentation.rest.response.GetAllGamesResponse;
import fr.epita.assistants.jws.utils.PlayerInfo;
import fr.epita.assistants.jws.domain.service.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Provider;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Converter {

     public static GameEntity toEntity(GameModel gm, PlayerModelRepository playerRepository)
    {

        List<PlayerModel> vince =  playerRepository.list("game_id",gm.getId());
        List<PlayerEntity> players = new ArrayList<>();
        vince.forEach(lambda -> players.add(toEntityplayer(lambda)));
        GameEntity res = new GameEntity(
                        gm.getId(),
                        gm.getStarttime(),
                        gm.getState(),
                players,
                new ArrayList<>(gm.getMap())

                );
        return  res;
    }
    /*public static GameEntity toEntity(GameModel game) {
        Long Id = game.getId();
        Timestamp starttime = game.getStarttime();
        String state = game.getState();
        List<GameEntity> players =
        List<String> map = game.getMap();
        GameEntity newgame = new GameEntity(Id, starttime, state, players, map);
        return newgame;

    }*/

    public static PlayerEntity toEntityplayer(PlayerModel player) {
        Long Id = player.getId();
        Timestamp lastbomb = player.getLastbomb();
        Timestamp lastmovement = player.getLastmovement();
        Integer lives = player.getLives();
        String name = player.getName();
        Integer posx = player.getPosx();
        Integer posy = player.getPosy();
        Integer position = player.getPosition();
        Integer game_id = Math.toIntExact(player.getGame_id());
        PlayerEntity newplayer = new PlayerEntity(Id, lastbomb, lastmovement, lives, name, posx, posy, position, game_id);
        return newplayer;
    }
    public static GetAllGamesResponse toresponse(GameEntity game)
    {
        Long Id = game.getId();
        String state = game.getState();
        Integer players = game.getPlayers().size();
        GetAllGamesResponse resp = new GetAllGamesResponse(Id, players, state);
        return resp;
    }
    public static CreateGameResponse tocreategame(GameEntity game, PlayerEntity player, PlayerModelRepository playrepo, GameModel gm) {
        Timestamp starttime = game.getStarttime();
        String state = game.getState();
        Long Id = game.getId();
        List<String> map = game.getMap();
        GameService service = new GameService();
        List<PlayerInfo> listplayer = service.getplayers(gm);
        return new CreateGameResponse(starttime, state, listplayer, map, Id);

    }
    public static PlayerInfo fromEntitytoInfo(PlayerEntity player)
    {
        Long Id = player.getId();
        String name = player.getName();
        Integer lives = player.getLives();
        Integer posX = player.getPosx();
        Integer posY = player.getPosy();
        PlayerInfo nouv = new PlayerInfo(Id, name, lives, posX, posY);
        return nouv;
    }



    public static GameModel toModel(GameEntity game) {
        Long Id = game.getId();
        Timestamp starttime = game.getStarttime();
        String state = game.getState();
        GameModel newgame = new GameModel();
        return newgame;

    }

    public static PlayerModel toModelPlayer(PlayerEntity player) {
       /* Long Id = player.getId();
        Timestamp lastbomb = player.getLastbomb();
        Timestamp lastmovement = player.getLastmovement();
        Integer lives = player.getLives();
        String name = player.getName();
        Integer posx = player.getPosx();
        Integer posy = player.getPosy();
        Integer position = player.getPosition();
        GameModel = player.getGame_id();*/
        PlayerModel newplayer = new PlayerModel();
        return newplayer;
    }
}
