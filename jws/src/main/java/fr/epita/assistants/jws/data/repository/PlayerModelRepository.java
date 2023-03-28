package fr.epita.assistants.jws.data.repository;
import fr.epita.assistants.jws.data.model.PlayerModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerModelRepository implements PanacheRepositoryBase<PlayerModel, Long>{
}
