package br.com.ufms.eprontuarioapi.application.config.security.token.service;

import br.com.ufms.eprontuarioapi.domain.usuario.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${e-prontuario.jwt.expiration}")
    private String tempoExpiracaoToken;

    @Value("${e-prontuario.jwt.secret}")
    private String segredo;


    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();

        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(tempoExpiracaoToken));

        return Jwts.builder()
                .setIssuer("API E-PRONTUARIO UFMS")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, segredo)
                .compact();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.segredo).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims body = Jwts.parser().setSigningKey(this.segredo).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }
}
