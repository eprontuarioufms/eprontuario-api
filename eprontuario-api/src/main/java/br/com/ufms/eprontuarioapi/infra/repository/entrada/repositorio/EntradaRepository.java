package br.com.ufms.eprontuarioapi.infra.repository.entrada.repositorio;

import br.com.ufms.eprontuarioapi.domain.entrada.entity.Entrada;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository  extends GenericRepository<Entrada, Long> {
}
