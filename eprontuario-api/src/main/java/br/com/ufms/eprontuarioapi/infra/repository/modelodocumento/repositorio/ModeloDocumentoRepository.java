package br.com.ufms.eprontuarioapi.infra.repository.modelodocumento.repositorio;

import br.com.ufms.eprontuarioapi.domain.modelodocumento.entity.ModeloDocumento;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloDocumentoRepository extends GenericRepository<ModeloDocumento, Long> {

    @Query("SELECT md FROM ModeloDocumento md WHERE LOWER(md.titulo) IN (SELECT LOWER(doc.titulo) FROM Documento doc)")
    boolean isModeloItemDocumentoEstaEmUsoEmAlgumModeloDocumento(Long idModeloDocumento);// todo arrumar consulta id não usado

    @Query("SELECT md FROM ModeloDocumento md WHERE LOWER(:titulo) IN (SELECT LOWER(doc.titulo) FROM Documento doc)")
    boolean isExisteDocumentoComMesmoTituloIgnoreCase(@Param("titulo") String titulo);
}
