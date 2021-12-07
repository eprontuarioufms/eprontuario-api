package br.com.ufms.eprontuarioapi.domain.professor.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProfessorNaoExisteExceprion  extends RuntimeException{
    public ProfessorNaoExisteExceprion(String s) {
    }
}
