package fr.epita.assistants.jws.presentation.rest.response;

import fr.epita.assistants.jws.domain.entity.GameEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GetAllGamesResponse {
   Long Id;
   Integer players;
   String state;

}
