package br.com.ufms.eprontuarioapi.domain.utils.validador.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidadorEProntuarioException extends RuntimeException {
    public ValidadorEProntuarioException(String s) {
        super(s);
    }
}
