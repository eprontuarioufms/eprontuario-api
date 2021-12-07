package br.com.ufms.eprontuarioapi.application.controllers.documento;


import br.com.ufms.eprontuarioapi.domain.documento.pojo.DocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.documento.service.DocumentoService;
import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericSqlRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/eprontuario-api/documento")
@Api(value = "Documentos", tags = {"Documento"})
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @ApiOperation(value = "Buscar documentos com paginação", tags = {"Documento"})
    @GetMapping
    public Page<DocumentoPojo> buscarTodosDocumentosPage(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
        return documentoService.buscarTodos(paginacao);
    }

    @ApiOperation(value = "Buscar Documento expecífico por meio do identificador", tags = {"Documento"})
    @GetMapping(path = "/{id}")
    public ResponseEntity<DocumentoPojo> buscarDocumentoPorId(@PathVariable("id") Long id)  {
        return documentoService.buscarPorId(id);
    }

    @ApiOperation(value = "inserir Documento", tags = {"Documento"})
    @Transactional
    @PostMapping
    public ResponseEntity<DocumentoPojo> inserirDocumento(@RequestBody DocumentoPojo documentoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        return documentoService.inserir(documentoPojo, uriComponentsBuilder);
    }

    @ApiOperation(value = "Atualizar Documento", tags = {"Documento"})
    @Transactional
    @PutMapping(path = "/{idDocumento}")
    public ResponseEntity<DocumentoPojo> atualizarDocumento(@PathVariable("idDocumento") Long idDocumento, @RequestBody DocumentoPojo documentoPojo) throws GenericSqlRuntimeException, IllegalAccessException {
        return documentoService.atualizar(idDocumento, documentoPojo);
    }

    @ApiOperation(value = "Deletar Documento por meio do identificador", tags = {"Documento"})
    @Transactional
    @DeleteMapping(path = "/{idDocumento}")
    public void deletarDocumento(@PathVariable("idDocumento") Long idDocumento)  {
        documentoService.deletar(idDocumento);
    }

}
