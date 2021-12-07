package br.com.ufms.eprontuarioapi.domain.documento.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DocumentoSemTextoException extends RuntimeException {
    public DocumentoSemTextoException(String s) {
        super(s);
    }
}
