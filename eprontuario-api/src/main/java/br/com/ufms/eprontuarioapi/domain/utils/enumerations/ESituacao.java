package br.com.ufms.eprontuarioapi.domain.utils.enumerations;

public enum ESituacao {
    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    private String situacao;

    ESituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }
}
