package fr.epita.assistants.jws.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
@Entity @Table(name = "player")
@Setter
@Getter
@Data
public class PlayerModel extends PanacheEntityBase {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    @JoinColumn(name = "id")
    @Column(name = "lastbomb")
    public Timestamp lastbomb;
    @Column(name = "lastmovement")
    public Timestamp lastmovement;
     public long game_id;
    public Integer lives;
    public String name;
    public Integer posx;
    public Integer posy;
    public Integer position;
    public @ElementCollection @CollectionTable(name = "game_player", joinColumns = @JoinColumn(name = "players_id")) List<Long> players;

}
