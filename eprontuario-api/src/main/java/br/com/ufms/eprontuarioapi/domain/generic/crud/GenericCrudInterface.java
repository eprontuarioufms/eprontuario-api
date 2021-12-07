package br.com.ufms.eprontuarioapi.domain.generic.crud;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Interface base para Manter Entidades
 *
 * @param <I>  IdType ou tipo do atributo id
 * @param <E> Entity ou entidade da aplicação
 */
public interface GenericCrudInterface <E, I extends Serializable>{

    E salvarEntidade(E entidade) throws GenericRuntimeException;

    E editarEntidade(I idEntidade, E entidade) throws GenericRuntimeException;

    Optional<E> buscarEntidadePorId(I idEntidade) throws GenericRuntimeException;

    Page<E> buscarTodasEntidades(Pageable paginacao);

    List<E> buscarTodasEntidades();

    void deletarEntidadePorId(I idEntidade) throws GenericRuntimeException;

    boolean existeEntidadePorId(I idEntidade);
}
