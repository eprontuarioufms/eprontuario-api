package br.com.ufms.eprontuarioapi.domain.pessoa.enumeration;

public enum ETipoPessoa {
    ACADEMICO("ACADEMICO"),
    PACIENTE("PACIENTE"),
    ADMNISTRADOR("ADMNISTRADOR"),
    PROFESSOR("PROFESSOR");

    private String tipoPessoa;

    ETipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }
}
