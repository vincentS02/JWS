package fr.epita.assistants.jws.domain.entity;

import com.google.inject.Inject;
import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.repository.GameModelRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.glassfish.json.JsonUtil;
import java.lang.System.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

import static fr.epita.assistants.jws.converter.Converter.toEntity;

@AllArgsConstructor
@Getter
@Setter
public class GameEntity {
    public Long Id;
    Timestamp starttime;
    String state;
    List<PlayerEntity> players;
    List<String> map;
/*   public GameEntity(Timestamp starttime, String state)
    {
        this.state = state;
        this.starttime = starttime;
    }*/
    /*@Inject
    GameModelRepository gamemodelrepo;*/
    /*public GameEntity CreateGame()
    {
        GameEntity newgame = new GameEntity(starttime, state);
        gamemodelrepo.persist(newgame);
        return newgame;
    }*/
}
