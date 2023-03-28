package fr.epita.assistants.jws.data.repository;
import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.domain.entity.GameEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import lombok.AllArgsConstructor;
import org.glassfish.json.JsonUtil;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static fr.epita.assistants.jws.converter.Converter.toEntity;
@ApplicationScoped
public class GameModelRepository implements PanacheRepositoryBase<GameModel, Long>{

}
