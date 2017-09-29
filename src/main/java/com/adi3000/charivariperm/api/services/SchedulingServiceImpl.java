package com.adi3000.charivariperm.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.api.dao.SchedulingDao;
import com.adi3000.charivariperm.api.models.Scheduling;

@Service("schedulingService")
@Transactional
public class SchedulingServiceImpl implements SchedulingService {

	@Autowired
    private SchedulingDao dao;
     
    public void saveScheduling(Scheduling scheduling) {
        dao.saveScheduling(scheduling);
    }
 
    public List<Scheduling> findAllSchedulings() {
        return dao.findAllSchedulings();
    }
 
    public void deleteSchedulingById(Long id) {
        dao.deleteSchedulingById(id);
    }
 
    public Scheduling findById(Long id) {
        return dao.findById(id);
    }
 
    public void updateScheduling(Scheduling scheduling){
        dao.updateScheduling(scheduling);
    }
}
