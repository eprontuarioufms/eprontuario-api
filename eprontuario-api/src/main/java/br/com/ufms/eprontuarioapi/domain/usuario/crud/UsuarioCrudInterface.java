package br.com.ufms.eprontuarioapi.domain.usuario.crud;

import br.com.ufms.eprontuarioapi.domain.generic.crud.GenericCrudInterface;
import br.com.ufms.eprontuarioapi.domain.usuario.entity.Usuario;

public interface UsuarioCrudInterface extends GenericCrudInterface<Usuario, Long> {

    Usuario buscarUsuarioPorIdPessoa(Long idPessoa);
}
