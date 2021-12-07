package br.com.ufms.eprontuarioapi.domain.permissaomodelodocumento.enumeration;

public enum ETipoPermissao {
    CRIAR("CRIAR"),
    ALTERAR("ALTERAR"),
    VISUALIZAR("VISUALIZAR");

    private String tipoPermissao;

    ETipoPermissao(String tipoPermissao) {
        this.tipoPermissao = tipoPermissao;
    }

    public String getTipoPermissao() {
        return tipoPermissao;
    }

}
