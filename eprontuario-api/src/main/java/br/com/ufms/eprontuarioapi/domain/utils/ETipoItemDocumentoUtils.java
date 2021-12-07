package br.com.ufms.eprontuarioapi.domain.utils;

import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.entity.ModeloItemDocumento;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.pojo.ModeloItemDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.entity.ItemDocumento;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.enumeration.ETipoItem;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.pojo.ItemDocumentoPojo;

public class ETipoItemDocumentoUtils {

    private ETipoItemDocumentoUtils() {
    }

    public static boolean isString(ModeloItemDocumento c) {
        return ETipoItem.STRING.equals(c.getTipo());
    }

    public static boolean isBoolean(ModeloItemDocumento c) {
        return ETipoItem.BOOLEAN.equals(c.getTipo());
    }

    public static boolean isDouble(ModeloItemDocumento c) {
        return ETipoItem.DOUBLE.equals(c.getTipo());
    }

    public static boolean isInteger(ModeloItemDocumento c) {
        return ETipoItem.INTEGER.equals(c.getTipo());
    }

    public static boolean isDataHora(ModeloItemDocumento c) {
        return ETipoItem.DATAHORA.equals(c.getTipo());
    }

    public static boolean isValorFixo(ModeloItemDocumento c) {
        return ETipoItem.VALORFIXO.equals(c.getTipo());
    }


    public static boolean isString(ModeloItemDocumentoPojo cPojo) {
        return ETipoItem.STRING.equals(cPojo.getTipo());
    }

    public static boolean isBoolean(ModeloItemDocumentoPojo cPojo) {
        return ETipoItem.BOOLEAN.equals(cPojo.getTipo());
    }

    public static boolean isDouble(ModeloItemDocumentoPojo cPojo) {
        return ETipoItem.DOUBLE.equals(cPojo.getTipo());
    }

    public static boolean isInteger(ModeloItemDocumentoPojo cPojo) {
        return ETipoItem.INTEGER.equals(cPojo.getTipo());
    }

    public static boolean isDataHora(ModeloItemDocumentoPojo cPojo) {
        return ETipoItem.DATAHORA.equals(cPojo.getTipo());
    }

    public static boolean isValorFixo(ModeloItemDocumentoPojo cPojo) {
        return ETipoItem.VALORFIXO.equals(cPojo.getTipo());
    }


    public static boolean isString(ItemDocumento item) {
        return ETipoItem.STRING.equals(item.getTipo());
    }

    public static boolean isBoolean(ItemDocumento item) {
        return ETipoItem.BOOLEAN.equals(item.getTipo());
    }

    public static boolean isDouble(ItemDocumento item) {
        return ETipoItem.DOUBLE.equals(item.getTipo());
    }

    public static boolean isInteger(ItemDocumento item) {
        return ETipoItem.INTEGER.equals(item.getTipo());
    }

    public static boolean isDataHora(ItemDocumento item) {
        return ETipoItem.DATAHORA.equals(item.getTipo());
    }

    public static boolean isValorFixo(ItemDocumento item) {
        return ETipoItem.VALORFIXO.equals(item.getTipo());
    }


    public static boolean isString(ItemDocumentoPojo itemPojo) {
        return ETipoItem.STRING.equals(itemPojo.getTipo());
    }

    public static boolean isBoolean(ItemDocumentoPojo itemPojo) {
        return ETipoItem.BOOLEAN.equals(itemPojo.getTipo());
    }

    public static boolean isDouble(ItemDocumentoPojo itemPojo) {
        return ETipoItem.DOUBLE.equals(itemPojo.getTipo());
    }

    public static boolean isInteger(ItemDocumentoPojo itemPojo) {
        return ETipoItem.INTEGER.equals(itemPojo.getTipo());
    }

    public static boolean isDataHora(ItemDocumentoPojo itemPojo) {
        return ETipoItem.DATAHORA.equals(itemPojo.getTipo());
    }

    public static boolean isValorFixo(ItemDocumentoPojo itemPojo) {
        return ETipoItem.VALORFIXO.equals(itemPojo.getTipo());
    }


}
