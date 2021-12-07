package br.com.ufms.eprontuarioapi.domain.disciplina.entity;


import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.entity.ModeloDocumento;
import br.com.ufms.eprontuarioapi.domain.professor.entity.Professor;
import br.com.ufms.eprontuarioapi.domain.utils.enumerations.ESituacao;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_DICIPLINA")
@SequenceGenerator(name = "seq_diciplina", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "DI_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "DI_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "DI_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "DI_USUARIO_ALTERACAO"))
})
public class Disciplina extends GenericEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DI_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DI_TITULO")
    private String titulo;

    @Column(name = "DI_DESCRICAO")
    private String descricao;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "MD_ID")
    private List<ModeloDocumento> modelosDocumento;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PE_ID")
    private Professor professor;

    @Enumerated(EnumType.STRING)
    @Column(name = "DI_SITUACAO")
    private ESituacao situacao;


    @Builder(builderMethodName = "DiciplinaBuilder")
    public Disciplina(Long id,
                      String titulo,
                      String descricao,
                      List<ModeloDocumento> modelosDocumento,
                      Professor professor,
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
        this.modelosDocumento = modelosDocumento;
        this.professor = professor;
        this.situacao = situacao;
    }
}

