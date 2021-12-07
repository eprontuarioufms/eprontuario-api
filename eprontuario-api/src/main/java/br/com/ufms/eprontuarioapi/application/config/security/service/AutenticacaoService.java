package br.com.ufms.eprontuarioapi.application.config.security.service;

import br.com.ufms.eprontuarioapi.domain.usuario.entity.Usuario;
import br.com.ufms.eprontuarioapi.infra.repository.usuario.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);

        if (usuario.isPresent()){
             return usuario.get();
        }

        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
