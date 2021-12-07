package br.com.ufms.eprontuarioapi.domain.paciente.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PacienteNaoExisteException extends RuntimeException {
    public PacienteNaoExisteException(String s) {
        super(s);
    }
}
