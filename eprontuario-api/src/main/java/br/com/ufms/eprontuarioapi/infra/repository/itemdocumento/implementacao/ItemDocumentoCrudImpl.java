package br.com.ufms.eprontuarioapi.infra.repository.itemdocumento.implementacao;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.crud.ItemDocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.entity.ItemDocumento;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNulaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.itemdocumento.repositorio.ItemDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ItemDocumentoCrudImpl implements ItemDocumentoCrudInterface {

    @Autowired
    private ItemDocumentoRepository itemDocumentoRepository;

    @Override
    public ItemDocumento salvarEntidade(ItemDocumento entidade) throws GenericRuntimeException {
        validarItemDocumento(entidade);
        return itemDocumentoRepository.save(entidade);
    }

    @Override
    public ItemDocumento editarEntidade(Long idEntidade, ItemDocumento entidade) throws GenericRuntimeException {
        validarIdItemDocumento(idEntidade);
        validarItemDocumento(entidade);
        return itemDocumentoRepository.save(entidade);
    }

    @Override
    public Optional<ItemDocumento> buscarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdItemDocumento(idEntidade);
        return itemDocumentoRepository.findById(idEntidade);
    }

    @Override
    public Page<ItemDocumento> buscarTodasEntidades(Pageable paginacao) {
        return itemDocumentoRepository.findAll(paginacao);
    }

    @Override
    public List<ItemDocumento> buscarTodasEntidades() {
        return itemDocumentoRepository.findAll();
    }

    @Override
    public void deletarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdItemDocumento(idEntidade);
        itemDocumentoRepository.deleteById(idEntidade);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade) {
        return itemDocumentoRepository.existsById(idEntidade);
    }

    private void validarItemDocumento(ItemDocumento entidade) throws GenericRuntimeException {
        if (isNull(entidade)) {
            throw new EntidadeNulaInfraException("não pode ser salvo Item Documento Nulo.");
        }
    }

    private void validarIdItemDocumento(Long idEntidade) throws GenericRuntimeException {
        if (!itemDocumentoRepository.existsById(idEntidade)) {
            throw new EntidadeNaoEncontradaInfraException("Item Documento não encontrado.");
        }
    }

    @Override
    public List<ItemDocumento> buscarTodosItemDocumentoComMesmoIdDeDocumento(Long idDocumento) {
        return itemDocumentoRepository.findAllByDocumento_Id(idDocumento);
    }

    @Override
    public void salvarTodosItensDocumentos(List<ItemDocumento> itensDocumentos) {
        itemDocumentoRepository.saveAll(itensDocumentos);
    }

    @Override
    public List<ItemDocumento> buscarTodosItensDocumentosPorIdDocumento(Long idDocumento) {
        return itemDocumentoRepository.findAllByDocumento_Id(idDocumento);
    }

    @Override
    public void deletarTodosItensComMesmoIdDocumento(Long idDocumento) {
        itemDocumentoRepository.deleteByDocumento_Id(idDocumento);
    }
}
