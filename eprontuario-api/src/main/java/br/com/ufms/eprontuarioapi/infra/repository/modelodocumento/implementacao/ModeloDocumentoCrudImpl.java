package br.com.ufms.eprontuarioapi.infra.repository.modelodocumento.implementacao;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.crud.ModeloDocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.entity.ModeloDocumento;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNulaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.modelodocumento.repositorio.ModeloDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ModeloDocumentoCrudImpl implements ModeloDocumentoCrudInterface {

    @Autowired
    private ModeloDocumentoRepository modeloDocumentoRepository;

    @Override
    public ModeloDocumento salvarEntidade(ModeloDocumento modeloDocumento) throws GenericRuntimeException {
        validarModeloDocumento(modeloDocumento);
        return modeloDocumentoRepository.save(modeloDocumento);
    }

    @Override
    public Optional<ModeloDocumento> buscarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdModeloDocumento(idEntidade);
        return modeloDocumentoRepository.findById(idEntidade);
    }

    @Override
    public List<ModeloDocumento> buscarTodasEntidades() {
        return modeloDocumentoRepository.findAll();
    }

    @Override
    public ModeloDocumento editarEntidade(Long idEntidade, ModeloDocumento modeloDocumento) throws GenericRuntimeException {
        validarIdModeloDocumento(idEntidade);
        validarModeloDocumento(modeloDocumento);
        return modeloDocumentoRepository.save(modeloDocumento);
    }

    @Override
    public void deletarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdModeloDocumento(idEntidade);
        modeloDocumentoRepository.deleteById(idEntidade);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade) {
        return modeloDocumentoRepository.existsById(idEntidade);
    }

    @Override
    public Page<ModeloDocumento> buscarTodasEntidades(Pageable paginacao) {
        return modeloDocumentoRepository.findAll(paginacao);
    }


    private void validarIdModeloDocumento(Long idEntidade) throws GenericRuntimeException {
        if (!modeloDocumentoRepository.existsById(idEntidade)) {
            throw new EntidadeNaoEncontradaInfraException("Modelo Documento não encontrado.");
        }
    }

    private void validarModeloDocumento(ModeloDocumento modeloDocumento) throws GenericRuntimeException {
        if (isNull(modeloDocumento)) {
            throw new EntidadeNulaInfraException("Modelo Documento não pode ser Nulo.");
        }
    }

    @Override
    public boolean isModeloItemDocumentoEstaEmUsoEmAlgumModeloDocumento(Long idModeloDocumento) {
        return modeloDocumentoRepository.isModeloItemDocumentoEstaEmUsoEmAlgumModeloDocumento(idModeloDocumento);
    }

    @Override
    public boolean isExisteDocumentoComMesmoTitulo(String tituloDocumento) {
        return modeloDocumentoRepository.isExisteDocumentoComMesmoTituloIgnoreCase(tituloDocumento);
    }
}
