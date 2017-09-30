package com.adi3000.common.orm.dao;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * @author adi
 *
 * @param <T>
 */
public interface DAO<T> extends Serializable {

    public T find(Serializable id);

    public void delete(Serializable id) throws DAOException;

    public void delete(T data) throws DAOException;

    public Serializable save(T data) throws DAOException;

    public void saveOrUpdate(T data) throws DAOException;

    public void update(T data) throws DAOException;

    public void delete(Collection<T> data) throws DAOException;

    public Collection<Serializable> save(Collection<T> collection) throws DAOException;

    public void saveOrUpdate(Collection<T> collection) throws DAOException;

    public void update(Collection<T> collection) throws DAOException;

    public Collection<T> findAll();

}