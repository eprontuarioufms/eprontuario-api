package br.com.ufms.eprontuarioapi.domain.generic.service;


import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericSqlInexistenteRuntimeException;
import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericSqlRuntimeException;
import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
import br.com.ufms.eprontuarioapi.infra.repository.generic.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class GenericController é a classe responsável  por regras de negocio, genéricos e simples como:
 * Inserção em uma base de dados, remoção da base de dados, e update.
 *
 * @param <E></E> Entidade a qual ela ira prestar o servico.
 * @param <R></R> Repositorio responsavel pela E que vc esta utilizando.
 * @param <I>     IdType ou tipo do atributo id
 * @author Eduardo Balan
 */
@MappedSuperclass
public class GenericService<E extends GenericEntity<I>, R extends GenericRepository<E, I>, I extends Serializable> {

    /* Repositorio responsavel pela Entity */
    @Autowired
    private R repository;

    private static final String REGISTRO_NAO_ENCONTRADO = "Registro não encontrado na base de dados.";

    public E inserir(E entityNova) throws GenericSqlRuntimeException {
        try {
            return repository.save(entityNova);
        } catch (Exception e) {
            throw new GenericSqlRuntimeException("Erro ao inserir registro no Banco de Dados.", e);
        }
    }

    public E inserirComValidacao(E entityNova) throws GenericSqlRuntimeException {
        if (Objects.nonNull(entityNova.getId())) {
            throw new GenericRuntimeException("Objeto possui id e não pode ser Inserido.");
        }

        return inserir(entityNova);
    }

    public E editar(E enditadeAEditar) throws GenericSqlRuntimeException {
        try {
            return repository.saveAndFlush(enditadeAEditar);
        } catch (Exception e) {
            throw new GenericSqlRuntimeException("Erro ao editar", e);
        }
    }

    public E editarComValidacao(E entidadeAEditar, I idUrl) throws GenericSqlRuntimeException {
        if (Objects.isNull(entidadeAEditar.getId())) {
            throw new GenericRuntimeException("Objeto não possui id para edição.");
        }

        if (!entidadeAEditar.getId().equals(idUrl)) {
            throw new GenericRuntimeException("Id do Objeto difere da url Id");
        }

        if (!repository.existsById(entidadeAEditar.getId())) {
            throw new GenericSqlInexistenteRuntimeException(REGISTRO_NAO_ENCONTRADO);
        }
        return editar(entidadeAEditar);
    }

    public void excluir(I idEntity) throws GenericSqlRuntimeException {
        if (!repository.existsById(idEntity)) {
            Logger.getGlobal().log(Level.SEVERE, REGISTRO_NAO_ENCONTRADO);
            throw new GenericSqlInexistenteRuntimeException(REGISTRO_NAO_ENCONTRADO);
        }

        try {
            repository.deleteById(idEntity);
        } catch (DataIntegrityViolationException e) {
            throw new GenericSqlRuntimeException("Não é possível realizar a exclusão. O registro possui relacionamentos no sistema.");
        } catch (Exception e) {
            throw new GenericSqlRuntimeException(e);
        }

    }
}
