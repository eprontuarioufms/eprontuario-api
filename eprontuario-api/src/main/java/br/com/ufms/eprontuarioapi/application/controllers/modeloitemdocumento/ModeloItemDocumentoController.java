package br.com.ufms.eprontuarioapi.application.controllers.modeloitemdocumento;


import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.pojo.ModeloItemDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.service.ModeloItemDocumentoService;
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
@RequestMapping("/eprontuario-api/modelo-item-documento")
@Api(value = "Modelo Item Documento", tags = {"Modelo Item Documento"})
public class ModeloItemDocumentoController {

    @Autowired
    private ModeloItemDocumentoService modeloItemDocumentoService;

    @ApiOperation(value = "Buscar Modelo de Item Documento por meio do identificador", tags = {"Modelo Item Documento"})
    @GetMapping(path = "/{idModeloItemDocumento}")
    public ResponseEntity<ModeloItemDocumentoPojo> buscarModeloPorId(@PathVariable("idModeloItemDocumento") Long idModeloItemDocumento)  {
        return modeloItemDocumentoService.buscarModeloPorId(idModeloItemDocumento);
    }

    @ApiOperation(value = "Buscar todos modelos-itens-documento em paginação", tags = {"Modelo Item Documento"})
    @GetMapping
    public Page<ModeloItemDocumentoPojo> buscarTodosModeloItensDocumentosPage(Pageable paginacao) {
        return modeloItemDocumentoService.buscarTodos(paginacao);
    }

    @ApiOperation(value = "Buscar todos Modelos de Item Documento por Título do Item", tags = {"Modelo Item Documento"})
    @GetMapping(path = "/documento")
    public List<ModeloItemDocumentoPojo> buscarTodosModeloItensDocumentosPorTituloDocumento(@RequestParam(name = "titulo") String titulo) {
        return modeloItemDocumentoService.buscarTodosModeloItemDocumento(titulo);
    }

    @ApiOperation(value = "Inserir Modelo de Item Documento", tags = {"Modelo Item Documento"})
    @Transactional
    @PostMapping
    public ResponseEntity<ModeloItemDocumentoPojo> inserirModeloItemDocumento(@RequestBody ModeloItemDocumentoPojo modeloItemDocumentoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        return modeloItemDocumentoService.inserir(modeloItemDocumentoPojo, uriComponentsBuilder);
    }

    @ApiOperation(value = "Atualizar Modelo de Item Documento", tags = {"Modelo Item Documento"})
    @Transactional
    @PutMapping(path = "/{idModeloItemDocumento}")
    public ResponseEntity<ModeloItemDocumentoPojo> atualizarModeloItemDocumento(@PathVariable("idModeloItemDocumento") Long idModeloItemDocumento, @RequestBody ModeloItemDocumentoPojo modeloItemDocumentoPojo)  {
        return modeloItemDocumentoService.atualizar(idModeloItemDocumento, modeloItemDocumentoPojo);
    }

    @ApiOperation(value = "Deletar Modelo de Item Documento por meio do identificador", tags = {"Modelo Item Documento"})
    @Transactional
    @DeleteMapping(path = "/{idModeloItemDocumento}")
    public void deletarModeloItemDocumento(@PathVariable("idModeloItemDocumento") Long idModeloItemDocumento)  {
        modeloItemDocumentoService.deletar(idModeloItemDocumento);
    }

}
