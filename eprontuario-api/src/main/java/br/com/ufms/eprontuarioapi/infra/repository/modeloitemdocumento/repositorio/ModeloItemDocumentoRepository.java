package br.com.ufms.eprontuarioapi.infra.repository.modeloitemdocumento.repositorio;


import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.entity.ModeloItemDocumento;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloItemDocumentoRepository extends GenericRepository<ModeloItemDocumento, Long> {

//    @Query("SELECT mid FROM ModeloItemDocumento mid WHERE mid.titulo = :tituloDocumento AND mid.titulo = :tituloItemDocumento")
//    List<ModeloItemDocumento> buscarTodosControlesPorTituloDocumentETituloItemDocumento(@Param("tituloDocumento") String tituloDocumento,
//                                                                                        @Param("tituloItemDocumento") String tituloItemDocumento);

    List<ModeloItemDocumento> findAllByTitulo(String tituloDocumento);//todo usar lower

//    Page<ModeloItemDocumento> findAllByStatus(EStatus status, Pageable paginacao);

//    Page<ModeloItemDocumento> findAllByStatusIsNotLike(EStatus status, Pageable paginacao);
}
