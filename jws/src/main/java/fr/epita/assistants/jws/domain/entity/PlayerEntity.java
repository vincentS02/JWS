package fr.epita.assistants.jws.domain.entity;

import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.model.PlayerModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@AllArgsConstructor
@Getter
@Setter
public class PlayerEntity {
    public Long Id;
    public Timestamp lastbomb;
    public Timestamp lastmovement;
    public Integer lives;
    public String name;
    public Integer posx;
    public Integer posy;
    public Integer position;
    Integer game_id;

}
