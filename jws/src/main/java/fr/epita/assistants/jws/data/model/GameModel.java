package fr.epita.assistants.jws.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.glassfish.json.JsonUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
@Entity @Table(name = "game")
@Getter
@Setter
@Data
public class GameModel extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn public Long id;
    @Column(name ="starttime" )
    public Timestamp starttime;
    @Column(name = "state")
    public String state;

    @JoinColumn(name = "id")
    public @ElementCollection @CollectionTable(name = "game_player", joinColumns = @JoinColumn(name = "gamemodel_id")) List<String> players;
    public @ElementCollection @CollectionTable(name = "game_map", joinColumns = @JoinColumn(name = "gamemodel_id")) List<String> map;
   // @OneToMany(targetEntity = PlayerModel.class, mappedBy = "game_id") public List<Long> game_id;
}
