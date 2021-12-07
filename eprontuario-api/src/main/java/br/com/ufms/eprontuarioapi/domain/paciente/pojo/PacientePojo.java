package br.com.ufms.eprontuarioapi.domain.paciente.pojo;

import br.com.ufms.eprontuarioapi.domain.paciente.entity.Paciente;
import br.com.ufms.eprontuarioapi.domain.paciente.enumerations.EGenero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacientePojo {

    private Long id;
    private String nome;
    private String cpf;
    private EGenero genero;

    public Paciente gerarPaciente() {
        return Paciente.PacienteBuilder()
                .id(this.id)
                .nome(this.nome)
                .cpf(this.cpf)
                .genero(this.genero)
                .build();
    }

    public static PacientePojo gerarPacientePojo(Paciente paciente) {
        return new PacientePojo.PacientePojoBuilder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .cpf(paciente.getCpf())
                .genero(paciente.getGenero())
                .build();
    }
}
