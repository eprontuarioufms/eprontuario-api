package br.com.ufms.eprontuarioapi.infra.repository.documento.repositorio;


import br.com.ufms.eprontuarioapi.domain.documento.entity.Documento;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends GenericRepository<Documento, Long> {

    @Query("SELECT doc FROM Documento doc WHERE doc.entrada.id = :idEntrada")
    List<Documento> buscarTodosDocumentosPorIdEntrada(@Param("idEntrada") Long idEntrada);

    List<Documento> findAllByEntrada_Id(Long id);

    boolean existsAllByPaciente_Id(Long id);

    boolean existsByAcademico_Id(Long id);

}
