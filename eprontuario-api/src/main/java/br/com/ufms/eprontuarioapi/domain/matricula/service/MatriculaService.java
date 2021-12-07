package br.com.ufms.eprontuarioapi.domain.matricula.service;

import br.com.ufms.eprontuarioapi.domain.matricula.crud.MatriculaCrudInterface;
import br.com.ufms.eprontuarioapi.domain.matricula.entity.Matricula;
import br.com.ufms.eprontuarioapi.domain.matricula.pojo.MatriculaPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaCrudInterface matriculaCrudInterface;

    public ResponseEntity<MatriculaPojo> inserir(MatriculaPojo matriculaPojo, UriComponentsBuilder uriComponentsBuilder)  {
        Matricula matriculaSalva = matriculaCrudInterface.salvarEntidade(matriculaPojo.gerarMatricula());
        URI uri = uriComponentsBuilder.path("/matricula/{id}").buildAndExpand(matriculaSalva.getId()).toUri();

        return ResponseEntity.created(uri).body(MatriculaPojo.gerarMatriculaPojo(matriculaSalva));
    }

    public  ResponseEntity<MatriculaPojo>  editar(Long idMatricula, MatriculaPojo matriculaPojo)  {
        Matricula matriculaEditada = matriculaCrudInterface.editarEntidade(idMatricula, matriculaPojo.gerarMatricula());
        return ResponseEntity.ok(MatriculaPojo.gerarMatriculaPojo(matriculaEditada));
    }

    public ResponseEntity<MatriculaPojo> buscarPorId(Long idMatricula)  {
        return matriculaCrudInterface.buscarEntidadePorId(idMatricula)
                .map(MatriculaPojo::gerarMatriculaPojo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public Page<MatriculaPojo> buscarTodos(Pageable paginacao) {
        Page<Matricula> disciplinaPage = matriculaCrudInterface.buscarTodasEntidades(paginacao);
        return disciplinaPage.map(MatriculaPojo::gerarMatriculaPojo);
    }

    public void excluirDisciplina(Long idMatricula)  {
        matriculaCrudInterface.deletarEntidadePorId(idMatricula);
    }
}
