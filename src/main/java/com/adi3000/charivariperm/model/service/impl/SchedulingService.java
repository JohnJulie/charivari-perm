package com.adi3000.charivariperm.model.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.model.dao.impl.SchedulingDao;
import com.adi3000.charivariperm.model.dataobject.Scheduling;
import com.adi3000.common.orm.dao.DAOException;

@Service("schedulingService")
@Transactional
public class SchedulingService implements com.adi3000.charivariperm.model.service.SchedulingService {

	@Inject
    private SchedulingDao dao;
     
    public void saveScheduling(Scheduling scheduling) {
        try {
			dao.save(scheduling);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public List<Scheduling> findAllSchedulings() {
        return dao.findAll();
    }
 
    public void deleteSchedulingById(Long id) {
        try {
			dao.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public Scheduling findById(Long id) {
        return dao.find(id);
    }
 
    public void updateScheduling(Scheduling scheduling){
        try {
			dao.update(scheduling);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
