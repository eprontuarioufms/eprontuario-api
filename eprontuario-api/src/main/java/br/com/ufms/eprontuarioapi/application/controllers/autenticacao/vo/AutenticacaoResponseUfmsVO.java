package br.com.ufms.eprontuarioapi.application.controllers.autenticacao.vo;

import lombok.Data;

import java.util.List;

@Data
public class AutenticacaoResponseUfmsVO {
    private String authToken;
    private UsuarioUfmsVO usuario;
    private List<String> tokens;
    private String dn;
}
