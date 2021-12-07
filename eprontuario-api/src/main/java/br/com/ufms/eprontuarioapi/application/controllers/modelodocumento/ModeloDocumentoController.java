package br.com.ufms.eprontuarioapi.application.controllers.modelodocumento;


import br.com.ufms.eprontuarioapi.domain.documento.enumeration.EStatus;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.pojo.ModeloDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.service.ModeloDocumentoService;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.pojo.ModeloItemDocumentoPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/eprontuario-api/modelo-documento")
@Api(value = "Modelo Documento", tags = {"Modelo Documento"})
public class ModeloDocumentoController {

    @Autowired
    private ModeloDocumentoService modeloDocumentoService;


    @ApiOperation(value = "Buscar Titulos de modelos de Documentos já registrados", tags = {"Modelo Documento"})
    @GetMapping(path = "/titulos-registrados")
    public List<ModeloDocumentoPojo> buscarDocumentosRegistrados() {
        return modeloDocumentoService.buscarTitulosDeDocumentosRegistrados();
    }

    @ApiOperation(value = "Buscar todos modelos de Documentos em paginação", tags = {"Modelo Documento"})
    @GetMapping
    public Page<ModeloItemDocumentoPojo> buscarTodosModelosDocumentosPage(Pageable paginacao,
                                                                          @RequestParam(name = "status", required = false) EStatus status) {
        return modeloDocumentoService.buscarTodos(paginacao, status);
    }

    @ApiOperation(value = "Inserir Modelo de Documento", tags = {"Modelo Documento"})
    @Transactional
    @PostMapping
    public ResponseEntity<ModeloDocumentoPojo> inserirModeloDocumento(@RequestBody ModeloDocumentoPojo modeloDocumentoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        return modeloDocumentoService.inserir(modeloDocumentoPojo, uriComponentsBuilder);
    }

    @ApiOperation(value = "Atualizar Modelo de Documento", tags = {"Modelo Documento"})
    @Transactional
    @PutMapping(path = "/{idModeloDocumento}")
    public ResponseEntity<ModeloDocumentoPojo> atualizarModeloDocumento(@PathVariable("idModeloDocumento") Long idModeloDocumento, @RequestBody ModeloDocumentoPojo modeloDocumentoPojo)  {
        return modeloDocumentoService.atualizar(idModeloDocumento, modeloDocumentoPojo);
    }

    @ApiOperation(value = "Deletar Modelo de Documento por meio do identificador", tags = {"Modelo Item Documento"})
    @Transactional
    @DeleteMapping(path = "/{idModeloDocumento}")
    public void deletarModeloDocumento(@PathVariable("idModeloDocumento") Long idModeloDocumento)  {
        modeloDocumentoService.deletar(idModeloDocumento);
    }

}
