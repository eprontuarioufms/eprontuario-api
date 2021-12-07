package br.com.ufms.eprontuarioapi.domain.pessoa.entity;

import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
import br.com.ufms.eprontuarioapi.domain.pessoa.enumeration.ETipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PESSOA")
@SequenceGenerator(name = "seq_pessoa", allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "PES_TIPO", length = 10)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "PES_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "PES_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "PES_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "PES_USUARIO_ALTERACAO"))
})
public class Pessoa extends GenericEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PES_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PES_NOME")
    private String nome;

    @Column(name = "PES_CPF")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "PES_TIPO", insertable = false, updatable = false, length = 12)
    private ETipoPessoa tipoPessoa;

    @Builder(builderMethodName = "PessoaBuilder")
    public Pessoa(Long id,
                  String nome,
                  String cpf,
                  Date dataCadastro,
                  Date dataAlteracao,
                  String usuarioCadastro,
                  String usuarioAlteracao
    ) {

        super(dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

}
