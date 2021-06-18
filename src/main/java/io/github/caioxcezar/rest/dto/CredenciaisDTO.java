package io.github.caioxcezar.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredenciaisDTO {
    private String login;
    private String senha;
}
