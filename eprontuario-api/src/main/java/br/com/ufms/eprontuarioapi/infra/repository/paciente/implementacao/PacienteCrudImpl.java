package br.com.ufms.eprontuarioapi.infra.repository.paciente.implementacao;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.paciente.crud.PacienteCrudInterface;
import br.com.ufms.eprontuarioapi.domain.paciente.entity.Paciente;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNulaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.paciente.repositorio.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class PacienteCrudImpl implements PacienteCrudInterface {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Paciente salvarEntidade(Paciente paciente) throws GenericRuntimeException {
        validarPaciente(paciente);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdPaciente(idEntidade);
        return pacienteRepository.findById(idEntidade);
    }

    @Override
    public List<Paciente> buscarTodasEntidades() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente editarEntidade(Long idEntidade, Paciente paciente) throws GenericRuntimeException {
        validarIdPaciente(idEntidade);
        validarPaciente(paciente);
        return pacienteRepository.save(paciente);
    }

    @Override
    public void deletarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdPaciente(idEntidade);
        pacienteRepository.deleteById(idEntidade);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade){
        return pacienteRepository.existsById(idEntidade);
    }

    @Override
    public Page<Paciente> buscarTodasEntidades(Pageable paginacao) {
        return pacienteRepository.findAll(paginacao);
    }


    private void validarIdPaciente(Long idEntidade) throws GenericRuntimeException {
        if (!pacienteRepository.existsById(idEntidade)) {
            throw new EntidadeNaoEncontradaInfraException("Paciente não encontrado.");
        }
    }

    private void validarPaciente(Paciente paciente) throws GenericRuntimeException {
        if (isNull(paciente)) {
            throw new EntidadeNulaInfraException("Paciente não pode ser Nulo.");
        }
    }
}
