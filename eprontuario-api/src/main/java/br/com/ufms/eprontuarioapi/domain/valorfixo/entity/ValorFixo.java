package br.com.ufms.eprontuarioapi.domain.valorfixo.entity;

import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "VF_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "VF_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "VF_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "VF_USUARIO_ALTERACAO"))
})
@Table(name = "TB_VALOR_FIXO")
@SequenceGenerator(name = "seq_valor_fixo", allocationSize = 1)
public class ValorFixo extends GenericEntity<Long> {

    @Id
    @Column(name = "VF_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "VF_CHAVE")
    private String chave;

    @Column(name = "VF_VALOR")
    private String valor;

    @Builder(builderMethodName = "ValorFixoBuilder")
    public ValorFixo(Long id,
                     String chave,
                     String valor,
                     Date dataCadastro,
                     Date dataAlteracao,
                     String usuarioCadastro,
                     String usuarioAlteracao
    ) {
        super(dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);

        this.id = id;
        this.chave = chave;
        this.valor = valor;
    }
}
