package br.com.ufms.eprontuarioapi.domain.paciente.entity;


import br.com.ufms.eprontuarioapi.domain.paciente.enumerations.EGenero;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PACIENTE")
@DiscriminatorValue("PACIENTE")
@PrimaryKeyJoinColumn(name = "PES_ID")
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "PAC_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "PAC_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "PAC_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "PAC_USUARIO_ALTERACAO"))
})
public class Paciente extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAC_GENERO")
    private EGenero genero;


    @Builder(builderMethodName = "PacienteBuilder")
    public Paciente(Long id,
                   String nome,
                   String cpf,
                   EGenero genero,
                   Date dataCadastro,
                   Date dataAlteracao,
                   String usuarioCadastro,
                   String usuarioAlteracao
    ) {

        super(id, nome, cpf, dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);

        this.genero = genero;
    }

}
