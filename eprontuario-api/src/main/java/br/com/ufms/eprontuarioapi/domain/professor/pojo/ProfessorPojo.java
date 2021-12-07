package br.com.ufms.eprontuarioapi.domain.professor.pojo;


import br.com.ufms.eprontuarioapi.domain.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorPojo {

    private Long id;
    private String nome;
    private String siap;

    public Professor gerarProfessor() {
        return Professor.ProfessorBuilder()
                .id(this.id)
                .nome(this.nome)
                .siap(this.siap)
                .build();
    }

    public static ProfessorPojo gerarProfessorPojo(Professor professor) {
        return new ProfessorPojo.ProfessorPojoBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .siap(professor.getSiap())
                .build();
    }
}
