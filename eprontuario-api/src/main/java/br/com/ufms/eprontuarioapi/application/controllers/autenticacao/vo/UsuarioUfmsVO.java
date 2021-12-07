package br.com.ufms.eprontuarioapi.application.controllers.autenticacao.vo;

import lombok.Data;

@Data
public class UsuarioUfmsVO {
    private String id;
    private String nome;
    private String cpf;
    private String passaporte;
    private String emailAlternativo;
    private String rga;
    private String ativo;
    private String nomeComPassaporte;
}
