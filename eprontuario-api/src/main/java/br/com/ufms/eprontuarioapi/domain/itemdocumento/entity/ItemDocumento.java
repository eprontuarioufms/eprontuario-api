package br.com.ufms.eprontuarioapi.domain.itemdocumento.entity;

import br.com.ufms.eprontuarioapi.domain.documento.entity.Documento;
import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.enumeration.ETipoItem;
import br.com.ufms.eprontuarioapi.domain.utils.enumerations.ESituacao;
import br.com.ufms.eprontuarioapi.domain.valorfixo.entity.ValorFixo;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_ITEM_DOCUMENTO")
@SequenceGenerator(name = "seq_item_documento", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "ITE_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "ITE_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "ITE_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "ITE_USUARIO_ALTERACAO"))
})
public class ItemDocumento extends GenericEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ITE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "DOC_ID")
    private Documento documento;

    @Enumerated(EnumType.STRING)
    @Column(name = "ITE_SITUACAO")
    private ESituacao situacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "ITE_TIPO_ITEM")
    private ETipoItem tipo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VF_ID")
    private ValorFixo valorFixo;

    @Column(name = "ITE_ID_PAI")
    private Long idPai;

    @Column(name = "ITE_VALOR_TEXTO")
    private String valorTexto;

    @Column(name = "ITE_VALOR_DATA_HORA")
    private Date valorData;

    @Column(name = "ITE_VALOR_FLUTUANTE")
    private Double valorFlutuante;

    @Column(name = "ITE_VALOR_INTEGER")
    private Integer valorInteiro;

    @Column(name = "ITE_TITULO")
    private String titulo;

    @Builder(builderMethodName = "ItemDocumentoBuilder")
    public ItemDocumento(
            Long id,
            Documento documento,
            Long idPai,
            String valorTexto,
            Date valorData,
            Double valorFlutuante,
            Integer valorInteiro,
            String titulo,
            ESituacao situacao,
            ETipoItem tipo,
            Date dataCadastro,
            Date dataAlteracao,
            String usuarioCadastro,
            String usuarioAlteracao
    ) {
        super(dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);

        this.id = id;
        this.documento = documento;
        this.idPai = idPai;
        this.valorTexto = valorTexto;
        this.valorData = valorData;
        this.valorFlutuante = valorFlutuante;
        this.valorInteiro = valorInteiro;
        this.titulo = titulo;
        this.situacao = situacao;
        this.tipo = tipo;
    }
}