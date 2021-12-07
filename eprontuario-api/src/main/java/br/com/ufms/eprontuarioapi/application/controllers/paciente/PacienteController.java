package br.com.ufms.eprontuarioapi.application.controllers.paciente;

import br.com.ufms.eprontuarioapi.domain.paciente.pojo.PacientePojo;
import br.com.ufms.eprontuarioapi.domain.paciente.service.PacienteService;
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
@RequestMapping("/eprontuario-api/paciente")
@Api(value = "Paciente", tags = {"Paciente"})
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public Page<PacientePojo> buscarTodosPacientePage(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
        return pacienteService.buscarTodos(paginacao);
    }

    @GetMapping(path = "/{idPaciente}")
    public ResponseEntity<PacientePojo> buscarPacientePorId(@PathVariable("idPaciente") Long idPaciente)  {
        return pacienteService.buscarPorId(idPaciente);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PacientePojo> inserirPaciente(@RequestBody PacientePojo pacientePojo, UriComponentsBuilder uriComponentsBuilder)  {
        return pacienteService.inserir(pacientePojo, uriComponentsBuilder);
    }

    @Transactional
    @PutMapping(path = "/{idPaciente}")
    public ResponseEntity<PacientePojo> atualizarPaciente(@PathVariable("idPaciente") Long idPaciente, @RequestBody PacientePojo pacientePojo)  {
        return pacienteService.atualizar(idPaciente, pacientePojo);
    }

    @Transactional
    @DeleteMapping(path = "/{idPaciente}")
    public void deletarPaciente(@PathVariable("idPaciente") Long idPaciente)  {
        pacienteService.deletar(idPaciente);
    }
}
