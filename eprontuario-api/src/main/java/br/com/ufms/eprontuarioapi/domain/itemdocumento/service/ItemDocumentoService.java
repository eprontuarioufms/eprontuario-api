package br.com.ufms.eprontuarioapi.domain.itemdocumento.service;

import br.com.ufms.eprontuarioapi.domain.itemdocumento.crud.ItemDocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.entity.ItemDocumento;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.pojo.ItemDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.utils.validador.Validator;
import br.com.ufms.eprontuarioapi.domain.valorfixo.crud.ValorFixoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.valorfixo.entity.ValorFixo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isBoolean;
import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isDataHora;
import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isDouble;
import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isInteger;
import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isString;
import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isValorFixo;
import static java.util.Objects.nonNull;

@Service
public class ItemDocumentoService {

    @Autowired
    private ItemDocumentoCrudInterface itemDocumentoCrudInterface;

    @Autowired
    private ValorFixoCrudInterface valorFixoCrudInterface;

    public ResponseEntity<ItemDocumentoPojo> buscarItemPorId(Long idItem)  {
        return itemDocumentoCrudInterface.buscarEntidadePorId(idItem)
                .map(ItemDocumentoPojo::gerarItemDocumentoPojo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public Page<ItemDocumentoPojo> buscarTodosItens(Pageable paginacao) {
        Page<ItemDocumento> controleItemDocumentoPage = itemDocumentoCrudInterface.buscarTodasEntidades(paginacao);
        return controleItemDocumentoPage.map(ItemDocumentoPojo::gerarItemDocumentoPojo);
    }

    public ResponseEntity<ItemDocumentoPojo> inserirItem(ItemDocumentoPojo itemDocumentoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        ItemDocumento itemDocumento = itemDocumentoPojo.gerarItemDocumento();

        validarItemDocumento(itemDocumento);

        ItemDocumento itemDocumentoSalvo = itemDocumentoCrudInterface.salvarEntidade(itemDocumento);

        URI uri = uriComponentsBuilder.path("/item-documento/{id}").buildAndExpand(itemDocumentoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(ItemDocumentoPojo.gerarItemDocumentoPojo(itemDocumentoSalvo));
    }

    private void validarItemDocumento(ItemDocumento itemDocumento) {
        Predicate<ItemDocumento> isTipoDoItemFoiInformado = criarPredicateParaValidarTipoDoControleItemDocumento();

        Validator.of(itemDocumento)
                .validate(isTipoDoItemFoiInformado, "Deve ser informado o tipo da informação a ser salvo nos elementos do Documento")
                .get();
    }

    private Predicate<ItemDocumento> criarPredicateParaValidarTipoDoControleItemDocumento() {
        List<String> chavesJaCadastradas = valorFixoCrudInterface.buscarTodasEntidades()
                .stream().map(ValorFixo::getChave)
                .collect(Collectors.toList());

        return i -> isString(i)    ||
                    isBoolean(i)   ||
                    isDouble(i)    ||
                    isInteger(i)   ||
                    isDataHora(i)  ||
                    isValorFixo(i) &&
                    ischaveDeValorFixoJaFoiCadastrada(chavesJaCadastradas, i);
    }

    private boolean ischaveDeValorFixoJaFoiCadastrada(List<String> chavesJaCadastradas, ItemDocumento i) {
        return nonNull(i.getValorFixo().getChave()) && chavesJaCadastradas.contains(i.getValorFixo().getChave());
    }


    public ResponseEntity<ItemDocumentoPojo> atualizarItem(Long idItem, ItemDocumentoPojo itemDocumentoPojo)  {
        validarExistenciaDoItemdocumento(idItem);
        ItemDocumento itemDocumentoAtualizado = itemDocumentoCrudInterface.salvarEntidade(itemDocumentoPojo.gerarItemDocumento());

        return ResponseEntity.ok(ItemDocumentoPojo.gerarItemDocumentoPojo(itemDocumentoAtualizado));
    }

    private void validarExistenciaDoItemdocumento(Long idItem) {
        if(isItemDocumentoNaoExiste(idItem)){
            throw new IllegalArgumentException("Item de documento não existe.");
        }
    }

    private boolean isItemDocumentoNaoExiste(Long idItem) {
        return !itemDocumentoCrudInterface.existeEntidadePorId(idItem);
    }

    public void deletarItemPorId(Long idItem)  {
        validarExistenciaDoItemdocumento(idItem);
        itemDocumentoCrudInterface.deletarEntidadePorId(idItem);
    }

}
