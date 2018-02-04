package com.adi3000.charivariperm.model.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.model.dao.impl.PermanenceDao;
import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.charivariperm.model.dataobject.Scheduling;
import com.adi3000.charivariperm.model.enumeration.PermanenceStatus;
import com.adi3000.common.CharivariUtil;
import com.adi3000.common.orm.dao.DAOException;
import com.adi3000.common.orm.spring.TransactionalReadOnly;
import com.adi3000.common.orm.spring.TransactionalUpdate;

@Service("permanenceService")
public class PermanenceService implements com.adi3000.charivariperm.model.service.PermanenceService {
	
	@Inject
    private PermanenceDao dao;
	
	@Inject
	private SchedulingService schedulingService;
     
	@TransactionalUpdate
    public long savePermanence(Permanence permanence) {
    	long id = Long.MAX_VALUE;
        try {
        	permanence.setOriginalFamilyId(permanence.getFamily().getId());
        	if (permanence.getStatus() == null) {
            	permanence.setStatus(PermanenceStatus.NOT_CONFIRMED);
        	}
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
    	LocalDateTime now = LocalDateTime.now();
    	// LocalDateTime now = LocalDate.of(2017, 8, 29).atTime(10, 15);
    	return dao.getPermanenceByDate(CharivariUtil.getDateFromLocalDateTime(now));
    }
    
    @TransactionalReadOnly
    public List<Permanence> getEmptyPermanences() {
    	PermanenceStatus toReplace = PermanenceStatus.HELP;
    	return dao.getPermanenceByStatus(toReplace);
    }
    
    @TransactionalReadOnly
    public List<Permanence> getWeekPermanences(LocalDateTime start, LocalDateTime end) {
    	Date startToDate = CharivariUtil.getDateFromLocalDateTime(start);
    	Date endToDate = CharivariUtil.getDateFromLocalDateTime(end);
    	return dao.getPermanenceByWeek(startToDate, endToDate);
    }
    
    @TransactionalUpdate
    public void generatePermanencesFamily(Long schedulingId){
    	LocalDateTime endPermanence = LocalDate.of(2018, 7, 29).atTime(19,0);
    	Scheduling scheduling = this.schedulingService.findById(schedulingId);
    	LocalDateTime lastDate = CharivariUtil.getLocalDateTimeFromDate(scheduling.getStartHour());
    	while (endPermanence.isAfter(lastDate)) {
    		Permanence perm = new Permanence();
    		perm.setFamily(scheduling.getFamily());
    		perm.setStatus(PermanenceStatus.NOT_CONFIRMED);
    		perm.setStartDate(CharivariUtil.getDateFromLocalDateTime(lastDate));
    		perm.setEndDate(CharivariUtil.getDateFromLocalDateTime(lastDate.plusMinutes(scheduling.getDuration())));
    		this.savePermanence(perm);

    		lastDate = lastDate.plusDays(scheduling.getFrequency());
    	}
    }
    
    @TransactionalReadOnly
    public List<Permanence> getReplacement() {
    	return dao.getReplacementPermanence();
    }
    
    @TransactionalReadOnly
    public List<Permanence> getPermanenceToReplace(LocalDate dateToReplace, String slot) {
    	Date startToDate;
    	Date endToDate; 
    	if (slot.equals("0")) {
    		startToDate = CharivariUtil.getDateFromLocalDateTime(dateToReplace.atTime(7, 45));
    		endToDate = CharivariUtil.getDateFromLocalDateTime(dateToReplace.atTime(10, 45));
    	} else if (slot.equals("1")) {
    		startToDate = CharivariUtil.getDateFromLocalDateTime(dateToReplace.atTime(10, 00));
    		endToDate = CharivariUtil.getDateFromLocalDateTime(dateToReplace.atTime(13, 00));
    	} else {
    		startToDate = CharivariUtil.getDateFromLocalDateTime(dateToReplace.atTime(15, 30));
    		endToDate = CharivariUtil.getDateFromLocalDateTime(dateToReplace.atTime(18, 30));
    	}
    	return dao.getPermanenceBySlot(startToDate, endToDate);
    }
}
