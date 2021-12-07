package br.com.ufms.eprontuarioapi.domain.academico.entity;


import br.com.ufms.eprontuarioapi.domain.pessoa.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ACADEMICO")
@DiscriminatorValue("ACADEMICO")
@PrimaryKeyJoinColumn(name = "PES_ID")
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "AC_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "AC_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "AC_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "AC_USUARIO_ALTERACAO"))
})
public class Academico extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(name = "AC_RGA")
    private String rga;

    @Builder(builderMethodName = "AcademicoBuilder")
    public Academico(Long id,
                     String nome,
                     String cpf,
                     String rga,
                     Date dataCadastro,
                     Date dataAlteracao,
                     String usuarioCadastro,
                     String usuarioAlteracao
    ) {
        super(id, nome, cpf, dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);
        this.rga = rga;
    }
}


