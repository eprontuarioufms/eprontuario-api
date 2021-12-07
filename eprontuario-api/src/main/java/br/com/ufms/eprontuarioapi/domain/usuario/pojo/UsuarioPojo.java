package br.com.ufms.eprontuarioapi.domain.usuario.pojo;

import br.com.ufms.eprontuarioapi.domain.perfil.entity.Perfil;
import br.com.ufms.eprontuarioapi.domain.pessoa.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPojo {
    private Long id;
    private String nome;
    private Pessoa pessoa;
    private String login;
    private String email;
    private String senha;
    private List<Perfil> perfis;

}
