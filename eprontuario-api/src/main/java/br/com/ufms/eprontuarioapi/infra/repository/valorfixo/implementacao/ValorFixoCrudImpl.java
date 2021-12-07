package br.com.ufms.eprontuarioapi.infra.repository.valorfixo.implementacao;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.valorfixo.crud.ValorFixoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.valorfixo.entity.ValorFixo;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNulaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.valorfixo.repositorio.ValorFixoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ValorFixoCrudImpl implements ValorFixoCrudInterface {

    @Autowired
    private ValorFixoRepository valorFixoRepository;

    @Override
    public ValorFixo salvarEntidade(ValorFixo entidade) throws GenericRuntimeException {
        validarValorFixo(entidade);
        return valorFixoRepository.save(entidade);
    }

    @Override
    public ValorFixo editarEntidade(Long idEntidade, ValorFixo entidade) throws GenericRuntimeException {
        validarIdValorFixo(idEntidade);
        validarValorFixo(entidade);
        return valorFixoRepository.save(entidade);
    }

    @Override
    public Optional<ValorFixo> buscarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdValorFixo(idEntidade);
        return valorFixoRepository.findById(idEntidade);
    }

    @Override
    public Page<ValorFixo> buscarTodasEntidades(Pageable paginacao) {
        return valorFixoRepository.findAll(paginacao);
    }

    @Override
    public List<ValorFixo> buscarTodasEntidades() {
        return valorFixoRepository.findAll();
    }

    @Override
    public void deletarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdValorFixo(idEntidade);
        valorFixoRepository.deleteById(idEntidade);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade) {
        return valorFixoRepository.existsById(idEntidade);
    }

    private void validarValorFixo(ValorFixo entidade) throws GenericRuntimeException {
        if (isNull(entidade)) {
            throw new EntidadeNulaInfraException("não pode ser salvo Valor Fixo Nulo.");
        }
    }

    private void validarIdValorFixo(Long idEntidade) throws GenericRuntimeException {
        if (!valorFixoRepository.existsById(idEntidade)) {
            throw new EntidadeNaoEncontradaInfraException("Valor Fixo não encontrado.");
        }
    }
}
