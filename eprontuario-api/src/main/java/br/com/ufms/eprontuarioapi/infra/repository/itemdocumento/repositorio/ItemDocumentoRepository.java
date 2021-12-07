package br.com.ufms.eprontuarioapi.infra.repository.itemdocumento.repositorio;

import br.com.ufms.eprontuarioapi.domain.itemdocumento.entity.ItemDocumento;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDocumentoRepository extends GenericRepository<ItemDocumento, Long> {

    void deleteByDocumento_Id(Long idDocumento);

    List<ItemDocumento> findAllByDocumento_Id(Long idDocumento);
}
