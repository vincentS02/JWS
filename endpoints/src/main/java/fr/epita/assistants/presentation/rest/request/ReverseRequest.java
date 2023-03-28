package fr.epita.assistants.presentation.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ws.rs.core.Response;
@AllArgsConstructor

public class ReverseRequest {
    public String reverse;
}