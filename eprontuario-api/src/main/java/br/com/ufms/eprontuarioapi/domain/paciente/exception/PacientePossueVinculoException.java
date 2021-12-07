package br.com.ufms.eprontuarioapi.domain.paciente.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PacientePossueVinculoException extends RuntimeException {
    public PacientePossueVinculoException(String s) {
        super(s);
    }
}
