package com.adi3000.charivariperm.model.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.model.dao.impl.PermanenceDao;
import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.common.orm.dao.DAOException;

@Service("permanenceService")
@Transactional
public class PermanenceService implements com.adi3000.charivariperm.model.service.PermanenceService {
	
	@Inject
    private PermanenceDao dao;
     
    public long savePermanence(Permanence permanence) {
    	long id = Long.MAX_VALUE;
        try {
			id = (long) dao.save(permanence);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return id;
    }
 
    public List<Permanence> findAllPermanences() {
        return dao.findAll();
    }
 
    public void deletePermanenceById(Long id) {
        try {
			dao.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public Permanence findById(Long id) {
        return dao.find(id);
    }
 
    public void updatePermanence(Permanence permanence){
        try {
			dao.update(permanence);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
