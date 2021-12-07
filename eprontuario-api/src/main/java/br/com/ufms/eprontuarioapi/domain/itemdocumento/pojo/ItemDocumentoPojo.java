package br.com.ufms.eprontuarioapi.domain.itemdocumento.pojo;

import br.com.ufms.eprontuarioapi.domain.documento.entity.Documento;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.entity.ItemDocumento;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.enumeration.ETipoItem;
import br.com.ufms.eprontuarioapi.domain.utils.enumerations.ESituacao;
import br.com.ufms.eprontuarioapi.domain.valorfixo.entity.ValorFixo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDocumentoPojo {

    private Long id;
    private Documento documento;
    private ValorFixo valorFixo;
    private Long idPai;
    private String valorTexto;
    private Date valorData;
    private Double valorFlutuante;
    private Integer valorInteiro;
    private String titulo;
    private ESituacao situacao;
    private ETipoItem tipo;


    public ItemDocumento gerarItemDocumento() {
        return ItemDocumento.ItemDocumentoBuilder()
                .id(this.id)
                .documento(this.documento)
                .idPai(this.idPai)
                .valorTexto(this.valorTexto)
                .valorData(this.valorData)
                .valorFlutuante(this.valorFlutuante)
                .valorInteiro(this.valorInteiro)
                .titulo(this.titulo)
                .situacao(this.situacao)
                .tipo(this.tipo)
                .build();
    }

    public static ItemDocumentoPojo gerarItemDocumentoPojo(ItemDocumento itemDocumento) {
        return new ItemDocumentoPojoBuilder()
                .id(itemDocumento.getId())
                .documento(Documento.DocumentoBuilder().id(itemDocumento.getDocumento().getId()).build())
                .idPai(itemDocumento.getIdPai())
                .valorTexto(itemDocumento.getValorTexto())
                .valorData(itemDocumento.getValorData())
                .valorFlutuante(itemDocumento.getValorFlutuante())
                .valorInteiro(itemDocumento.getValorInteiro())
                .titulo(itemDocumento.getTitulo())
                .situacao(itemDocumento.getSituacao())
                .tipo(itemDocumento.getTipo())
                .build();
    }

}
