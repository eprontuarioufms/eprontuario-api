package br.com.ufms.eprontuarioapi.domain.documento.pojo;


import br.com.ufms.eprontuarioapi.domain.documento.entity.Documento;
import br.com.ufms.eprontuarioapi.domain.academico.pojo.AcademicoPojo;
import br.com.ufms.eprontuarioapi.domain.entrada.pojo.EntradaPojo;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.pojo.ItemDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.paciente.pojo.PacientePojo;
import br.com.ufms.eprontuarioapi.domain.reponsavel.entity.Responsavel;
import br.com.ufms.eprontuarioapi.domain.utils.validador.Validator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private AcademicoPojo academico;
    private PacientePojo paciente;
    private EntradaPojo entrada;
    private String titulo;
    private String descricao;

    private List<ItemDocumentoPojo> itensDocumento;

    private Responsavel responsavel;


    public Documento gerarDocumento() {

        validarPojoAntesDeGerarDocumento();

        return Documento.DocumentoBuilder()
                .id(this.id)
                .academico(this.academico.gerarAcademico())
                .paciente(this.paciente.gerarPaciente())
                .entrada(this.entrada.gerarEntrada())
                .titulo(this.titulo)
                .descricao(this.descricao)
                .build();
    }

    public static DocumentoPojo gerarDocumentoPojo(Documento documento) {

        return new DocumentoPojo.DocumentoPojoBuilder()
                .id(documento.getId())
                .academico(AcademicoPojo.gerarAcademicoPojo(documento.getAcademico()))
                .paciente(PacientePojo.gerarPacientePojo(documento.getPaciente()))
                .entrada(EntradaPojo.gerarEntradaPojo(documento.getEntrada()))
                .titulo(documento.getTitulo())
                .descricao(documento.getDescricao())
                .build();
    }

    private void validarPojoAntesDeGerarDocumento() {
        Validator.of(this)
                .validate(DocumentoPojo::getPaciente, Objects::nonNull, "Para gerar o Documento o Paciente deve ser informado.")
                .validate(DocumentoPojo::getAcademico, Objects::nonNull, "Para gerar o Documento o Academico deve ser informado.")
                .validate(DocumentoPojo::getEntrada, Objects::nonNull, "Para gerar o Documento a Entrada deve ser informado.")
                .validate(DocumentoPojo::getTitulo, Objects::nonNull, "Para gerar o Documento o Título  deve ser informado.")
                .get();
    }

}
