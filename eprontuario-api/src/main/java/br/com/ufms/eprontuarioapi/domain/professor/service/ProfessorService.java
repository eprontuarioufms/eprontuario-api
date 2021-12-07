package br.com.ufms.eprontuarioapi.domain.professor.service;

import br.com.ufms.eprontuarioapi.domain.professor.crud.ProfessorCrudInterface;
import br.com.ufms.eprontuarioapi.domain.professor.entity.Professor;
import br.com.ufms.eprontuarioapi.domain.professor.exception.ProfessorNaoExisteExceprion;
import br.com.ufms.eprontuarioapi.domain.professor.pojo.ProfessorPojo;
import br.com.ufms.eprontuarioapi.domain.usuario.crud.UsuarioCrudInterface;
import br.com.ufms.eprontuarioapi.domain.usuario.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Objects.nonNull;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorCrudInterface professorCrudInterface;

    @Autowired
    private UsuarioCrudInterface usuarioCrudInterface;

    public Page<ProfessorPojo> buscarTodos(Pageable paginacao) {
        Page<Professor> professorPage = professorCrudInterface.buscarTodasEntidades(paginacao);
        return professorPage.map(ProfessorPojo::gerarProfessorPojo);
    }

    public ResponseEntity<ProfessorPojo> buscarPorId(Long idProfessor)  {
        return professorCrudInterface.buscarEntidadePorId(idProfessor)
                .map(ProfessorPojo::gerarProfessorPojo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<ProfessorPojo> inserir(ProfessorPojo professorPojo, UriComponentsBuilder uriComponentsBuilder)  {
        Professor professor = professorPojo.gerarProfessor();

        Professor professorSalvo = professorCrudInterface.salvarEntidade(professor);

        URI uri = uriComponentsBuilder.path("/professor/{id}").buildAndExpand(professorSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(ProfessorPojo.gerarProfessorPojo(professorSalvo));
    }

    public ResponseEntity<ProfessorPojo> atualizar(Long idProfessor, ProfessorPojo professorPojo)  {
        Professor professor = professorCrudInterface.salvarEntidade(professorPojo.gerarProfessor());
        return ResponseEntity.ok(ProfessorPojo.gerarProfessorPojo(professor));
    }

    public void deletar(Long idProfessor)  {
        validarExistenciaDoProfessor(idProfessor);
        removerUsuarioDoProfessor(idProfessor);
        removerProfessorPorId(idProfessor);
    }

    private void removerProfessorPorId(Long idProfessor)  {
        professorCrudInterface.deletarEntidadePorId(idProfessor);
    }

    private void removerUsuarioDoProfessor(Long idProfessor)  {
        Usuario usuarioDoProfessor = usuarioCrudInterface.buscarUsuarioPorIdPessoa(idProfessor);

        if (nonNull(usuarioDoProfessor)){
            usuarioCrudInterface.deletarEntidadePorId(idProfessor);
        }
    }

    private void validarExistenciaDoProfessor(Long idProfessor) {
        if (professorNaoExiste(idProfessor)){
            throw new ProfessorNaoExisteExceprion("Porfessor não existe.");
        }
    }

    private boolean professorNaoExiste(Long idProfessor) {
        return !professorCrudInterface.existeEntidadePorId(idProfessor);
    }
}
