package br.com.ufms.eprontuarioapi.domain.academico.exception;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;

public class DeletarAcademicoVinculadoAUmDocumentoException extends GenericRuntimeException {
    public DeletarAcademicoVinculadoAUmDocumentoException(String s) {
        super(s);
    }
}
