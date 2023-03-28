package fr.epita.assistants.jws.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlayerInfo {
    public  Long id;
    public String name;
    public Integer lives;
    public Integer posX;
    public Integer posY;
}
