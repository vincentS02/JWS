package fr.epita.assistants.jws.presentation.rest.response;

import fr.epita.assistants.jws.domain.entity.PlayerEntity;
import fr.epita.assistants.jws.utils.PlayerInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class CreateGameResponse
{
    Timestamp startTime;
    String state;
    List<PlayerInfo> players;
    List<String> map;
    Long Id;
}
