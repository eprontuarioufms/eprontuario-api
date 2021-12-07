package br.com.ufms.eprontuarioapi.domain.documento.service;


import br.com.ufms.eprontuarioapi.domain.documento.businesss.VerificarItensDocumentosValidosBO;
import br.com.ufms.eprontuarioapi.domain.documento.crud.DocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.documento.entity.Documento;
import br.com.ufms.eprontuarioapi.domain.documento.exceptions.DocumentoNaoExisteException;
import br.com.ufms.eprontuarioapi.domain.documento.pojo.DocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.entrada.crud.EntradaCrudInterface;
import br.com.ufms.eprontuarioapi.domain.entrada.entity.Entrada;
import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericSqlRuntimeException;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.crud.ItemDocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.entity.ItemDocumento;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.pojo.ItemDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.utils.validador.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoCrudInterface documentoCrudInterface;

    @Autowired
    private VerificarItensDocumentosValidosBO verificarItensDocumentosValidosBO;

    @Autowired
    private EntradaCrudInterface entradaCrudInterface;

    @Autowired
    private ItemDocumentoCrudInterface itemDocumentoCrudInterface;

    public ResponseEntity<DocumentoPojo> inserir(DocumentoPojo documentoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        Documento documento = documentoPojo.gerarDocumento();
        Entrada entrada = documentoPojo.getEntrada().gerarEntrada();

        criarEntradaParaDocumento(documento, entrada);
        validarDocumento(documento);

        Documento documentoSalvo = documentoCrudInterface.salvarEntidade(documento);
        List<ItemDocumento> itensDocumentos = documentoPojo.getItensDocumento().stream().map(ItemDocumentoPojo::gerarItemDocumento).collect(Collectors.toList());

        validarItensDoDocumento(documentoSalvo, itensDocumentos);

        adicionarDocumentoSalvoParaCadaItemDocumento(documentoPojo, documentoSalvo);
        salvarTodosItensDocumentos(documentoPojo);

        URI uri = uriComponentsBuilder.path("/documento/{id}").buildAndExpand(documentoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(DocumentoPojo.gerarDocumentoPojo(documentoSalvo));
    }

    public void deletar(Long idDocumento) {
        validarExistenciaDeDocumento(idDocumento);
        removerDependenciasDeDocumento(idDocumento);
        documentoCrudInterface.deletarEntidadePorId(idDocumento);
    }

    public Page<DocumentoPojo> buscarTodos(Pageable paginacao) {

        Page<Documento> documentoPage = documentoCrudInterface.buscarTodasEntidades(paginacao);

        List<DocumentoPojo> documentosPojo = documentoPage.stream()
                .map(DocumentoPojo::gerarDocumentoPojo)
                .peek(documentoPojo -> {
                    List<ItemDocumentoPojo> itensDTO = itemDocumentoCrudInterface.buscarTodosItemDocumentoComMesmoIdDeDocumento(documentoPojo.getId()).stream()
                            .map(ItemDocumentoPojo::gerarItemDocumentoPojo).collect(Collectors.toList());
                    documentoPojo.setItensDocumento(itensDTO);
                }).collect(Collectors.toList());

        return new PageImpl<>(documentosPojo, paginacao, paginacao.getOffset());
    }

    public ResponseEntity<DocumentoPojo> buscarPorId(Long idDocumento)  {
        return documentoCrudInterface.buscarEntidadePorId(idDocumento)
                .map(DocumentoPojo::gerarDocumentoPojo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<DocumentoPojo> atualizar(Long idDocumento, DocumentoPojo documentoPojo) throws GenericSqlRuntimeException, IllegalAccessException {
        validarExistenciaDeDocumento(idDocumento);
        Documento documentoAtualizado = documentoCrudInterface.editarEntidade(idDocumento, documentoPojo.gerarDocumento());
        return ResponseEntity.ok(DocumentoPojo.gerarDocumentoPojo(documentoAtualizado));
    }

    private void salvarTodosItensDocumentos(DocumentoPojo documentoPojo) {
        List<ItemDocumento> itensDocumentos = documentoPojo.getItensDocumento().stream().map(ItemDocumentoPojo::gerarItemDocumento).collect(Collectors.toList());
        itemDocumentoCrudInterface.salvarTodosItensDocumentos(itensDocumentos);
    }

    private void adicionarDocumentoSalvoParaCadaItemDocumento(DocumentoPojo documentoPojo, Documento documentoSalvo) {
        documentoPojo.getItensDocumento().forEach(itemDocumento -> itemDocumento.setDocumento(documentoSalvo));
    }

    private void validarItensDoDocumento(Documento documento, List<ItemDocumento> itensDocumentos) {
        verificarItensDocumentosValidosBO.executar(documento, itensDocumentos);
    }

    private void criarEntradaParaDocumento(Documento documento, Entrada entrada)  {
        if (isNovaEntrada(entrada)) {
            validarEntrada(entrada);//quando não existir entrada
            Entrada entradaSalva = entradaCrudInterface.salvarEntidade(entrada);
            documento.setEntrada(entradaSalva);
        }
    }

    private void validarDocumento(Documento documento) {

        Validator.of(documento)
                .validate(Documento::getPaciente, Objects::nonNull, "Paciente não existe.")
                .validate(Documento::getAcademico, Objects::nonNull, "Academico não existe.")
                .validate(Documento::getEntrada, Objects::nonNull, "Entrada não existe.")
                .validate(Documento::getTitulo, Objects::nonNull, "O Titulo documento não existe.")
                .get();
    }

    private boolean isNovaEntrada(Entrada entrada) {
        return nonNull(entrada) && isNull(entrada.getId());
    }

    private void validarEntrada(Entrada entrada) {
        Validator.of(entrada)
                .validate(Entrada::getPaciente, Objects::nonNull, "Paciente não existe.")
                .get();
    }

    private void removerDependenciasDeDocumento(Long idDocumento) {
        removerItensRelacionadosAoDocumento(idDocumento);
    }

    private void removerItensRelacionadosAoDocumento(Long idDocumento) {
        List<ItemDocumento> itensDocumento = itemDocumentoCrudInterface.buscarTodosItensDocumentosPorIdDocumento(idDocumento);

        itensDocumento.forEach(itemDocumento -> itemDocumentoCrudInterface.deletarEntidadePorId(itemDocumento.getId()));
    }

    private void validarExistenciaDeDocumento(Long idDocumento) throws GenericRuntimeException {
        if (isDocumentoNaoEncontrado(idDocumento)) {
            throw new DocumentoNaoExisteException("Documento não foi encontrado");
        }
    }

    private boolean isDocumentoNaoEncontrado(Long idDocumento) {
        return !documentoCrudInterface.existeEntidadePorId(idDocumento);
    }

}
