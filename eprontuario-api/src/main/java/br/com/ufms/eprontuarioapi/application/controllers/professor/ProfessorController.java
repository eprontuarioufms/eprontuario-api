package br.com.ufms.eprontuarioapi.application.controllers.professor;

import br.com.ufms.eprontuarioapi.domain.professor.pojo.ProfessorPojo;
import br.com.ufms.eprontuarioapi.domain.professor.service.ProfessorService;
import io.swagger.annotations.Api;
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
@RequestMapping("/eprontuario-api/professor")
@Api(value = "Professor", tags = {"Professor"})
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public Page<ProfessorPojo> buscarTodosProfessoresPage(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
        return professorService.buscarTodos(paginacao);
    }

    @GetMapping(path = "/{idProfessor}")
    public ResponseEntity<ProfessorPojo> buscarProfessorPorId(@PathVariable("idProfessor") Long idProfessor)  {
        return professorService.buscarPorId(idProfessor);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProfessorPojo> inserirProfessor(@RequestBody ProfessorPojo professorPojo, UriComponentsBuilder uriComponentsBuilder)  {
        return professorService.inserir(professorPojo, uriComponentsBuilder);
    }

    @Transactional
    @PutMapping(path = "/{idProfessor}")
    public ResponseEntity<ProfessorPojo> atualizarProfessor(@PathVariable("idProfessor") Long idProfessor, @RequestBody ProfessorPojo professorPojo)  {
        return professorService.atualizar(idProfessor, professorPojo);
    }

    @Transactional
    @DeleteMapping(path = "/{idProfessor}")
    public void deletarProfessor(@PathVariable("idProfessor") Long idProfessor)  {
        professorService.deletar(idProfessor);
    }
}
