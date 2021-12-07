package br.com.ufms.eprontuarioapi.domain.documento.crud;

import br.com.ufms.eprontuarioapi.domain.documento.entity.Documento;
import br.com.ufms.eprontuarioapi.domain.generic.crud.GenericCrudInterface;

import java.util.List;

public interface DocumentoCrudInterface extends GenericCrudInterface<Documento, Long> {

    boolean existeDocumentoComMesmoIdDeAcademico(Long idAcademico);

    boolean isPacienteEstaSendoUsadoEmAlgumDocumento(Long idPacidente);

    List<Documento> buscarTodosDocumentosPorIdDaEntrada(Long idEntrada);
}
