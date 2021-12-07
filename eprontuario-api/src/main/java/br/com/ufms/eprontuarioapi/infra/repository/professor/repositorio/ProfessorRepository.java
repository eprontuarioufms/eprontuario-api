package br.com.ufms.eprontuarioapi.infra.repository.professor.repositorio;

import br.com.ufms.eprontuarioapi.domain.professor.entity.Professor;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends GenericRepository<Professor, Long> {
}
