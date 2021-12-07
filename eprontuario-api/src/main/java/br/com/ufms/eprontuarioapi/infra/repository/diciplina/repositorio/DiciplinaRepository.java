package br.com.ufms.eprontuarioapi.infra.repository.diciplina.repositorio;

import br.com.ufms.eprontuarioapi.domain.disciplina.entity.Disciplina;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiciplinaRepository extends GenericRepository<Disciplina, Long> {



}
