package br.com.ufms.eprontuarioapi.domain.perfil.enumeration;

public enum EPerfil {
    PROFESSOR("ROLE_PROFESSOR"),
    ACADEMICO("ROLE_ACADEMICO"),
    ADMINISTRADOR("ROLE_ADMINISTRADOR");

    private String perfil;

    EPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }
}
