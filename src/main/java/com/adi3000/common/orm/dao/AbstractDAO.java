package com.adi3000.common.orm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.adi3000.common.logger.AbstractLogger;




/**
 * 
 * @author adi
 *
 * @param <T>
 */
public class AbstractDAO<T> extends AbstractLogger implements DAO<T>{
    /**
     *
     */
    private static final long serialVersionUID = 8495076397653964860L;
    /**
     *
     */
    @Inject
    private transient SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    private final Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    @Override
    public T find(Serializable id) {
        T data = (T) getSession().get(clazz, id);
        if (data == null) {
            throw new ObjectNotFoundException(id, clazz.getName());
        }
        getLogger().debug("Object of class {} found with id : {}", clazz.getName(), id);
        return data;
    }

    @Override
    public void delete(Collection<T> datas) throws DAOException {
        getLogger().debug("Delete objects of class {}", clazz.getName());
        for (T data : datas) {
            getSession().delete(data);
        }
    }

    @Override
    public void delete(T data) throws DAOException {
        getLogger().debug("Delete object of class {}", clazz.getName());
        getSession().delete(Collections.singleton(data));
    }

    @Override
    public void delete(Serializable id) throws DAOException {
        getLogger().debug("Delete object of class {} with id : {}", clazz.getName(), id);
        getSession().delete(getSession().load(clazz, id));
    }

    @Override
    public Collection<Serializable> save(Collection<T> collection) throws DAOException {
        getLogger().debug("Save objects of class {}", clazz.getName());
        List<Serializable> collectionsId = new ArrayList<>();
        for (T dtObject : collection) {
                collectionsId.add(getSession().save(dtObject));
        }
        return collectionsId;
    }

    @Override
    public void saveOrUpdate(Collection<T> collection) throws DAOException {
        getLogger().debug("Save and/or update objects of class {}", clazz.getName());
        for (T dtObject : collection) {
            getSession().saveOrUpdate(dtObject);
        }
    }

    @Override
    public void update(Collection<T> collection) throws DAOException {
        getLogger().debug("Update objects of class {}", clazz.getName());
        for (T dtObject : collection) {
                getSession().update(dtObject);
        }
    }

    @Override
    public Serializable save(T data) throws DAOException {
        getLogger().debug("Save object of class {}", clazz.getName());
        return getSession().save(data);
    }

    @Override
    public void saveOrUpdate(T data) throws DAOException {
        getLogger().debug("Save and/or update object of class {}", clazz.getName());
        saveOrUpdate(Collections.singleton(data));
    }

    @Override
    public void update(T data) throws DAOException {
        getLogger().debug("Update object of class {}", clazz.getName());
        update(Collections.singleton(data));
    }

    @Override
    public List<T> findAll() {
        getLogger().debug("Find all objects of class {}", clazz.getName());
        List<T> dtObjects = getSession().createCriteria(clazz)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return dtObjects;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Criteria createCriteria(){
    	return getSession().createCriteria(getClazz());
    }

    /**
	 *
	 * @return the class used by this {@link DAO}
	 */
	public Class<T> getClazz() {
		return clazz;
	}
}