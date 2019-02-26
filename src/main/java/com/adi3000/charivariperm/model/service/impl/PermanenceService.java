package com.adi3000.charivariperm.model.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Service;

import com.adi3000.charivariperm.model.dao.impl.PermanenceDao;
import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.charivariperm.model.dataobject.Scheduling;
import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.charivariperm.model.dataobject.Holidays;
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
	@Inject
	private FamilyService familyService;
	@Inject
	private HolidaysService holidaysService; 
     
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
	
	@TransactionalUpdate
    public long flyingPermanence(Long nobodyFamilyId, Long familyId, LocalDateTime startDate) {
		Family family = this.familyService.findById(familyId);
		Permanence perm = new Permanence();
		perm.setOriginalFamilyId(nobodyFamilyId);
		perm.setFamily(family);
		perm.setStatus(PermanenceStatus.REPLACEMENT);
		perm.setIsOpen(true);
		perm.setStartDate(CharivariUtil.getDateFromLocalDateTime(startDate));
		perm.setEndDate(CharivariUtil.getDateFromLocalDateTime(startDate.plusMinutes(180)));
		return this.savePermanence(perm);
    }
 
	@TransactionalReadOnly
    public List<Permanence> findAllPermanences() {
        return dao.findAll();
    }
	
	@TransactionalReadOnly
    public List<Permanence> findAllOpenedPermanences() {
        return dao.getOpenedPermanences();
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
        	Logger.getGlobal().info("upadate perm " + permanence.getStartDate().toString());
			dao.update(permanence);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @TransactionalReadOnly
    public List<Permanence> getCurrentPermanences() {
    	LocalDateTime now = LocalDateTime.now();
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
    	List<Holidays> holidays = this.holidaysService.findAllHolidays();
    	Scheduling scheduling = this.schedulingService.findById(schedulingId);
    	Family family = this.familyService.findById(scheduling.getFamily().getId());
    	LocalDateTime endPermanence = CharivariUtil.getLocalDateTimeFromDate(family.getEndDateContract());
    	LocalDateTime lastDate = CharivariUtil.getLocalDateTimeFromDate(scheduling.getStartHour());
    	while (endPermanence.isAfter(lastDate)) {
    		boolean onHolidays = false;
    		for(Holidays holiday : holidays ) {
    			LocalDateTime startHoliday = CharivariUtil.getLocalDateTimeFromDate(holiday.getStartDate());
    			LocalDateTime endHoliday = CharivariUtil.getLocalDateTimeFromDate(holiday.getEndDate());
    			if (
    					(lastDate.isAfter(startHoliday) && lastDate.isBefore(endHoliday)) ||
    					(lastDate.plusMinutes(scheduling.getDuration()).isAfter(startHoliday) && lastDate.plusMinutes(scheduling.getDuration()).isBefore(endHoliday))
    					) {
    				onHolidays = true;
    			}
    		}
    		if (!onHolidays) {
    			Permanence perm = new Permanence();
        		perm.setFamily(family);
        		perm.setStatus(PermanenceStatus.NOT_CONFIRMED);
        		perm.setIsOpen(true);
        		perm.setStartDate(CharivariUtil.getDateFromLocalDateTime(lastDate));
        		perm.setEndDate(CharivariUtil.getDateFromLocalDateTime(lastDate.plusMinutes(scheduling.getDuration())));
        		this.savePermanence(perm);
    		}
    		lastDate = lastDate.plusDays(scheduling.getFrequency());
    	}
    }
    
    @TransactionalReadOnly
    public List<LocalDate> getNotClosedPermanences() {
    	List<Permanence> permanenceNotClosed = dao.getOpenedPermanences();
    	List<LocalDate> monthToValidate = new ArrayList<>();
    	LocalDate date = null;
    	LocalDate currentDate = LocalDate.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/YYYY");
    	for (Permanence permanence : permanenceNotClosed) {
    		date = CharivariUtil.getLocalDateTimeFromDate(permanence.getStartDate()).toLocalDate();
    		if (date.getMonthValue() != currentDate.getMonthValue()) {
    			boolean onList = false;
    			for (LocalDate dateOnList : monthToValidate) {
    				if (dateOnList.format(formatter).equals(date.format(formatter))) {
    					onList = true;
    				}
    			}
    			if (!onList) {
    				monthToValidate.add(date);
    			}
    		}
		}
    	// Create HashSet from ArrayList.
        HashSet<LocalDate> set = new HashSet<>(monthToValidate);

        // Create ArrayList from the set.
        ArrayList<LocalDate> toReturn = new ArrayList<>(set);
    	return toReturn;
    }
    
    @TransactionalReadOnly
    public List<Permanence> getReplacement(Long nobodyId) {
    	return dao.getReplacementPermanence(nobodyId);
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
    
    @TransactionalUpdate
    public void validateMonthPermanences(LocalDate date) {
    	Integer year = date.getYear();
    	Integer month = date.getMonthValue();
    	LocalDate firstDay = LocalDate.of(year, month, 1);
    	Date firstDayOfMonth = CharivariUtil.getDateFromLocalDateTime(LocalDate.of(year, month, 1).atTime(0, 0));
    	Date lastDayOfMonth = CharivariUtil.getDateFromLocalDateTime(LocalDate.of(year, month, firstDay.lengthOfMonth()).atTime(23, 59));
    	List<Permanence> toUpdate = dao.getPermanenceByDate(firstDayOfMonth, lastDayOfMonth);
    	for (Permanence permanence : toUpdate) {
			permanence.setIsOpen(false);
			updatePermanence(permanence);
		}
    }
    
    @TransactionalReadOnly
    public List<Permanence> getPermCountByFamily(Long familyId) {
    	Family family = this.familyService.findById(familyId);
    	return dao.getPermanencesByFamily(family);
    }
    
}
