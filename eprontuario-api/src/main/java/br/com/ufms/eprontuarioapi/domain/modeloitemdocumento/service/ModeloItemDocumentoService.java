package br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.service;


import br.com.ufms.eprontuarioapi.domain.documento.exceptions.DocumentoNaoExisteException;
import br.com.ufms.eprontuarioapi.domain.generic.service.GenericService;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.businesses.InserirModeloItemDocumentoBO;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.crud.ModeloItemDocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.entity.ModeloItemDocumento;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.pojo.ModeloItemDocumentoPojo;
import br.com.ufms.eprontuarioapi.infra.repository.modeloitemdocumento.repositorio.ModeloItemDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModeloItemDocumentoService extends GenericService<ModeloItemDocumento, ModeloItemDocumentoRepository, Long> {

    @Autowired
    private ModeloItemDocumentoCrudInterface modeloItemDocumentoCrudInterface;

    @Autowired
    private InserirModeloItemDocumentoBO inserirModeloItemDocumentoBO;


    public ResponseEntity<ModeloItemDocumentoPojo> buscarModeloPorId(Long idModelor)  {
        return modeloItemDocumentoCrudInterface.buscarEntidadePorId(idModelor)
                .map(ModeloItemDocumentoPojo::gerarModeloItemDocumentoPojo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<ModeloItemDocumentoPojo> inserir(ModeloItemDocumentoPojo modeloItemDocumentoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        ModeloItemDocumento modeloItemDocumentoSalvo = inserirModeloItemDocumentoBO.executar(modeloItemDocumentoPojo);

        URI uri = uriComponentsBuilder.path("/controle-item-documento/{id}").buildAndExpand(modeloItemDocumentoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(ModeloItemDocumentoPojo.gerarModeloItemDocumentoPojo(modeloItemDocumentoSalvo));
    }

    public void deletar(Long idModeloItemDocumento) {
        validarExistenciaDoModeloItemdocumento(idModeloItemDocumento);
        modeloItemDocumentoCrudInterface.deletarEntidadePorId(idModeloItemDocumento);
    }

    public ResponseEntity<ModeloItemDocumentoPojo> atualizar(Long idModeloItemDoc, ModeloItemDocumentoPojo modeloItemDocumentoPojo)  {
        validarExistenciaDoModeloItemdocumento(idModeloItemDoc);
        ModeloItemDocumento modeloItemDocumentoAtualizado = modeloItemDocumentoCrudInterface.salvarEntidade(modeloItemDocumentoPojo.gerarModeloItemDocumento());

        return ResponseEntity.ok(ModeloItemDocumentoPojo.gerarModeloItemDocumentoPojo(modeloItemDocumentoAtualizado));
    }

    public Page<ModeloItemDocumentoPojo> buscarTodos(Pageable paginacao) {
        Page<ModeloItemDocumento> controleItemDocumentoPage = modeloItemDocumentoCrudInterface.buscarTodasEntidades(paginacao);

        return controleItemDocumentoPage.map(ModeloItemDocumentoPojo::gerarModeloItemDocumentoPojo);
    }

    public List<ModeloItemDocumentoPojo> buscarTodosModeloItemDocumento(String tituloDocumento) {
        List<ModeloItemDocumento> modeloItemDocumentoList = modeloItemDocumentoCrudInterface.buscarTodosModelosPeloTituloDoDocumento(tituloDocumento);
        return modeloItemDocumentoList.stream().map(ModeloItemDocumentoPojo::gerarModeloItemDocumentoPojo).collect(Collectors.toList());
    }

    private void validarExistenciaDoModeloItemdocumento(Long idModeloItemDoc) throws DocumentoNaoExisteException {
        if (isModeloItemDocumentoNaoExiste(idModeloItemDoc)) {
            throw new DocumentoNaoExisteException("Documento não foi encontrado");
        }
    }

    private boolean isModeloItemDocumentoNaoExiste(Long idModeloItemDoc) {
        return !modeloItemDocumentoCrudInterface.existeEntidadePorId(idModeloItemDoc);
    }

}
