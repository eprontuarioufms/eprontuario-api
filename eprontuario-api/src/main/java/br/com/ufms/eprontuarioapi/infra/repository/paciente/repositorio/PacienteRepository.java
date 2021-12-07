package br.com.ufms.eprontuarioapi.infra.repository.paciente.repositorio;


import br.com.ufms.eprontuarioapi.domain.paciente.entity.Paciente;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends GenericRepository<Paciente, Long> {
}
