package br.com.ufms.eprontuarioapi.infra.repository.exceptions;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;

public class EntidadeNaoEncontradaInfraException extends GenericRuntimeException {
    public EntidadeNaoEncontradaInfraException(String s) {
        super(s);
    }
}
