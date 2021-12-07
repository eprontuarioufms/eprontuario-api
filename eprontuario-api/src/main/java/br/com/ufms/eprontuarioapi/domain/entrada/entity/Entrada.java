package br.com.ufms.eprontuarioapi.domain.entrada.entity;


import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
import br.com.ufms.eprontuarioapi.domain.paciente.entity.Paciente;
import br.com.ufms.eprontuarioapi.domain.reponsavel.entity.Responsavel;
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
@NoArgsConstructor
@Table(name = "TB_ENTRADA")
@SequenceGenerator(name = "seq_entrada", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "ENT_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "ENT_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "ENT_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "ENT_USUARIO_ALTERACAO"))
})
public class Entrada extends GenericEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENT_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAC_ID")
    private Paciente paciente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RES_ID")
    private Responsavel responsavel;

    @Column(name = "ENT_DATA_HORA")
    private Date dataHora;

    @Column(name = "ENT_DESCRICAO")
    private String descricao;


    @Builder(builderMethodName = "EntradaBuilder")
    public Entrada(Long id,
                   Paciente paciente,
                   Responsavel responsavel,
                   String descricao,
                   Date dataCadastro,
                   Date dataAlteracao,
                   String usuarioCadastro,
                   String usuarioAlteracao,
                   Date dataHora
    ) {

        super(dataCadastro, dataAlteracao, usuarioCadastro, usuarioAlteracao);

        this.id = id;
        this.paciente = paciente;
        this.responsavel = responsavel;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

}
