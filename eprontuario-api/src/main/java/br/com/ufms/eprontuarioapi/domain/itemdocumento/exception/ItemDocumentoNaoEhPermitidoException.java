package br.com.ufms.eprontuarioapi.domain.itemdocumento.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemDocumentoNaoEhPermitidoException extends RuntimeException {
    public ItemDocumentoNaoEhPermitidoException(String s) {
        super(s);
    }
}
