package br.com.ufms.eprontuarioapi.infra.repository.professor.implementacao;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.professor.crud.ProfessorCrudInterface;
import br.com.ufms.eprontuarioapi.domain.professor.entity.Professor;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNulaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.professor.repositorio.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ProfessorCrudImpl implements ProfessorCrudInterface {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public Professor salvarEntidade(Professor entidade) throws GenericRuntimeException {
        validarProfessor(entidade);
        return professorRepository.save(entidade);
    }

    @Override
    public Professor editarEntidade(Long idEntidade, Professor entidade) throws GenericRuntimeException {
        validarIdProfessor(idEntidade);
        validarProfessor(entidade);
        return professorRepository.save(entidade);
    }

    @Override
    public Optional<Professor> buscarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdProfessor(idEntidade);
        return professorRepository.findById(idEntidade);
    }

    @Override
    public Page<Professor> buscarTodasEntidades(Pageable paginacao) {
        return professorRepository.findAll(paginacao);
    }

    @Override
    public List<Professor> buscarTodasEntidades() {
        return professorRepository.findAll();
    }

    @Override
    public void deletarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdProfessor(idEntidade);
        professorRepository.deleteById(idEntidade);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade) {
        return professorRepository.existsById(idEntidade);
    }

    private void validarProfessor(Professor entidade) throws GenericRuntimeException {
        if (isNull(entidade)) {
            throw new EntidadeNulaInfraException("Não pode ser salvo Professor Nulo.");
        }
    }

    private void validarIdProfessor(Long idEntidade) throws GenericRuntimeException {
        if (!professorRepository.existsById(idEntidade)) {
            throw new EntidadeNaoEncontradaInfraException("Professor não encontrado.");
        }
    }
}
