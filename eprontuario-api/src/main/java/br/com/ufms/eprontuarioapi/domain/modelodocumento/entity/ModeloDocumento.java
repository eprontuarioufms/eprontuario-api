package br.com.ufms.eprontuarioapi.domain.modelodocumento.entity;


import br.com.ufms.eprontuarioapi.domain.documento.enumeration.EStatus;
import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
import br.com.ufms.eprontuarioapi.domain.utils.enumerations.ESituacao;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_MODELO_DOCUMENTO")
@SequenceGenerator(name = "seq_modelo_documento", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "MD_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "MD_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "MD_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "MD_USUARIO_ALTERACAO"))
})
public class ModeloDocumento extends GenericEntity<Long> {

    @Id
    @Column(name = "MD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MD_TITULO")
    private String titulo;

    @Column(name = "MD_DESCRICAO")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "MD_STATUS")
    private EStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "MD_SITUACAO")
    private ESituacao situacao;


    @Builder(builderMethodName = "ModeloDocumentoBuilder")
    public ModeloDocumento(Long id,
                           String titulo,
                           String descricao,
                           EStatus status,
                           ESituacao situacao,
                           Date dataCadastro,
                           Date dataAlteracao,
                           String usuarioCadastro,
                           String usuarioAlteracao
    ) {

        super(dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.situacao = situacao;
    }
}
