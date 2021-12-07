package br.com.ufms.eprontuarioapi.application.controllers.academico;

import br.com.ufms.eprontuarioapi.domain.academico.pojo.AcademicoPojo;
import br.com.ufms.eprontuarioapi.domain.academico.service.AcademicoService;
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
@RequestMapping("/eprontuario-api/academico")
@Api(value = "Academico", tags = {"Academico"})
public class AcademicoController {

    @Autowired
    private AcademicoService academicoService;

    @ApiOperation(value = "Buscar todos Acadêmicos por meio da Paginação", tags = {"Academico"})
    @GetMapping
    public Page<AcademicoPojo> buscarTodosAcademicosPage(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
        return academicoService.buscarTodos(paginacao);
    }

    @ApiOperation(value = "Buscar Acadêmico expecífico por meio do identificador", tags = {"Academico"})
    @GetMapping(path = "/{idAcademico}")
    public ResponseEntity<AcademicoPojo> buscarAcademicoPorId(@PathVariable("idAcademico") Long idAcademico)  {
        return academicoService.buscarPorId(idAcademico);
    }

    @ApiOperation(value = "Inserir Acadêmico", tags = {"Academico"})
    @Transactional
    @PostMapping
    public ResponseEntity<AcademicoPojo> inserirAcademico(@RequestBody AcademicoPojo academicoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        return academicoService.inserir(academicoPojo, uriComponentsBuilder);
    }

    @ApiOperation(value = "Atualizar Acadêmico", tags = {"Academico"})
    @Transactional
    @PutMapping(path = "/{idAcademico}")
    public ResponseEntity<AcademicoPojo> atualizarAcademico(@PathVariable("idAcademico") Long idAcademico, @RequestBody AcademicoPojo academicoPojo) {
        return academicoService.atualizar(idAcademico, academicoPojo);
    }

    @ApiOperation(value = "Deletar Acadêmico por meio do Identificador", tags = {"Academico"})
    @Transactional
    @DeleteMapping(path = "/{idAcademico}")
    public void deletarAcademico(@PathVariable("idAcademico") Long idAcademico)  {
        academicoService.deletar(idAcademico);
    }
}
