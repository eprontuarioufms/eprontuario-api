package br.com.ufms.eprontuarioapi.infra.repository.academico.implementacao;

import br.com.ufms.eprontuarioapi.domain.academico.crud.AcademicoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.academico.entity.Academico;
import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.infra.repository.academico.repositorio.AcademicoRepository;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicoCrudImpl implements AcademicoCrudInterface {

    @Autowired
    private AcademicoRepository academicoRepository;

    @Override
    public Academico salvarEntidade(Academico academico) {
        return academicoRepository.save(academico);
    }

    @Override
    public Academico editarEntidade(Long idAcademico, Academico academico) throws GenericRuntimeException {
        isAcademicoExistePorId(idAcademico);
        return academicoRepository.save(academico);
    }

    @Override
    public Optional<Academico> buscarEntidadePorId(Long idAcademico) throws GenericRuntimeException {
        isAcademicoExistePorId(idAcademico);
        return academicoRepository.findById(idAcademico);
    }

    @Override
    public Page<Academico> buscarTodasEntidades(Pageable paginacao) {
        return academicoRepository.findAll(paginacao);
    }

    @Override
    public List<Academico> buscarTodasEntidades() {
        return academicoRepository.findAll();
    }

    @Override
    public void deletarEntidadePorId(Long idAcademico) throws GenericRuntimeException {
        isAcademicoExistePorId(idAcademico);
        academicoRepository.deleteById(idAcademico);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade) {
        return academicoRepository.existsById(idEntidade);
    }


    private void isAcademicoExistePorId(Long idAcademico) throws GenericRuntimeException {
        if (!academicoRepository.existsById(idAcademico)) {
            throw new EntidadeNaoEncontradaInfraException("Acadêmico não encontrado.");
        }
    }

}
