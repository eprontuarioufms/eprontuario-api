package br.com.ufms.eprontuarioapi.application.controllers.entrada;


import br.com.ufms.eprontuarioapi.domain.documento.pojo.DocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.entrada.pojo.EntradaPojo;
import br.com.ufms.eprontuarioapi.domain.entrada.service.EntradaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/eprontuario-api/entrada")
@Api(value = "Entrada", tags = {"Entrada"})
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @GetMapping
    public List<EntradaPojo> buscarTodasEntradas() {
        return entradaService.buscarTodasEntradas();
    }

    @GetMapping(path = "/{idEntrada}")
    public ResponseEntity<EntradaPojo> buscarEntrada(@PathVariable("idEntrada") Long idEntrada)  {
        return entradaService.buscarEntrada(idEntrada);
    }

    @ApiOperation(value = "Inserir nova Entrada", tags = {"Entrada"})
    @Transactional
    @PostMapping
    public ResponseEntity<EntradaPojo> inserirEntrada(@RequestBody EntradaPojo entradaPojo, UriComponentsBuilder uriComponentsBuilder)  {
        return entradaService.inserirEntrada(entradaPojo, uriComponentsBuilder);
    }
    @ApiOperation(value = "Buscar Documentos pelo identificador da Entrada", tags = {"Entrada"})
    @GetMapping(path = "/{idEntrada}/documento")
    public List<DocumentoPojo> buscarDocumentosDeUmaDeterminadaEntrada(@PathVariable("idEntrada") Long idEntrada) {
        return entradaService.buscarDocumentosDeUmaDeterminadaEntrada(idEntrada);
    }

    @ApiOperation(value = "Deletar Entrada por meio do identificador", tags = {"Entrada"})
    @Transactional
    @DeleteMapping(path = "/{idEntrada}")
    public void deletarControleItemDocumento(@PathVariable("idEntrada") Long idEntrada)  {
        entradaService.deletar(idEntrada);
    }

}
