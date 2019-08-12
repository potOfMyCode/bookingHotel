package model.dao;

import model.dao.exception.DaoException;

import java.util.List;

public interface GenericDao<E, K> extends AutoCloseable {

    List<E> getAll();

    E update(E entity) throws DaoException;

    E getEntityById(K id) throws DaoException;

    void delete(K id) throws DaoException;

    void create(E entity) throws DaoException;

    @Override
    void close();
}
