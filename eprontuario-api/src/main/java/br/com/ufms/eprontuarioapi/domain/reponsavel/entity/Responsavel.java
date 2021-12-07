package br.com.ufms.eprontuarioapi.domain.reponsavel.entity;

import br.com.ufms.eprontuarioapi.domain.pessoa.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_RESPONSAVEL")
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "RES_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "RES_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "RES_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "RES_USUARIO_ALTERACAO"))
})
public class Responsavel extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(name = "RES_CONTATO")
    private String contato;

    @Builder(builderMethodName = "ResponsavelBuilder")
    public Responsavel(Long id,
                       String nome,
                       String cpf,
                       String contato,
                       Date dataCadastro,
                       Date dataAlteracao,
                       String usuarioCadastro,
                       String usuarioAlteracao
    ) {

        super(id, nome, cpf, dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);
        this.contato = contato;
    }


}
