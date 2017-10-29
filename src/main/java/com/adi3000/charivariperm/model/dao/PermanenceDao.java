package com.adi3000.charivariperm.model.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.charivariperm.model.enumeration.PermanenceStatus;
import com.adi3000.common.orm.dao.DAO;


public interface PermanenceDao extends DAO<Permanence> {

	public List<Permanence> getPermanenceByDate(Date date);
	
	public List<Permanence> getPermanenceByStatus(PermanenceStatus status);
	
	public List<Permanence> getPermanenceByWeek(Date startDate, Date endDate);
    
}
