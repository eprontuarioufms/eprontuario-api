package br.com.ufms.eprontuarioapi.domain.modelodocumento.crud;

import br.com.ufms.eprontuarioapi.domain.generic.crud.GenericCrudInterface;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.entity.ModeloDocumento;

public interface ModeloDocumentoCrudInterface extends GenericCrudInterface<ModeloDocumento, Long> {

    boolean isModeloItemDocumentoEstaEmUsoEmAlgumModeloDocumento(Long idModeloDocumento);

    boolean isExisteDocumentoComMesmoTitulo(String tituloDocumento);
}
