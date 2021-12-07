package br.com.ufms.eprontuarioapi.domain.permissaomodelodocumento.entity;

import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.entity.ModeloDocumento;
import br.com.ufms.eprontuarioapi.domain.permissaomodelodocumento.enumeration.ETipoPermissao;
import br.com.ufms.eprontuarioapi.domain.professor.entity.Professor;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_PERMISSAO_MODELO_DOC")
@SequenceGenerator(name = "seq_item_documento", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "PMD_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "PMD_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "PMD_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "PMD_USUARIO_ALTERACAO"))
})
public class PermissaoModeloDocumento extends GenericEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PMD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MD_ID")
    private ModeloDocumento modeloDocumento;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PE_ID")
    private Professor professor;

    @Enumerated(EnumType.STRING)
    @Column(name = "PMD_TIPO_PERMISSAO")
    private ETipoPermissao tipoPermissao;

}
