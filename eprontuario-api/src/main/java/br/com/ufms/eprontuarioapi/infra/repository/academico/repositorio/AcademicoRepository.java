package br.com.ufms.eprontuarioapi.infra.repository.academico.repositorio;

import br.com.ufms.eprontuarioapi.domain.academico.entity.Academico;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicoRepository extends GenericRepository<Academico, Long> {
}
