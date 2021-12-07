package br.com.ufms.eprontuarioapi.infra.repository.exceptions;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;

public class EntidadeNulaInfraException extends GenericRuntimeException {
    public EntidadeNulaInfraException(String s) {
        super(s);
    }
}
