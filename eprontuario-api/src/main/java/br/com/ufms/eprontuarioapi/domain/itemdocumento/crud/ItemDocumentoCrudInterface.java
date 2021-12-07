package br.com.ufms.eprontuarioapi.domain.itemdocumento.crud;

import br.com.ufms.eprontuarioapi.domain.generic.crud.GenericCrudInterface;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.entity.ItemDocumento;

import java.util.List;

public interface ItemDocumentoCrudInterface  extends GenericCrudInterface<ItemDocumento, Long> {

    List<ItemDocumento> buscarTodosItemDocumentoComMesmoIdDeDocumento(Long idDocumento);

    void salvarTodosItensDocumentos(List<ItemDocumento> itensDocumentos);

    List<ItemDocumento> buscarTodosItensDocumentosPorIdDocumento(Long idDocumento);

    void deletarTodosItensComMesmoIdDocumento(Long idDocumento);
}
