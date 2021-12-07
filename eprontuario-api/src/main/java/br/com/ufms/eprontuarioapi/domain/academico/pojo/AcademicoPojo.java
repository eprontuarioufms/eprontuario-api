package br.com.ufms.eprontuarioapi.domain.academico.pojo;

import br.com.ufms.eprontuarioapi.domain.academico.entity.Academico;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcademicoPojo {

    private Long id;
    private String nome;
    private String rga;

    public Academico gerarAcademico() {
        return Academico.AcademicoBuilder()
                .id(this.id)
                .nome(this.nome)
                .rga(this.rga)
                .build();
    }

    public static AcademicoPojo gerarAcademicoPojo(Academico academico) {
        return new AcademicoPojo.AcademicoPojoBuilder()
                .id(academico.getId())
                .nome(academico.getNome())
                .rga(academico.getRga())
                .build();
    }
}
