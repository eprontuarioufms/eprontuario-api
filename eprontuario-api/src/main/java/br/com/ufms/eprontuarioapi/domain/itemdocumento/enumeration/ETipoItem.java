package br.com.ufms.eprontuarioapi.domain.itemdocumento.enumeration;

public enum ETipoItem {

    STRING("STRING"),
    BOOLEAN("BOOLEAN"),
    INTEGER("INTEGER"),
    DOUBLE("DOUBLE"),
    DATAHORA("DATA_HORA"),
    VALORFIXO("VALOR_FIXO");

    private String tipoItem;

    ETipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public String getTipoItem() {
        return this.tipoItem;
    }
}
