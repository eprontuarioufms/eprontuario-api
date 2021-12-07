package br.com.ufms.eprontuarioapi.infra.repository.diciplina.implementacao;

import br.com.ufms.eprontuarioapi.domain.disciplina.entity.Disciplina;
import br.com.ufms.eprontuarioapi.domain.disciplina.crud.DiciplinaCrudInterface;
import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.diciplina.repositorio.DiciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiciplinaCrudImpl implements DiciplinaCrudInterface {

    @Autowired
    private DiciplinaRepository diciplinaRepository;


    @Override
    public Disciplina salvarEntidade(Disciplina disciplina) {
        return diciplinaRepository.save(disciplina);
    }

    @Override
    public Optional<Disciplina> buscarEntidadePorId(Long idDiciplina) throws GenericRuntimeException {
        isDiciplinaExistePorId(idDiciplina);
        return diciplinaRepository.findById(idDiciplina);
    }

    @Override
    public List<Disciplina> buscarTodasEntidades() {
        return diciplinaRepository.findAll();
    }

    @Override
    public Disciplina editarEntidade(Long idDiciplina, Disciplina disciplina) throws GenericRuntimeException {
        isDiciplinaExistePorId(idDiciplina);
        return diciplinaRepository.save(disciplina);
    }

    @Override
    public void deletarEntidadePorId(Long idDiciplina) throws GenericRuntimeException {
        isDiciplinaExistePorId(idDiciplina);
        diciplinaRepository.deleteById(idDiciplina);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade) {
        return diciplinaRepository.existsById(idEntidade);
    }

    @Override
    public Page<Disciplina> buscarTodasEntidades(Pageable paginacao) {
        return diciplinaRepository.findAll(paginacao);
    }

    private void isDiciplinaExistePorId(Long idDiciplina) throws GenericRuntimeException {
        if (!diciplinaRepository.existsById(idDiciplina)) {
            throw new EntidadeNaoEncontradaInfraException("Disciplina não encontrada.");
        }
    }
}
