package br.com.ufms.eprontuarioapi.domain.valorfixo.service;

import br.com.ufms.eprontuarioapi.domain.valorfixo.crud.ValorFixoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.valorfixo.entity.ValorFixo;
import br.com.ufms.eprontuarioapi.domain.valorfixo.pojo.ValorFixoPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Objects.isNull;

@Service
public class ValorFixoService {

    @Autowired
    private ValorFixoCrudInterface valorFixoCrudInterface;

    public Page<ValorFixoPojo> buscarTodos(Pageable paginacao) {
        Page<ValorFixo> controleItemDocumentoPage = valorFixoCrudInterface.buscarTodasEntidades(paginacao);
        return controleItemDocumentoPage.map(ValorFixoPojo::gerarValorFixoPojo);
    }

    public ResponseEntity<ValorFixoPojo> buscarPorId(Long idValorFixo)  {
        return valorFixoCrudInterface.buscarEntidadePorId(idValorFixo)
                .map(ValorFixoPojo::gerarValorFixoPojo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<ValorFixoPojo> inserir(ValorFixoPojo valorFixoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        ValorFixo valorFixo = valorFixoPojo.gerarValorFixo();

        validarValorFixo(valorFixo);

        ValorFixo valorFixoSalvo = valorFixoCrudInterface.salvarEntidade(valorFixo);

        URI uri = uriComponentsBuilder.path("/controle-item-documento/{id}").buildAndExpand(valorFixoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(ValorFixoPojo.gerarValorFixoPojo(valorFixoSalvo));
    }

    private void validarValorFixo(ValorFixo valorFixo) {
        validarValorFixoChave(valorFixo);
        validarValorFixoValor(valorFixo);
    }

    private void validarValorFixoValor(ValorFixo valorFixo) {
        if (isNull(valorFixo.getValor()) || valorFixo.getValor().isEmpty()){
            throw new IllegalArgumentException("Deve ser informado o valor do Valor Fixo");
        }
    }

    private void validarValorFixoChave(ValorFixo valorFixo) {
        if (isNull(valorFixo.getChave())){
            throw new IllegalArgumentException("Deve ser informado a chave do Valor Fixo");
        }
    }

    public ResponseEntity<ValorFixoPojo> atualizar(Long idValorFixo, ValorFixoPojo valorFixoPojo)  {
        validarExistenciaDoValorFixo(idValorFixo);
        ValorFixo valorFixo = valorFixoCrudInterface.salvarEntidade(valorFixoPojo.gerarValorFixo());
        return ResponseEntity.ok(ValorFixoPojo.gerarValorFixoPojo(valorFixo));
    }

    public void deletar(Long idValorFixo)  {
        validarExistenciaDoValorFixo(idValorFixo);
        verificarSePossuiDependencia(idValorFixo);
        valorFixoCrudInterface.deletarEntidadePorId(idValorFixo);
    }

    private void verificarSePossuiDependencia(Long idValorFixo) {

    }

    private void validarExistenciaDoValorFixo(Long idValorFixo) {
        if (isValorFixoNaoExiste(idValorFixo)){
            throw new IllegalArgumentException("Valor Fixo não existe.");
        }
    }

    private boolean isValorFixoNaoExiste(Long idValorFixo) {
        return !valorFixoCrudInterface.existeEntidadePorId(idValorFixo);
    }
}
