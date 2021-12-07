package br.com.ufms.eprontuarioapi.domain.paciente.service;


import br.com.ufms.eprontuarioapi.domain.documento.crud.DocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.paciente.crud.PacienteCrudInterface;
import br.com.ufms.eprontuarioapi.domain.paciente.entity.Paciente;
import br.com.ufms.eprontuarioapi.domain.paciente.exception.PacienteNaoExisteException;
import br.com.ufms.eprontuarioapi.domain.paciente.exception.PacientePossueVinculoException;
import br.com.ufms.eprontuarioapi.domain.paciente.pojo.PacientePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class PacienteService {

    @Autowired
    private PacienteCrudInterface pacienteCrudInterface;

    @Autowired
    private DocumentoCrudInterface documentoCrudInterface;

    public Page<PacientePojo> buscarTodos(Pageable paginacao) {
        Page<Paciente> documentoPage = pacienteCrudInterface.buscarTodasEntidades(paginacao);
        return documentoPage.map(PacientePojo::gerarPacientePojo);
    }

    public ResponseEntity<PacientePojo> buscarPorId(Long idPaciente)  {
        return pacienteCrudInterface.buscarEntidadePorId(idPaciente)
                .map(PacientePojo::gerarPacientePojo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<PacientePojo> inserir(PacientePojo pacientePojo, UriComponentsBuilder uriComponentsBuilder)  {
        Paciente paciente = pacientePojo.gerarPaciente();

        Paciente pacienteSalvo = pacienteCrudInterface.salvarEntidade(paciente);

        URI uri = uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(pacienteSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(PacientePojo.gerarPacientePojo(pacienteSalvo));
    }

    public ResponseEntity<PacientePojo> atualizar(Long idPaciente, PacientePojo pacientePojo)  {
        Paciente pacienteAtualizado = pacienteCrudInterface.salvarEntidade(pacientePojo.gerarPaciente());
        return ResponseEntity.ok(PacientePojo.gerarPacientePojo(pacienteAtualizado));

    }

    public void deletar(Long idPaciente)  {
        validarExistenciaDoPaciente(idPaciente);
        validarDependenciaDoPaciente(idPaciente);
        removerPacienterPeloId(idPaciente);

    }

    private void validarDependenciaDoPaciente(Long idPaciente) {
        if (isPacienteEstaSendoUsadoEmAlgumDocumento(idPaciente)) {
            throw new PacientePossueVinculoException("Paciente não pode ser excluido, pois possue vínculo.");
        }
    }

    private boolean isPacienteEstaSendoUsadoEmAlgumDocumento(Long idPaciente) {
        return documentoCrudInterface.isPacienteEstaSendoUsadoEmAlgumDocumento(idPaciente);
    }

    private void removerPacienterPeloId(Long idPaciente)  {
        pacienteCrudInterface.deletarEntidadePorId(idPaciente);
    }

    private void validarExistenciaDoPaciente(Long idPaciente) {
        if (isNaoExistePacienteComId(idPaciente)) {
            throw new PacienteNaoExisteException("Paciente nao encontrado.");
        }
    }

    private boolean isNaoExistePacienteComId(Long idPaciente) {
        return !pacienteCrudInterface.existeEntidadePorId(idPaciente);
    }
}
