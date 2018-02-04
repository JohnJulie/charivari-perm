package com.adi3000.charivariperm.model.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.adi3000.charivariperm.model.dao.ChildDao;
import com.adi3000.charivariperm.model.dataobject.Child;
import com.adi3000.common.orm.dao.DAOException;
import com.adi3000.common.orm.spring.TransactionalReadOnly;
import com.adi3000.common.orm.spring.TransactionalUpdate;

@Service("childService")
public class ChildService implements com.adi3000.charivariperm.model.service.ChildService {
	
	@Inject
	private ChildDao dao;
	
	@TransactionalUpdate
    public long saveChild(Child child) {
		long id = Long.MAX_VALUE;
        try {
			id = (long) dao.save(child);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return id;
    }
 
	@TransactionalReadOnly
    public List<Child> findAllChilds() {
        return (List<Child>) dao.findAll();
    }
 
    @TransactionalUpdate
    public void deleteChildById(Long id) {
        try {
			dao.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @TransactionalReadOnly
    public Child findById(Long id) {
        return dao.find(id);
    }
 
    @TransactionalUpdate
    public void updateChild(Child child){
        try {
			dao.update(child);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
