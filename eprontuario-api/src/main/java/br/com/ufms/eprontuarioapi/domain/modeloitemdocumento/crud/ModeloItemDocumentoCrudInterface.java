package br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.crud;

import br.com.ufms.eprontuarioapi.domain.generic.crud.GenericCrudInterface;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.entity.ModeloItemDocumento;

import java.util.List;

public interface ModeloItemDocumentoCrudInterface extends GenericCrudInterface<ModeloItemDocumento, Long> {

    List<ModeloItemDocumento> buscarTodosModelosPeloTituloDoDocumento(String tituloDocumento);
}
