package br.com.ufms.eprontuarioapi.infra.repository.valorfixo.repositorio;


import br.com.ufms.eprontuarioapi.domain.valorfixo.entity.ValorFixo;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValorFixoRepository extends GenericRepository<ValorFixo, Long> {

    List<ValorFixo> findByChaveOrderByValorAsc(String chave);

    boolean existsByChaveAndValor(String chave, String valor);
}
