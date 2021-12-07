package br.com.ufms.eprontuarioapi.domain.documento.entity;


import br.com.ufms.eprontuarioapi.domain.academico.entity.Academico;
import br.com.ufms.eprontuarioapi.domain.entrada.entity.Entrada;
import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
import br.com.ufms.eprontuarioapi.domain.paciente.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Table(name = "TB_DOCUMENTO")
@SequenceGenerator(name = "seq_documento", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "DOC_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "DOC_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "DOC_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "DOC_USUARIO_ALTERACAO"))
})
public class Documento extends GenericEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DOC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENF_ID")
    private Academico academico;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAC_ID")
    private Paciente paciente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENT_ID")
    private Entrada entrada;

    @Column(name = "DOC_TITULO")
    private String titulo;

    @Column(name = "DOC_DESCRICAO")
    private String descricao;

    public Documento() {
        super();
    }

    @Builder(builderMethodName = "DocumentoBuilder")
    public Documento(Long id,
                     String titulo,
                     String descricao,
                     Academico academico,
                     Paciente paciente,
                     Entrada entrada,
                     Date dataCadastro,
                     Date dataAlteracao,
                     String usuarioCadastro,
                     String usuarioAlteracao
    ) {

        super(dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);

        this.id = id;
        this.academico = academico;
        this.paciente = paciente;
        this.entrada = entrada;
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
