package fr.epita.assistants.jws.presentation.rest.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoveRequest {
    public Integer posX;
    public Integer posY;
}
