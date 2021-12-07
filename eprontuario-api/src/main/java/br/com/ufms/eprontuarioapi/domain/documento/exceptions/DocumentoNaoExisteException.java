package br.com.ufms.eprontuarioapi.domain.documento.exceptions;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;

public class DocumentoNaoExisteException extends GenericRuntimeException {
    public DocumentoNaoExisteException(String s) {
        super(s);
    }
}
