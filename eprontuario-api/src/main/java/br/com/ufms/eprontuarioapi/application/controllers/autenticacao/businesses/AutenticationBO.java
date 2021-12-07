package br.com.ufms.eprontuarioapi.application.controllers.autenticacao.businesses;


import br.com.ufms.eprontuarioapi.application.config.security.token.service.TokenService;
import br.com.ufms.eprontuarioapi.application.controllers.autenticacao.vo.AutenticacaoResponseUfmsVO;
import br.com.ufms.eprontuarioapi.domain.academico.entity.Academico;
import br.com.ufms.eprontuarioapi.domain.perfil.entity.Perfil;
import br.com.ufms.eprontuarioapi.domain.perfil.enumeration.EPerfil;
import br.com.ufms.eprontuarioapi.domain.pessoa.enumeration.ETipoPessoa;
import br.com.ufms.eprontuarioapi.domain.usuario.pojo.UsuarioPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutenticationBO {

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<UsuarioPojo> gerarResponseEntityDeUsuarioPojo(AutenticacaoResponseUfmsVO response) {

        Perfil perfil = new Perfil();
        perfil.setPerfil(EPerfil.ACADEMICO);

        List<Perfil> perfis = new ArrayList<>();
        perfis.add(perfil);

        Academico academico = new Academico();
        academico.setRga(response.getUsuario().getRga());
        academico.setNome(response.getUsuario().getNome());
        academico.setCpf(response.getUsuario().getCpf());
        academico.setTipoPessoa(ETipoPessoa.ACADEMICO);



        UsuarioPojo usuarioPojo = new UsuarioPojo();
        usuarioPojo.setNome(response.getUsuario().getNome());
        usuarioPojo.setEmail(response.getUsuario().getEmailAlternativo());
        usuarioPojo.setLogin(response.getUsuario().getPassaporte());
        usuarioPojo.setPessoa(academico);
        usuarioPojo.setPerfis(perfis);

        return ResponseEntity.ok(usuarioPojo);
    }
}
