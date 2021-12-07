package br.com.ufms.eprontuarioapi.domain.disciplina.service;

import br.com.ufms.eprontuarioapi.domain.disciplina.entity.Disciplina;
import br.com.ufms.eprontuarioapi.domain.disciplina.crud.DiciplinaCrudInterface;
import br.com.ufms.eprontuarioapi.domain.disciplina.exception.DiciplinaPossuiInformacoesInvalidaException;
import br.com.ufms.eprontuarioapi.domain.disciplina.pojo.DisciplinaPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Objects.isNull;

@Service
public class DisciplinaService {

    @Autowired
    private DiciplinaCrudInterface diciplinaCrudInterface;

    public ResponseEntity<DisciplinaPojo> inserir(DisciplinaPojo disciplinaPojo, UriComponentsBuilder uriComponentsBuilder)  {
        validarDiciplina(disciplinaPojo);
        Disciplina disciplinaSalva = diciplinaCrudInterface.salvarEntidade(disciplinaPojo.gerarDiciplina());
        URI uri = uriComponentsBuilder.path("/disciplina/{id}").buildAndExpand(disciplinaSalva.getId()).toUri();

        return ResponseEntity.created(uri).body(DisciplinaPojo.gerarDisciplinaPojo(disciplinaSalva));
    }

    public ResponseEntity<DisciplinaPojo> editar(Long idDiciplina, DisciplinaPojo disciplinaPojo)  {
        validarDiciplina(disciplinaPojo);
        Disciplina disciplinaEditada = diciplinaCrudInterface.editarEntidade(idDiciplina, disciplinaPojo.gerarDiciplina());
        return ResponseEntity.ok(DisciplinaPojo.gerarDisciplinaPojo(disciplinaEditada));
    }

    public Page<DisciplinaPojo> buscarTodos(Pageable paginacao) {
        Page<Disciplina> disciplinaPage = diciplinaCrudInterface.buscarTodasEntidades(paginacao);
        return disciplinaPage.map(DisciplinaPojo::gerarDisciplinaPojo);
    }

    public ResponseEntity<DisciplinaPojo> buscarPorId(Long idDisciplina)  {
        return diciplinaCrudInterface.buscarEntidadePorId(idDisciplina)
                .map(DisciplinaPojo::gerarDisciplinaPojo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private void validarDiciplina(DisciplinaPojo disciplinaPojo){
        boolean isDisciplinaNaoPossuiTitulo = isNull(disciplinaPojo.getTitulo()) || disciplinaPojo.getTitulo().isEmpty();
        boolean isDisciplinaNaoPossuiProfessor = isNull(disciplinaPojo.getProfessorPojo());

        if (isDisciplinaNaoPossuiTitulo){
            throw new DiciplinaPossuiInformacoesInvalidaException("Disciplina não possui Titulo.");
        }
        if (isDisciplinaNaoPossuiProfessor){
            throw new DiciplinaPossuiInformacoesInvalidaException("Disciplina não possui Professor.");
        }
    }

    public void excluirDisciplina(Long idDisciplina)  {
        diciplinaCrudInterface.deletarEntidadePorId(idDisciplina);
    }

}
