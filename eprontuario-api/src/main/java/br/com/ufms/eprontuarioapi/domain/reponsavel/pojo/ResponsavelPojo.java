package br.com.ufms.eprontuarioapi.domain.reponsavel.pojo;


import br.com.ufms.eprontuarioapi.domain.reponsavel.entity.Responsavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static java.util.Objects.nonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsavelPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String contato;


    public Responsavel gerarResponsavel() {
        return Responsavel.ResponsavelBuilder()
                .id(this.id)
                .nome(this.nome)
                .contato(this.contato)
                .build();
    }

    public static ResponsavelPojo gerarResponsavelPojo(Responsavel responsavel) {
        if (nonNull(responsavel)) {
            return new ResponsavelPojo.ResponsavelPojoBuilder()
                    .id(responsavel.getId())
                    .nome(responsavel.getNome())
                    .contato(responsavel.getContato())
                    .build();
        } else {
            return null;
        }
    }
}
