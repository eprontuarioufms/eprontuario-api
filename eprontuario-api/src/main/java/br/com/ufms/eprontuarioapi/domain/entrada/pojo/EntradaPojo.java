package br.com.ufms.eprontuarioapi.domain.entrada.pojo;

import br.com.ufms.eprontuarioapi.domain.entrada.entity.Entrada;
import br.com.ufms.eprontuarioapi.domain.paciente.pojo.PacientePojo;
import br.com.ufms.eprontuarioapi.domain.reponsavel.pojo.ResponsavelPojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static java.util.Objects.nonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntradaPojo {
    private Long id;
    private PacientePojo paciente;
    private ResponsavelPojo responsavel;
    private Date dataHora;

    public Entrada gerarEntrada() {
        return Entrada.EntradaBuilder()
                .id(this.id)
                .paciente(this.paciente.gerarPaciente())
                .responsavel(nonNull(this.responsavel) ? this.responsavel.gerarResponsavel() : null)
                .dataHora(this.dataHora)
                .build();
    }

    public static EntradaPojo gerarEntradaPojo(Entrada entrada) {
        return new EntradaPojo.EntradaPojoBuilder()
                .id(entrada.getId())
                .paciente(PacientePojo.gerarPacientePojo(entrada.getPaciente()))
                .responsavel(ResponsavelPojo.gerarResponsavelPojo(entrada.getResponsavel()))
                .dataHora(entrada.getDataHora())
                .build();
    }

}
