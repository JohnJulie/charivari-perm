package com.adi3000.charivariperm.model.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.adi3000.charivariperm.model.dao.impl.FamilyDao;
import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.common.orm.dao.DAOException;
import com.adi3000.common.orm.spring.TransactionalReadOnly;
import com.adi3000.common.orm.spring.TransactionalUpdate;

@Service("familyService")
public class FamilyService implements com.adi3000.charivariperm.model.service.FamilyService {
	
	@Inject
    private FamilyDao dao;
    
	@TransactionalUpdate
    public long saveFamily(Family family) {
		long id = Long.MAX_VALUE;
        try {
			id = (long) dao.save(family);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
    }
 
	@TransactionalReadOnly
    public List<Family> findAllFamilies() {
        return dao.findAll();
    }
 
	@TransactionalUpdate
    public void deleteFamilyById(Long id) {
        try {
			dao.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @TransactionalReadOnly
    public Family findById(Long id) {
        return dao.find(id);
    }
 
    @TransactionalUpdate
    public void updateFamily(Family family){
        try {
			dao.update(family);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
