package br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.pojo;

import br.com.ufms.eprontuarioapi.domain.itemdocumento.enumeration.ETipoItem;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.pojo.ModeloDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.entity.ModeloItemDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModeloItemDocumentoPojo {

    private Long id;
    private ETipoItem tipo;
    private ModeloDocumentoPojo modeloDocumento;
    private String titulo;
    private String descricao;
    private Long idItemPai;
    private String valorFixoChave;
    private Boolean isItemDocumentoObrigatorio;
    private Integer qtdMaximaRegistros;

    public ModeloItemDocumento gerarModeloItemDocumento() {
        return ModeloItemDocumento.ModeloItemDocumentoBuilder()
                .id(this.id)
                .valorFixoChave(this.valorFixoChave)
                .idItemPai(this.idItemPai)
                .tipo(this.tipo)
                .isItemDocumentoObrigatorio(this.isItemDocumentoObrigatorio)
                .qtdMaximaRegistros(this.qtdMaximaRegistros)
                .titulo(this.titulo)
                .descricao(this.descricao)
                .build();
    }

    public static ModeloItemDocumentoPojo gerarModeloItemDocumentoPojo(ModeloItemDocumento controle) {
        return new ModeloItemDocumentoPojoBuilder()
                .id(controle.getId())
                .valorFixoChave(controle.getValorFixoChave())
                .idItemPai(controle.getIdItemPai())
                .tipo(controle.getTipo())
                .isItemDocumentoObrigatorio(controle.getIsItemDocumentoObrigatorio())
                .qtdMaximaRegistros(controle.getQtdMaximaRegistros())
                .titulo(controle.getTitulo())
                .descricao(controle.getDescricao())
                .build();
    }

}
