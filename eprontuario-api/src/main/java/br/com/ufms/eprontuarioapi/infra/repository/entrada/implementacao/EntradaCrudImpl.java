package br.com.ufms.eprontuarioapi.infra.repository.entrada.implementacao;

import br.com.ufms.eprontuarioapi.domain.entrada.crud.EntradaCrudInterface;
import br.com.ufms.eprontuarioapi.domain.entrada.entity.Entrada;
import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.infra.repository.entrada.repositorio.EntradaRepository;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNulaInfraException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class EntradaCrudImpl implements EntradaCrudInterface {

    @Autowired
    private EntradaRepository entradaRepository;

    @Override
    public Entrada salvarEntidade(Entrada entidade) throws GenericRuntimeException {
        validarEntrada(entidade);
        return entradaRepository.save(entidade);
    }

    @Override
    public Entrada editarEntidade(Long idEntidade, Entrada entidade) throws GenericRuntimeException {
        validarIdEntrada(idEntidade);
        validarEntrada(entidade);
        return entradaRepository.save(entidade);
    }

    @Override
    public Optional<Entrada> buscarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdEntrada(idEntidade);
        return entradaRepository.findById(idEntidade);
    }

    @Override
    public Page<Entrada> buscarTodasEntidades(Pageable paginacao) {
        return entradaRepository.findAll(paginacao);
    }

    @Override
    public List<Entrada> buscarTodasEntidades() {
        return entradaRepository.findAll();
    }

    @Override
    public void deletarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdEntrada(idEntidade);
        entradaRepository.deleteById(idEntidade);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade) {
        return entradaRepository.existsById(idEntidade);
    }

    private void validarEntrada(Entrada entidade) throws GenericRuntimeException {
        if (isNull(entidade)) {
            throw new EntidadeNulaInfraException("não pode ser salvo Entrada Nula.");
        }
    }

    private void validarIdEntrada(Long idEntidade) throws GenericRuntimeException {
        if (!entradaRepository.existsById(idEntidade)) {
            throw new EntidadeNaoEncontradaInfraException("Entrada não encontrada.");
        }
    }
}
