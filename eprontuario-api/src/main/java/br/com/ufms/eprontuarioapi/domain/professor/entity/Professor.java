package br.com.ufms.eprontuarioapi.domain.professor.entity;

import br.com.ufms.eprontuarioapi.domain.pessoa.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PROFESSOR")
@DiscriminatorValue("PROFESSOR")
@PrimaryKeyJoinColumn(name = "PES_ID")
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "PRO_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "PRO_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "PRO_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "PRO_USUARIO_ALTERACAO"))
})
public class Professor extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(name = "PRO_SIAP")
    private String siap;

    @Builder(builderMethodName = "ProfessorBuilder")
    public Professor(Long id,
                     String nome,
                     String cpf,
                     String siap,
                     Date dataCadastro,
                     Date dataAlteracao,
                     String usuarioCadastro,
                     String usuarioAlteracao
    ) {
        super(id, nome, cpf, dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);
        this.siap = siap;
    }
}
