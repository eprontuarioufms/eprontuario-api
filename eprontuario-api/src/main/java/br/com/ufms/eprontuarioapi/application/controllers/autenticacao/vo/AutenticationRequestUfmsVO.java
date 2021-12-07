package br.com.ufms.eprontuarioapi.application.controllers.autenticacao.vo;

import lombok.Data;

@Data
public class AutenticationRequestUfmsVO {
    private String passaporte;
    private String senha;
}
