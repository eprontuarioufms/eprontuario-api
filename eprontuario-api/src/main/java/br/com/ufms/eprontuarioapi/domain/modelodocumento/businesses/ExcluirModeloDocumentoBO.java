package br.com.ufms.eprontuarioapi.domain.modelodocumento.businesses;

import br.com.ufms.eprontuarioapi.domain.documento.enumeration.EStatus;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.crud.ModeloDocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.entity.ModeloDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExcluirModeloDocumentoBO {

    @Autowired
    private ModeloDocumentoCrudInterface documentoCrudInterface;

    public void executar(Long idModeloDocumento)  {
        if (isModeloDocumentoEstaSendoUsado(idModeloDocumento)) {
            mudarStatusDoControleItemDocumentoParaExcluido(idModeloDocumento);
        } else {
            documentoCrudInterface.deletarEntidadePorId(idModeloDocumento);
        }
    }

    private void mudarStatusDoControleItemDocumentoParaExcluido(Long idModeloDocumento)  {
        Optional<ModeloDocumento> optionalModeloDocumento =  documentoCrudInterface.buscarEntidadePorId(idModeloDocumento);

        if (optionalModeloDocumento.isPresent()){
            ModeloDocumento modeloDocumento = optionalModeloDocumento.get();
            modeloDocumento.setStatus(EStatus.EXCLUIDO);
            documentoCrudInterface.salvarEntidade(modeloDocumento);
        }
    }

    private boolean isModeloDocumentoEstaSendoUsado(Long idModeloDocumento) {
        return documentoCrudInterface.isModeloItemDocumentoEstaEmUsoEmAlgumModeloDocumento(idModeloDocumento);
    }

}
