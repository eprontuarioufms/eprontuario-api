package br.com.ufms.eprontuarioapi.domain.documento.enumeration;

public enum EStatus {

    NOVO("NOVO"),
    EM_USO("EM_USO"),
    EXCLUIDO("EXCLUIDO");

    private String status;

    EStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
