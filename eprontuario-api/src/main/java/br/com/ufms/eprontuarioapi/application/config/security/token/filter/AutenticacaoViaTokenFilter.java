package br.com.ufms.eprontuarioapi.application.config.security.token.filter;

import br.com.ufms.eprontuarioapi.application.config.security.token.service.TokenService;
import br.com.ufms.eprontuarioapi.domain.usuario.entity.Usuario;
import br.com.ufms.eprontuarioapi.infra.repository.usuario.repositorio.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);

        boolean isTokenValido = tokenService.validarToken(token);

        if (isTokenValido){
            autenticarUsuario(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarUsuario(String token) {

        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        UsernamePasswordAuthenticationToken autenticacao= new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(autenticacao);
    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        if (isTokenInvalido(token)){
            return null;
        }

        return token.substring(7, token.length());
    }

    private boolean isTokenInvalido(String token) {
        return Objects.isNull(token) || isTokenEhVazio(token) || isTokenNaoComecaComBearer(token);
    }

    private boolean isTokenNaoComecaComBearer(String token) {
        return !token.startsWith("Bearer ");
    }

    private boolean isTokenEhVazio(String token) {
        return token.isEmpty();
    }
}
