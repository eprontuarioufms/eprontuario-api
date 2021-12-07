package br.com.ufms.eprontuarioapi.application.controllers.autenticacao.service;

import br.com.ufms.eprontuarioapi.application.controllers.autenticacao.vo.AutenticacaoResponseUfmsVO;
import br.com.ufms.eprontuarioapi.application.controllers.autenticacao.vo.AutenticationRequestUfmsVO;
import br.com.ufms.eprontuarioapi.application.controllers.autenticacao.businesses.AutenticationBO;
import br.com.ufms.eprontuarioapi.domain.usuario.pojo.UsuarioPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AutenticationService {

    @Autowired
    private AutenticationBO autenticationBO;

    public ResponseEntity<UsuarioPojo> autenticarComPassaporteUFMS(AutenticationRequestUfmsVO autenticationRequestUfmsVO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        final String url = "https://api.ufms.br/passaporte-ws/authentication";

        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> ufmsRequest = new HashMap<>();
        ufmsRequest.put("passaporte", autenticationRequestUfmsVO.getPassaporte());
        ufmsRequest.put("senha", autenticationRequestUfmsVO.getSenha());

        HttpEntity<Map<String, String>> request = new HttpEntity<>(ufmsRequest, headers);

        try {
            AutenticacaoResponseUfmsVO response = restTemplate.postForObject(url, request, AutenticacaoResponseUfmsVO.class);
            return autenticationBO.gerarResponseEntityDeUsuarioPojo(response);

        } catch (HttpStatusCodeException se) {
            Logger.getGlobal().log(Level.WARNING, "Não foi possível verificar Passaporte.");
            return ResponseEntity.badRequest().build();

        }catch (Exception e){
            Logger.getGlobal().log(Level.WARNING, "Ocorreu um erro desconhecido na autenticação.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
