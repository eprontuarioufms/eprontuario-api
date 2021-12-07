package br.com.ufms.eprontuarioapi.infra.repository.usuario.repositorio;

import br.com.ufms.eprontuarioapi.domain.usuario.entity.Usuario;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

    Usuario findByPessoa_id(Long idProfessor);
}
