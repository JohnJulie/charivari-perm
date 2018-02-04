package com.adi3000.charivariperm.model.service.impl;

import org.springframework.stereotype.Service;

import com.adi3000.charivariperm.model.dao.AdultDao;
import com.adi3000.charivariperm.model.dataobject.Adult;
import com.adi3000.common.orm.dao.DAOException;
import com.adi3000.common.orm.spring.TransactionalReadOnly;
import com.adi3000.common.orm.spring.TransactionalUpdate;

import java.util.List;

import javax.inject.Inject;

@Service("adultService")
public class AdultService implements com.adi3000.charivariperm.model.service.AdultService {
	
	@Inject
	private AdultDao dao;
	
	@TransactionalUpdate
    public long saveAdult(Adult adult) {
		long id = Long.MAX_VALUE;
        try {
			id = (long) dao.save(adult);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return id;
    }
 
	@TransactionalReadOnly
    public List<Adult> findAllAdults() {
        return (List<Adult>) dao.findAll();
    }
 
    @TransactionalUpdate
    public void deleteAdultById(Long id) {
        try {
			dao.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @TransactionalReadOnly
    public Adult findById(Long id) {
        return dao.find(id);
    }
 
    @TransactionalUpdate
    public void updateAdult(Adult adult){
        try {
			dao.update(adult);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
