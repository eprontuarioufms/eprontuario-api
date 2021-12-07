package br.com.ufms.eprontuarioapi.infra.repository.documento.implementacao;

import br.com.ufms.eprontuarioapi.domain.documento.crud.DocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.documento.entity.Documento;
import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.infra.repository.documento.repositorio.DocumentoRepository;
import br.com.ufms.eprontuarioapi.infra.repository.exceptions.EntidadeNaoEncontradaInfraException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoCrudImpl implements DocumentoCrudInterface {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Override
    public Documento salvarEntidade(Documento entidade) {
        return documentoRepository.save(entidade);
    }

    @Override
    public Documento editarEntidade(Long idEntidade, Documento entidade) throws GenericRuntimeException {
        isIdExisteNoBanco(idEntidade);
        return documentoRepository.save(entidade);
    }

    @Override
    public Optional<Documento> buscarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        isIdExisteNoBanco(idEntidade);

        return documentoRepository.findById(idEntidade);
    }

    @Override
    public Page<Documento> buscarTodasEntidades(Pageable paginacao) {
        return documentoRepository.findAll(paginacao);
    }

    @Override
    public List<Documento> buscarTodasEntidades() {
        return documentoRepository.findAll();
    }

    @Override
    public void deletarEntidadePorId(Long idEntidade) throws GenericRuntimeException {
        isIdExisteNoBanco(idEntidade);
        documentoRepository.deleteById(idEntidade);
    }

    @Override
    public boolean existeEntidadePorId(Long idEntidade) {
        return documentoRepository.existsById(idEntidade);
    }


    @Override
    public boolean existeDocumentoComMesmoIdDeAcademico(Long idAcademico) {
        return documentoRepository.existsByAcademico_Id(idAcademico);
    }

    @Override
    public boolean isPacienteEstaSendoUsadoEmAlgumDocumento(Long idPacidente) {
        return documentoRepository.existsByAcademico_Id(idPacidente);
    }

    @Override
    public List<Documento> buscarTodosDocumentosPorIdDaEntrada(Long idEntrada) {
        return documentoRepository.buscarTodosDocumentosPorIdEntrada(idEntrada);
    }


    private void isIdExisteNoBanco(Long idEntidade) throws GenericRuntimeException {
        if (!documentoRepository.existsById(idEntidade)) {
            throw new EntidadeNaoEncontradaInfraException("Documento não encontrado.");
        }
    }
}
