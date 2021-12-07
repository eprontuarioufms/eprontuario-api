package br.com.ufms.eprontuarioapi.infra.repository.matricula.repositorio;

import br.com.ufms.eprontuarioapi.domain.matricula.entity.Matricula;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends GenericRepository<Matricula, Long> {

}
