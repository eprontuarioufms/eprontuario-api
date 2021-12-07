package br.com.ufms.eprontuarioapi.domain.generic.exceptions;

public class GenericSqlRuntimeException extends Throwable {

    public GenericSqlRuntimeException(String s, Exception e) {
        super(s, e);
    }

    public GenericSqlRuntimeException(String s) {
        super(s);
    }

    public GenericSqlRuntimeException(Exception e) {
        super(e);
    }
}
