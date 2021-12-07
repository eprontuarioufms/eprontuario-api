package br.com.ufms.eprontuarioapi.domain.documento.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DocumentoSemValorDoubleException extends RuntimeException {
    public DocumentoSemValorDoubleException(String s) {
        super(s);
    }
}
