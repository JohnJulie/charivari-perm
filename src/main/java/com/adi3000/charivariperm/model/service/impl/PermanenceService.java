package com.adi3000.charivariperm.model.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.model.dao.impl.PermanenceDao;
import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.common.CharivariUtil;
import com.adi3000.common.orm.dao.DAOException;
import com.adi3000.common.orm.spring.TransactionalReadOnly;
import com.adi3000.common.orm.spring.TransactionalUpdate;

@Service("permanenceService")
public class PermanenceService implements com.adi3000.charivariperm.model.service.PermanenceService {
	
	@Inject
    private PermanenceDao dao;
     
	@TransactionalUpdate
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
 
	@TransactionalReadOnly
    public List<Permanence> findAllPermanences() {
        return dao.findAll();
    }
 
	@TransactionalUpdate
    public void deletePermanenceById(Long id) {
        try {
			dao.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @TransactionalReadOnly
    public Permanence findById(Long id) {
        return dao.find(id);
    }
 
    @TransactionalUpdate
    public void updatePermanence(Permanence permanence){
        try {
			dao.update(permanence);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @TransactionalReadOnly
    public List<Permanence> getCurrentPermanences() {
    	// LocalDateTime now = LocalDateTime.now();
    	LocalDateTime now = LocalDate.of(2017, 8, 29).atTime(10, 15);
    	return dao.getPermanenceByDate(CharivariUtil.getDateFromLocalDateTime(now));
    }
    
}
