package br.com.ufms.eprontuarioapi.domain.modelodocumento.pojo;

import br.com.ufms.eprontuarioapi.domain.documento.enumeration.EStatus;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.entity.ModeloDocumento;
import br.com.ufms.eprontuarioapi.domain.utils.enumerations.ESituacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModeloDocumentoPojo {

    private Long id;
    private String titulo;
    private String descricao;
    private ESituacao situacao;
    private EStatus status;

    public ModeloDocumento gerarModeloDocumento() {
        return ModeloDocumento.ModeloDocumentoBuilder()
                .id(this.id)
                .titulo(this.titulo)
                .descricao(this.descricao)
                .situacao(this.situacao)
                .status(this.status)
                .build();
    }

    public static ModeloDocumentoPojo gerarModeloDocumentoPojo(ModeloDocumento modelo) {
        return new ModeloDocumentoPojoBuilder()
                .id(modelo.getId())
                .titulo(modelo.getTitulo())
                .descricao(modelo.getDescricao())
                .situacao(modelo.getSituacao())
                .status(modelo.getStatus())
                .build();
    }

    public static List<ModeloDocumento> gerarModelosDocumentos(List<ModeloDocumentoPojo> modelosDocumentosPojo) {
        return modelosDocumentosPojo.stream().map(ModeloDocumentoPojo::gerarModeloDocumento).collect(Collectors.toList());
    }

    public static List<ModeloDocumentoPojo> gerarModelosDocumentosPojo(List<ModeloDocumento> modelosDocumentos) {
        return modelosDocumentos.stream().map(ModeloDocumentoPojo::gerarModeloDocumentoPojo).collect(Collectors.toList());
    }

}
