package br.com.ufms.eprontuarioapi.domain.entrada.exception;

public class EntradaNaoExisteException extends RuntimeException {
    public EntradaNaoExisteException(String s) {
        super(s);
    }
}