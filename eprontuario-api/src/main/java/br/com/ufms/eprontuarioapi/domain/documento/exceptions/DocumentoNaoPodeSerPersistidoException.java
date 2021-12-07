package br.com.ufms.eprontuarioapi.domain.documento.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DocumentoNaoPodeSerPersistidoException extends RuntimeException {
    public DocumentoNaoPodeSerPersistidoException(String s) {
        super(s);
    }
}
