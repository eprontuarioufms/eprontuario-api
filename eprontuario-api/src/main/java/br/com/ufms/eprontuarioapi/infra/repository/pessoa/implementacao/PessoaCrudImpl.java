package br.com.ufms.eprontuarioapi.infra.repository.pessoa.implementacao;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.pessoa.crud.PessoaCrudInterface;
import br.com.ufms.eprontuarioapi.domain.pessoa.entity.Pessoa;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNulaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.pessoa.repositorio.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

public class PessoaCrudImpl implements PessoaCrudInterface {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa salvarEntidade(Pessoa entidade) throws GenericRuntimeException {
        validarPessoa(entidade);
        return pessoaRepository.save(entidade);
    }

    @Override
    public Pessoa editarEntidade(Long idEntidade, Pessoa entidade) throws GenericRuntimeException {
        validarIdPessoa(idEntidade);
        validarPessoa(entidade);
        return pessoaRepository.save(entidade);
    }

    @Override
    public Optional<Pessoa> buscarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdPessoa(idEntidade);
        return pessoaRepository.findById(idEntidade);
    }

    @Override
    public Page<Pessoa> buscarTodasEntidades(Pageable paginacao) {
        return pessoaRepository.findAll(paginacao);
    }

    @Override
    public List<Pessoa> buscarTodasEntidades() {
        return pessoaRepository.findAll();
    }

    @Override
    public void deletarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdPessoa(idEntidade);
        pessoaRepository.deleteById(idEntidade);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade) {
        return pessoaRepository.existsById(idEntidade);
    }

    private void validarPessoa(Pessoa entidade) throws GenericRuntimeException {
        if (isNull(entidade)) {
            throw new EntidadeNulaInfraException("Não pode ser salvo Pessoa Nula.");
        }
    }

    private void validarIdPessoa(Long idEntidade) throws GenericRuntimeException {
        if (!pessoaRepository.existsById(idEntidade)) {
            throw new EntidadeNaoEncontradaInfraException("Pessoa não encontrada.");
        }
    }
}
