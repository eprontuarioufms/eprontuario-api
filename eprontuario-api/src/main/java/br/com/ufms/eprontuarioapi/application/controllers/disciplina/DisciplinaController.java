package br.com.ufms.eprontuarioapi.application.controllers.disciplina;


import br.com.ufms.eprontuarioapi.domain.disciplina.pojo.DisciplinaPojo;
import br.com.ufms.eprontuarioapi.domain.disciplina.service.DisciplinaService;
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
@RequestMapping("/eprontuario-api/disciplina")
@Api(value = "Disciplina", tags = {"Disciplina"})
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @ApiOperation(value = "Buscar Disciplina com paginação", tags = {"Disciplina"})
    @GetMapping
    public Page<DisciplinaPojo> buscarTodasDisciplinasPage(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
        return disciplinaService.buscarTodos(paginacao);
    }

    @ApiOperation(value = "Buscar Disciplina expecífica por meio do identificador", tags = {"Disciplina"})
    @GetMapping(path = "/{id}")
    public ResponseEntity<DisciplinaPojo> buscarDisciplinaPorId(@PathVariable("id") Long id)  {
        return disciplinaService.buscarPorId(id);
    }

    @ApiOperation(value = "inserir Disciplina", tags = {"Disciplina"})
    @Transactional
    @PostMapping
    public ResponseEntity<DisciplinaPojo> inserirDisciplina(@RequestBody DisciplinaPojo disciplinaPojo, UriComponentsBuilder uriComponentsBuilder)  {
        return disciplinaService.inserir(disciplinaPojo, uriComponentsBuilder);
    }

    @ApiOperation(value = "Atualizar Disciplina", tags = {"Disciplina"})
    @Transactional
    @PutMapping(path = "/{idDisciplina}")
    public ResponseEntity<DisciplinaPojo> atualizarDisciplina(@PathVariable("idDisciplina") Long idDisciplina, @RequestBody DisciplinaPojo disciplinaPojo) {
        return disciplinaService.editar(idDisciplina, disciplinaPojo);
    }

    @ApiOperation(value = "Deletar Disciplina por meio do identificador", tags = {"Disciplina"})
    @Transactional
    @DeleteMapping(path = "/{idDisciplina}")
    public void deletarDisciplina(@PathVariable("idDisciplina") Long idDisciplina)  {
        disciplinaService.excluirDisciplina(idDisciplina);
    }

}
