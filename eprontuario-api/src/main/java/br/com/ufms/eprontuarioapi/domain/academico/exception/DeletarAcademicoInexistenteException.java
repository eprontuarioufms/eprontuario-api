package br.com.ufms.eprontuarioapi.domain.academico.exception;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;

public class DeletarAcademicoInexistenteException extends GenericRuntimeException {
    public DeletarAcademicoInexistenteException(String s) {
        super(s);
    }
}
