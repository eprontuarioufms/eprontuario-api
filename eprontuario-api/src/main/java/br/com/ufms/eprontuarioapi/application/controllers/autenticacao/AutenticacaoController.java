package br.com.ufms.eprontuarioapi.application.controllers.autenticacao;


import br.com.ufms.eprontuarioapi.application.config.security.token.dto.TokenDTO;
import br.com.ufms.eprontuarioapi.application.config.security.token.service.TokenService;
import br.com.ufms.eprontuarioapi.application.controllers.autenticacao.vo.AutenticacaoVO;
import br.com.ufms.eprontuarioapi.application.controllers.autenticacao.vo.AutenticationRequestUfmsVO;
import br.com.ufms.eprontuarioapi.application.controllers.autenticacao.service.AutenticationService;
import br.com.ufms.eprontuarioapi.domain.usuario.pojo.UsuarioPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eprontuario-api/auth")
@Api(value = "Autenticação", description = "Autenticação necessária para acesso a todos endpoints da API", tags = {"Autenticação"})
@Profile("production")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutenticationService autenticationService;

    @ApiOperation(value = "Autenticação necessária para utilizar a API", tags = {"Autenticação"})
    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoVO autenticacaoVO) {
        UsernamePasswordAuthenticationToken dadosAutenticacao = autenticacaoVO.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(dadosAutenticacao);
            String token = tokenService.gerarToken(authentication);

            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation(value = "Autenticação pela UFMS para utilizar os dados para agilizar o cadastro na API", tags = {"Autenticação"})
    @PostMapping("/UFMS")
    public ResponseEntity<UsuarioPojo> autenticarAlunoUFMS(@RequestBody AutenticationRequestUfmsVO autenticationRequestUfmsVO) {
        return autenticationService.autenticarComPassaporteUFMS(autenticationRequestUfmsVO);
    }

}
