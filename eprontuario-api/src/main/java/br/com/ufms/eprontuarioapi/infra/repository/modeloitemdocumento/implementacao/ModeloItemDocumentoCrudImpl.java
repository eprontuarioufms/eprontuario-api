package br.com.ufms.eprontuarioapi.infra.repository.modeloitemdocumento.implementacao;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.crud.ModeloItemDocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.entity.ModeloItemDocumento;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNulaInfraException;
import br.com.ufms.eprontuarioapi.infra.repository.modeloitemdocumento.repositorio.ModeloItemDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ModeloItemDocumentoCrudImpl implements ModeloItemDocumentoCrudInterface {

    @Autowired
    private ModeloItemDocumentoRepository modeloItemDocumentoRepository;

    @Override
    public ModeloItemDocumento salvarEntidade(ModeloItemDocumento modeloItemDocumento) throws GenericRuntimeException {
        validarModeloItemDocumento(modeloItemDocumento);
        return modeloItemDocumentoRepository.save(modeloItemDocumento);
    }

    @Override
    public Optional<ModeloItemDocumento> buscarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdModeloItemDocumento(idEntidade);
        return modeloItemDocumentoRepository.findById(idEntidade);
    }

    @Override
    public List<ModeloItemDocumento> buscarTodasEntidades() {
        return modeloItemDocumentoRepository.findAll();
    }

    @Override
    public ModeloItemDocumento editarEntidade(Long idEntidade, ModeloItemDocumento modeloItemDocumento) throws GenericRuntimeException {
        validarIdModeloItemDocumento(idEntidade);
        validarModeloItemDocumento(modeloItemDocumento);
        return modeloItemDocumentoRepository.save(modeloItemDocumento);
    }

    @Override
    public void deletarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        validarIdModeloItemDocumento(idEntidade);
        modeloItemDocumentoRepository.deleteById(idEntidade);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade){
        return modeloItemDocumentoRepository.existsById(idEntidade);
    }

    @Override
    public Page<ModeloItemDocumento> buscarTodasEntidades(Pageable paginacao) {
        return modeloItemDocumentoRepository.findAll(paginacao);
    }


    private void validarIdModeloItemDocumento(Long idEntidade) throws GenericRuntimeException {
        if (!modeloItemDocumentoRepository.existsById(idEntidade)) {
            throw new EntidadeNaoEncontradaInfraException("Modelo Item Documento não encontrado.");
        }
    }

    private void validarModeloItemDocumento(ModeloItemDocumento modeloItemDocumento) throws GenericRuntimeException {
        if (isNull(modeloItemDocumento)) {
            throw new EntidadeNulaInfraException("Modelo Item Documento não pode ser Nulo.");
        }
    }

    @Override
    public List<ModeloItemDocumento> buscarTodosModelosPeloTituloDoDocumento(String tituloDocumento) {
        return modeloItemDocumentoRepository.findAllByTitulo(tituloDocumento);
    }
}
