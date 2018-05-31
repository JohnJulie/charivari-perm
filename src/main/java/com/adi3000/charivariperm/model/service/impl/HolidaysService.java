package com.adi3000.charivariperm.model.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.adi3000.charivariperm.model.dao.impl.HolidaysDao;
import com.adi3000.charivariperm.model.dataobject.Holidays;
import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.charivariperm.model.dataobject.Scheduling;
import com.adi3000.charivariperm.model.enumeration.PermanenceStatus;
import com.adi3000.common.CharivariUtil;
import com.adi3000.common.orm.dao.DAOException;
import com.adi3000.common.orm.spring.TransactionalReadOnly;
import com.adi3000.common.orm.spring.TransactionalUpdate;

@Service("holidaysService")
public class HolidaysService implements com.adi3000.charivariperm.model.service.HolidaysService {
	
	@Inject
	private HolidaysDao dao;
	
	@TransactionalUpdate
    public long saveHolidays(Holidays holidays) {
		long id = Long.MAX_VALUE;
        try {
			id = (long) dao.save(holidays);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
    }
 
	@TransactionalReadOnly
    public List<Holidays> findAllHolidays() {
        return dao.findAll();
    }
 
	@TransactionalUpdate
    public void deleteHolidaysById(Long id) {
        try {
			dao.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @TransactionalReadOnly
    public Holidays findById(Long id) {
        return dao.find(id);
    }
 
    @TransactionalUpdate
    public void updateHolidays(Holidays holidays){
        try {
			dao.update(holidays);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @TransactionalUpdate
    public void generateHolidaysFromDates(LocalDateTime start, LocalDateTime end) {
    	Date startToDate = CharivariUtil.getDateFromLocalDateTime(start);
    	Date endToDate = CharivariUtil.getDateFromLocalDateTime(end);
    	Holidays holidays = new Holidays();
    	holidays.setStartDate(startToDate);
    	holidays.setEndDate(endToDate);
    	long id = Long.MAX_VALUE;
    	try {
			id = (long) dao.save(holidays);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
