package br.com.ufms.eprontuarioapi.domain.documento.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DocumentoSemPacienteException extends RuntimeException {
    public DocumentoSemPacienteException(String s) {
        super(s);
    }
}
