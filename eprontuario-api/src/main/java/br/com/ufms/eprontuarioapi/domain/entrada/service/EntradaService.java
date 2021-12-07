package br.com.ufms.eprontuarioapi.domain.entrada.service;


import br.com.ufms.eprontuarioapi.domain.documento.crud.DocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.documento.entity.Documento;
import br.com.ufms.eprontuarioapi.domain.documento.pojo.DocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.entrada.crud.EntradaCrudInterface;
import br.com.ufms.eprontuarioapi.domain.entrada.entity.Entrada;
import br.com.ufms.eprontuarioapi.domain.entrada.exception.EntradaNaoExisteException;
import br.com.ufms.eprontuarioapi.domain.entrada.pojo.EntradaPojo;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.crud.ItemDocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.entity.ItemDocumento;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.pojo.ItemDocumentoPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntradaService {

    @Autowired
    private EntradaCrudInterface entradaCrudInterface;

    @Autowired
    private DocumentoCrudInterface documentoCrudInterface;

    @Autowired
    private ItemDocumentoCrudInterface itemDocumentoCrudInterface;

    public List<EntradaPojo> buscarTodasEntradas() {
        return entradaCrudInterface.buscarTodasEntidades().stream().map(EntradaPojo::gerarEntradaPojo).collect(Collectors.toList());
    }

    public ResponseEntity<EntradaPojo> buscarEntrada(Long idEntrada) {
        return entradaCrudInterface.buscarEntidadePorId(idEntrada)
                .map(EntradaPojo::gerarEntradaPojo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public List<DocumentoPojo> buscarDocumentosDeUmaDeterminadaEntrada(Long idEntrada) {
        List<DocumentoPojo> documentoPojos = buscarDocumentosPorIdEntrada(idEntrada);
        atribuirItensDocumentosParaCadaDocumentoPojo(documentoPojos);

        return documentoPojos;
    }

    private void atribuirItensDocumentosParaCadaDocumentoPojo(List<DocumentoPojo> documentoPojos) {
        if (documentoPojos.isEmpty()) {
            return;
        }

        documentoPojos.forEach(documentoPojo -> {
            List<ItemDocumento> itensDocumentos = itemDocumentoCrudInterface.buscarTodosItensDocumentosPorIdDocumento(documentoPojo.getId());
            documentoPojo.setItensDocumento(itensDocumentos.stream().map(ItemDocumentoPojo::gerarItemDocumentoPojo).collect(Collectors.toList()));
        });
    }

    private List<DocumentoPojo> buscarDocumentosPorIdEntrada(Long idEntrada) {
        return documentoCrudInterface.buscarTodosDocumentosPorIdDaEntrada(idEntrada).stream()
                .map(DocumentoPojo::gerarDocumentoPojo).collect(Collectors.toList());
    }

    public ResponseEntity<EntradaPojo> inserirEntrada(EntradaPojo entradaPojo, UriComponentsBuilder uriComponentsBuilder) {
        Entrada entrada = entradaPojo.gerarEntrada();
        Entrada entradaSalva = entradaCrudInterface.salvarEntidade(entrada);

        URI uri = uriComponentsBuilder.path("/eprontuario-api/entrada/{id}").buildAndExpand(entradaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(EntradaPojo.gerarEntradaPojo(entradaSalva));
    }

    public void deletar(Long idEntrada) {
        validarExistenciaDaEntrada(idEntrada);
        removerDependenciasDaEntrada(idEntrada);
        removerEntrada(idEntrada);
    }

    private void removerEntrada(Long idEntrada) {
        entradaCrudInterface.deletarEntidadePorId(idEntrada);
    }

    private void removerDependenciasDaEntrada(Long idEntrada) {
        documentoCrudInterface.buscarTodosDocumentosPorIdDaEntrada(idEntrada)
                .stream()
                .map(Documento::getId)
                .forEach(idDocumento -> {
                    deletarItensDoDocumento(idDocumento);
                    deletarDocumentoPorId(idDocumento);

                });
    }

    private void deletarItensDoDocumento(Long idDocumento) {
        itemDocumentoCrudInterface.deletarTodosItensComMesmoIdDocumento(idDocumento);
    }

    private void deletarDocumentoPorId(Long idDocumento) {
        documentoCrudInterface.deletarEntidadePorId(idDocumento);
    }

    private void validarExistenciaDaEntrada(Long idEntrada) {
        if (isEntradaNaoExiste(idEntrada)) {
            throw new EntradaNaoExisteException("Entrada não encontrada.");
        }
    }

    private boolean isEntradaNaoExiste(Long idEntrada) {
        return !entradaCrudInterface.existeEntidadePorId(idEntrada);
    }
}
