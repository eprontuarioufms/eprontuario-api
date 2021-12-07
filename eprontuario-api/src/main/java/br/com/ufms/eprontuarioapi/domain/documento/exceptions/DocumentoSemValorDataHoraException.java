package br.com.ufms.eprontuarioapi.domain.documento.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DocumentoSemValorDataHoraException extends RuntimeException {
    public DocumentoSemValorDataHoraException(String s) {
        super(s);
    }
}
