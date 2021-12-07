package br.com.ufms.eprontuarioapi.domain.matricula.pojo;

import br.com.ufms.eprontuarioapi.domain.academico.entity.Academico;
import br.com.ufms.eprontuarioapi.domain.disciplina.entity.Disciplina;
import br.com.ufms.eprontuarioapi.domain.matricula.entity.Matricula;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatriculaPojo {

    private Long id;
    private Academico academico;
    private Disciplina disciplina;

    public Matricula gerarMatricula() {
        return Matricula.MatriculaBuilder()
                .id(this.id)
                .academico(this.academico)
                .disciplina(this.disciplina)
                .build();
    }

    public static MatriculaPojo gerarMatriculaPojo(Matricula matricula) {
        return new MatriculaPojo.MatriculaPojoBuilder()
                .id(matricula.getId())
                .academico(matricula.getAcademico())
                .disciplina(matricula.getDisciplina())
                .build();
    }

}
