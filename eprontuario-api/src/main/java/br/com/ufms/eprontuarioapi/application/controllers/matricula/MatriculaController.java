package br.com.ufms.eprontuarioapi.application.controllers.matricula;

import br.com.ufms.eprontuarioapi.domain.matricula.pojo.MatriculaPojo;
import br.com.ufms.eprontuarioapi.domain.matricula.service.MatriculaService;
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
@RequestMapping("/eprontuario-api/matricula")
@Api(value = "Matricula", tags = {"Matricula"})
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @ApiOperation(value = "Buscar Matricula com paginação", tags = {"Matricula"})
    @GetMapping
    public Page<MatriculaPojo> buscarTodasMatriculaPage(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
        return matriculaService.buscarTodos(paginacao);
    }

    @ApiOperation(value = "Buscar Matricula expecífica por meio do identificador", tags = {"Matricula"})
    @GetMapping(path = "/{id}")
    public ResponseEntity<MatriculaPojo> buscarMatriculaPorId(@PathVariable("id") Long id)  {
        return matriculaService.buscarPorId(id);
    }

    @ApiOperation(value = "inserir Matricula", tags = {"Matricula"})
    @Transactional
    @PostMapping
    public ResponseEntity<MatriculaPojo> inserirMatricula(@RequestBody MatriculaPojo matriculaPojo, UriComponentsBuilder uriComponentsBuilder)  {
        return matriculaService.inserir(matriculaPojo, uriComponentsBuilder);
    }

    @ApiOperation(value = "Atualizar Matricula", tags = {"Matricula"})
    @Transactional
    @PutMapping(path = "/{idMatricula}")
    public ResponseEntity<MatriculaPojo> atualizarMatricula(@PathVariable("idMatricula") Long idMatricula, @RequestBody MatriculaPojo matriculaPojo)  {
        return matriculaService.editar(idMatricula, matriculaPojo);
    }

    @ApiOperation(value = "Deletar Matricula por meio do identificador", tags = {"Matricula"})
    @Transactional
    @DeleteMapping(path = "/{idMatricula}")
    public void deletarMatricula(@PathVariable("idMatricula") Long idMatricula)  {
        matriculaService.excluirDisciplina(idMatricula);
    }

}
