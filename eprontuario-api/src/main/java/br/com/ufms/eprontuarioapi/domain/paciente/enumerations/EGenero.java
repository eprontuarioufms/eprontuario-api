package br.com.ufms.eprontuarioapi.domain.paciente.enumerations;

public enum EGenero {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String genero;

    EGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }
}
