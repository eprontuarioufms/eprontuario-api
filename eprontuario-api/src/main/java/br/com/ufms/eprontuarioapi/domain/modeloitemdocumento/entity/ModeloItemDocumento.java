package br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.entity;

import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.enumeration.ETipoItem;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.entity.ModeloDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_MODELO_ITEM_DOC")
@SequenceGenerator(name = "seq_modelo_item_documento", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "MID_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "MID_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "MID_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "MID_USUARIO_ALTERACAO"))
})
public class ModeloItemDocumento extends GenericEntity<Long> {

    @Id
    @Column(name = "MID_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "MID_TIPO")
    private ETipoItem tipo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MD_ID")
    private ModeloDocumento modeloDocumento;

    @Column(name = "MID_TITULO")
    private String titulo;

    @Column(name = "MID_DESCRICAO")
    private String descricao;

    @Column(name = "MID_ID_ITEM_PAI")
    private Long idItemPai;

    @Column(name = "MID_VF_CHAVE")
    private String valorFixoChave;

    @Column(name = "MID_IS_OBRIGATORIO")
    private Boolean isItemDocumentoObrigatorio;

    @Column(name = "MID_QTD_MAX_REGISTRO")
    private Integer qtdMaximaRegistros;


    @Builder(builderMethodName = "ModeloItemDocumentoBuilder")
    public ModeloItemDocumento(Long id,
                               String valorFixoChave,
                               Long idItemPai,
                               ModeloDocumento modeloDocumento,
                               ETipoItem tipo,
                               Boolean isItemDocumentoObrigatorio,
                               Integer qtdMaximaRegistros,
                               String titulo,
                               String descricao,
                               Date dataCadastro,
                               Date dataAlteracao,
                               String usuarioCadastro,
                               String usuarioAlteracao
    ) {

        super(dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);

        this.id = id;
        this.valorFixoChave = valorFixoChave;
        this.idItemPai = idItemPai;
        this.modeloDocumento = modeloDocumento;
        this.tipo = tipo;
        this.isItemDocumentoObrigatorio = isItemDocumentoObrigatorio;
        this.qtdMaximaRegistros = qtdMaximaRegistros;
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
