package br.com.ufms.eprontuarioapi.domain.documento.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DocumentoSemValorInteiroException extends RuntimeException {
    public DocumentoSemValorInteiroException(String s) {
        super(s);
    }
}
