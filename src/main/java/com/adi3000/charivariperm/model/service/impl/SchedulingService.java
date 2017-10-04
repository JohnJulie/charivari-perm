package com.adi3000.charivariperm.model.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.adi3000.charivariperm.model.dao.impl.SchedulingDao;
import com.adi3000.charivariperm.model.dataobject.Scheduling;
import com.adi3000.common.orm.dao.DAOException;
import com.adi3000.common.orm.spring.TransactionalReadOnly;
import com.adi3000.common.orm.spring.TransactionalUpdate;

@Service("schedulingService")
public class SchedulingService implements com.adi3000.charivariperm.model.service.SchedulingService {

	@Inject
    private SchedulingDao dao;
    
	@TransactionalUpdate
    public long saveScheduling(Scheduling scheduling) {
		long id = Long.MAX_VALUE;
        try {
			id = (long) dao.save(scheduling);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return id;
    }
 
	@TransactionalReadOnly
    public List<Scheduling> findAllSchedulings() {
        return dao.findAll();
    }
 
    @TransactionalUpdate
    public void deleteSchedulingById(Long id) {
        try {
			dao.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @TransactionalReadOnly
    public Scheduling findById(Long id) {
        return dao.find(id);
    }
 
    @TransactionalUpdate
    public void updateScheduling(Scheduling scheduling){
        try {
			dao.update(scheduling);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
