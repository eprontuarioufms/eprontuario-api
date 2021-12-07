package br.com.ufms.eprontuarioapi.domain.disciplina.pojo;

import br.com.ufms.eprontuarioapi.domain.disciplina.entity.Disciplina;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.pojo.ModeloDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.professor.pojo.ProfessorPojo;
import br.com.ufms.eprontuarioapi.domain.utils.enumerations.ESituacao;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DisciplinaPojo {
    private Long id;
    private String titulo;
    private String descricao;
    private List<ModeloDocumentoPojo> modelosDocumentosPojo;
    private ProfessorPojo professorPojo;
    private ESituacao situacao;


    public Disciplina gerarDiciplina() {
        return Disciplina.DiciplinaBuilder()
                .id(this.id)
                .titulo(this.titulo)
                .descricao(this.descricao)
                .modelosDocumento(ModeloDocumentoPojo.gerarModelosDocumentos(this.modelosDocumentosPojo))
                .professor(this.professorPojo.gerarProfessor())
                .situacao(this.situacao)
                .build();
    }

    public static DisciplinaPojo gerarDisciplinaPojo(Disciplina disciplina) {
        return new DisciplinaPojo.DisciplinaPojoBuilder()
                .id(disciplina.getId())
                .titulo(disciplina.getTitulo())
                .descricao(disciplina.getDescricao())
                .modelosDocumentosPojo(ModeloDocumentoPojo.gerarModelosDocumentosPojo(disciplina.getModelosDocumento()))
                .professorPojo(ProfessorPojo.gerarProfessorPojo(disciplina.getProfessor()))
                .situacao(disciplina.getSituacao())
                .build();
    }

}
