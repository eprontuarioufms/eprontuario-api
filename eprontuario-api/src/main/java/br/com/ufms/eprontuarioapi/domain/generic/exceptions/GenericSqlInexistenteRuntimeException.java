package br.com.ufms.eprontuarioapi.domain.generic.exceptions;

public class GenericSqlInexistenteRuntimeException extends RuntimeException {

    public GenericSqlInexistenteRuntimeException(String s) {
        super(s);
    }
}
