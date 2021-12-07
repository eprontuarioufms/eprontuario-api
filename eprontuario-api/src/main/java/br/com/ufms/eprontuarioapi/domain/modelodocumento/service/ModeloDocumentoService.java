package br.com.ufms.eprontuarioapi.domain.modelodocumento.service;

import br.com.ufms.eprontuarioapi.domain.documento.enumeration.EStatus;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.businesses.ExcluirModeloDocumentoBO;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.businesses.InserirModeloDocumentoBO;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.crud.ModeloDocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.entity.ModeloDocumento;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.pojo.ModeloDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.pojo.ModeloItemDocumentoPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModeloDocumentoService {

    @Autowired
    private ModeloDocumentoCrudInterface modeloDocumentoCrudInterface;

    @Autowired
    private ExcluirModeloDocumentoBO excluirModeloDocumentoBO;

    @Autowired
    private InserirModeloDocumentoBO inserirModeloDocumentoBO;

    public List<ModeloDocumentoPojo> buscarTitulosDeDocumentosRegistrados() {
        return modeloDocumentoCrudInterface.buscarTodasEntidades().stream().map(ModeloDocumentoPojo::gerarModeloDocumentoPojo).collect(Collectors.toList());
    }

    public ResponseEntity<ModeloDocumentoPojo> inserir(ModeloDocumentoPojo modeloDocumentoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        ModeloDocumento modeloDocumentoSalvo = inserirModeloDocumentoBO.executar(modeloDocumentoPojo);

        return ResponseEntity.ok(ModeloDocumentoPojo.gerarModeloDocumentoPojo(modeloDocumentoSalvo));
    }

    public Page<ModeloItemDocumentoPojo> buscarTodos(Pageable paginacao, EStatus status) {
        return null;
    }

    public ResponseEntity<ModeloDocumentoPojo> atualizar(Long idModeloDocumento, ModeloDocumentoPojo modeloDocumentoPojo)  {
        validarExistenciaDoModeloDocumento(idModeloDocumento);

        ModeloDocumento modeloDocumentoParaSalvar = modeloDocumentoPojo.gerarModeloDocumento();
        ModeloDocumento modeloDocumentoSalvo = modeloDocumentoCrudInterface.salvarEntidade(modeloDocumentoParaSalvar);

        return ResponseEntity.ok(ModeloDocumentoPojo.gerarModeloDocumentoPojo(modeloDocumentoSalvo));
    }

    public void deletar(Long idModeloDocumento)  {
        validarExistenciaDoModeloDocumento(idModeloDocumento);
        excluirModeloDocumentoBO.executar(idModeloDocumento);
    }

    private void validarExistenciaDoModeloDocumento(Long idModeloDocumento) {
        if (isModeloDocumentoNaoExiste(idModeloDocumento)){
            throw new IllegalArgumentException("Modelo de documento não Existe.");
        }
    }

    private boolean isModeloDocumentoNaoExiste(Long idModeloDocumento) {
        return !modeloDocumentoCrudInterface.existeEntidadePorId(idModeloDocumento);
    }
}
