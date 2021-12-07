package br.com.ufms.eprontuarioapi.domain.valorfixo.pojo;

import br.com.ufms.eprontuarioapi.domain.valorfixo.entity.ValorFixo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValorFixoPojo {

    private Long id;
    private String chave;
    private String valor;

    public ValorFixo gerarValorFixo() {
        return ValorFixo.ValorFixoBuilder()
                .id(this.id)
                .chave(this.chave)
                .valor(this.valor)
                .build();
    }

    public static ValorFixoPojo gerarValorFixoPojo(ValorFixo valorFixo) {
        return new ValorFixoPojo.ValorFixoPojoBuilder()
                .id(valorFixo.getId())
                .chave(valorFixo.getChave())
                .valor(valorFixo.getValor())
                .build();
    }
}
