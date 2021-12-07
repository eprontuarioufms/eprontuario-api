package br.com.ufms.eprontuarioapi.infra.repository.usuario.implementacao;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.usuario.crud.UsuarioCrudInterface;
import br.com.ufms.eprontuarioapi.domain.usuario.entity.Usuario;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNulaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.usuario.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class UsuarioCrudImpl implements UsuarioCrudInterface {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvarEntidade(Usuario entidade) throws GenericRuntimeException {
        validarUsuario(entidade);
        return usuarioRepository.save(entidade);
    }

    @Override
    public Usuario editarEntidade(Long idEntidade, Usuario entidade) throws GenericRuntimeException {
        validarIdUsuario(idEntidade);
        validarUsuario(entidade);
        return usuarioRepository.save(entidade);
    }

    @Override
    public Optional<Usuario> buscarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdUsuario(idEntidade);
        return usuarioRepository.findById(idEntidade);
    }

    @Override
    public Page<Usuario> buscarTodasEntidades(Pageable paginacao) {
        return usuarioRepository.findAll(paginacao);
    }

    @Override
    public List<Usuario> buscarTodasEntidades() {
        return usuarioRepository.findAll();
    }

    @Override
    public void deletarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdUsuario(idEntidade);
        usuarioRepository.deleteById(idEntidade);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade) {
        return usuarioRepository.existsById(idEntidade);
    }

    private void validarUsuario(Usuario entidade) throws GenericRuntimeException {
        if (isNull(entidade)) {
            throw new EntidadeNulaInfraException("não pode ser salvo Usuario Nulo.");
        }
    }

    private void validarIdUsuario(Long idEntidade) throws GenericRuntimeException {
        if (!usuarioRepository.existsById(idEntidade)) {
            throw new EntidadeNaoEncontradaInfraException("Usuario não encontrado.");
        }
    }

    @Override
    public Usuario buscarUsuarioPorIdPessoa(Long idPessoa) {
        return usuarioRepository.findByPessoa_id(idPessoa);
    }
}
