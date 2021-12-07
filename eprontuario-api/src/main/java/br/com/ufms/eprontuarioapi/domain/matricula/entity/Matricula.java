package br.com.ufms.eprontuarioapi.domain.matricula.entity;


import br.com.ufms.eprontuarioapi.domain.academico.entity.Academico;
import br.com.ufms.eprontuarioapi.domain.disciplina.entity.Disciplina;
import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_MATRICULA")
@SequenceGenerator(name = "seq_matricula", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "MA_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "MA_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "MA_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "MA_USUARIO_ALTERACAO"))
})
public class Matricula extends GenericEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MA_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "AC_ID")
    private Academico academico;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "DI_ID")
    private Disciplina disciplina;

    @Builder(builderMethodName = "MatriculaBuilder")
    public Matricula(
            Long id,
            Academico academico,
            Disciplina disciplina,
            Date dataCadastro,
            Date dataAlteracao,
            String usuarioCadastro,
            String usuarioAlteracao
    ) {
        super(dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);

        this.id = id;
        this.academico = academico;
        this.disciplina = disciplina;
    }
}
