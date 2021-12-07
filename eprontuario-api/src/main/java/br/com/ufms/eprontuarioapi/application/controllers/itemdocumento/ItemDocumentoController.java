package br.com.ufms.eprontuarioapi.application.controllers.itemdocumento;


import br.com.ufms.eprontuarioapi.domain.itemdocumento.pojo.ItemDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.service.ItemDocumentoService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/eprontuario-api/item-documento")
@Api(value = "Item Documento", tags = {"Item Documento"})
public class ItemDocumentoController {

    @Autowired
    private ItemDocumentoService itemDocumentoService;

    @ApiOperation(value = "Buscar item-documento por meio do identificador", tags = {"Item Documento"})
    @GetMapping(path = "/{idItem}")
    public ResponseEntity<ItemDocumentoPojo> buscarControlePorId(@PathVariable("idItem") Long idItem)  {
        return itemDocumentoService.buscarItemPorId(idItem);
    }

    @ApiOperation(value = "Buscar todos itens-documento em paginação", tags = {"Item Documento"})
    @GetMapping
    public Page<ItemDocumentoPojo> buscarTodosControleItensDocumentosPage(Pageable paginacao) {
        return itemDocumentoService.buscarTodosItens(paginacao);
    }

    @ApiOperation(value = "Inserir item-documento", tags = {"Item Documento"})
    @Transactional
    @PostMapping
    public ResponseEntity<ItemDocumentoPojo> inserirControleItemDocumento(@RequestBody ItemDocumentoPojo itemDocumentoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        return itemDocumentoService.inserirItem(itemDocumentoPojo, uriComponentsBuilder);
    }

    @ApiOperation(value = "Atualizar item-documento", tags = {"Item Documento"})
    @Transactional
    @PutMapping(path = "/{idItem}")
    public ResponseEntity<ItemDocumentoPojo> atualizarControleItemDocumento(@PathVariable("idItem") Long idItem, @RequestBody ItemDocumentoPojo itemDocumentoPojo)  {
        return itemDocumentoService.atualizarItem(idItem, itemDocumentoPojo);
    }

    @ApiOperation(value = "Deletar item-documento por meio do identificador", tags = {"Item Documento"})
    @Transactional
    @DeleteMapping(path = "/{idItem}")
    public void deletarControleItemDocumento(@PathVariable("idItem") Long idItem)  {
        itemDocumentoService.deletarItemPorId(idItem);
    }
}
