package br.com.ufms.eprontuarioapi.infra.repository.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.io.Serializable;

/**
 * Interface base para Repository
 *
 *
 * @param <I>  IdType ou tipo do atributo id
 * @param <E> Entity ou entidade da aplicação
 */
@NoRepositoryBean
public interface GenericRepository<E, I extends Serializable> extends JpaRepository<E, I>, QueryByExampleExecutor<E>{
}
