package br.com.ufms.eprontuarioapi.application.config.security.token.dto;

import lombok.Data;

@Data
public class TokenDTO {

    private String token;
    private String tipoAutenticacao;

    public TokenDTO(String token, String bearer) {
        this.token = token;
        this.tipoAutenticacao = bearer;
    }
}
