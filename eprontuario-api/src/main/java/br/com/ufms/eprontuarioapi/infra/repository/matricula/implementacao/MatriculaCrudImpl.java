package br.com.ufms.eprontuarioapi.infra.repository.matricula.implementacao;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.matricula.crud.MatriculaCrudInterface;
import br.com.ufms.eprontuarioapi.domain.matricula.entity.Matricula;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNulaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.matricula.repositorio.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class MatriculaCrudImpl implements MatriculaCrudInterface {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Override
    public Matricula salvarEntidade(Matricula matricula) throws GenericRuntimeException {
        validarMatricula(matricula);
        return matriculaRepository.save(matricula);
    }

    @Override
    public Optional<Matricula> buscarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdMatricula(idEntidade);
        return matriculaRepository.findById(idEntidade);
    }

    @Override
    public List<Matricula> buscarTodasEntidades() {
        return matriculaRepository.findAll();
    }

    @Override
    public Matricula editarEntidade(Long idEntidade, Matricula matricula) throws GenericRuntimeException {
        validarIdMatricula(idEntidade);
        validarMatricula(matricula);
        return matriculaRepository.save(matricula);
    }

    @Override
    public void deletarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdMatricula(idEntidade);
        matriculaRepository.deleteById(idEntidade);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade) {
        return matriculaRepository.existsById(idEntidade);
    }

    @Override
    public Page<Matricula> buscarTodasEntidades(Pageable paginacao) {
        return matriculaRepository.findAll(paginacao);
    }


    private void validarIdMatricula(Long idEntidade) throws GenericRuntimeException {
        if (!matriculaRepository.existsById(idEntidade)) {
            throw new EntidadeNaoEncontradaInfraException("Matricula não encontrada.");
        }
    }

    private void validarMatricula(Matricula matricula) throws GenericRuntimeException {
        if (isNull(matricula)) {
            throw new EntidadeNulaInfraException("Matricula não pode ser Nula.");
        }
    }
}
