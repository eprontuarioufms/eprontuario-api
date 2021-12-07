package br.com.ufms.eprontuarioapi.domain.disciplina.exception;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;

public class DiciplinaPossuiInformacoesInvalidaException extends GenericRuntimeException {
    public DiciplinaPossuiInformacoesInvalidaException(String s) {
        super(s);
    }
}
