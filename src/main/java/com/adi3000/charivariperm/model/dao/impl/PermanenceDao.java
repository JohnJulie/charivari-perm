package com.adi3000.charivariperm.model.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.charivariperm.model.enumeration.PermanenceStatus;
import com.adi3000.common.CharivariUtil;
import com.adi3000.common.orm.dao.AbstractDAO;

@Repository("permanenceDao")
public class PermanenceDao extends AbstractDAO<Permanence>  implements com.adi3000.charivariperm.model.dao.PermanenceDao {

	private static final long serialVersionUID = -347948511706338936L;
	
    public List<Permanence> getPermanenceByDate(Date date) {
    	Criteria req = getSession().createCriteria(Permanence.class)
    			.add(
	    				Restrictions.and(
							Restrictions.le("startDate", date),
							Restrictions.ge("endDate", date)
	    				)
    				);
    	@SuppressWarnings("unchecked")
		List<Permanence> permanences = (List<Permanence>)req.list();
		
		return permanences;
  
    }
    
    public List<Permanence> getPermanenceBySlot(Date startDate, Date endDate) {
    	Criteria req = getSession().createCriteria(Permanence.class)
    			.add(
	    				Restrictions.or(
							Restrictions.eq("startDate", startDate),
							Restrictions.eq("endDate", endDate)
	    				)
    				);
    	@SuppressWarnings("unchecked")
		List<Permanence> permanences = (List<Permanence>)req.list();
		
		return permanences;
  
    }
    
    public List<Permanence> getPermanenceByStatus(PermanenceStatus status) {
    	Criteria req = getSession().createCriteria(Permanence.class)
    			.add(
	    				Restrictions.and(
							Restrictions.eq("status", status)
	    				)
    				);
    	@SuppressWarnings("unchecked")
		List<Permanence> permanences = (List<Permanence>)req.list();
		
		return permanences;
    }
    
    public List<Permanence> getPermanenceByNotStatus(PermanenceStatus status) {
    	Criteria req = getSession().createCriteria(Permanence.class)
    			.add(
	    				Restrictions.and(
							Restrictions.ne("status", status),
							Restrictions.lt("startDate", new Date())
	    				)
    				);
    	@SuppressWarnings("unchecked")
		List<Permanence> permanences = (List<Permanence>)req.list();
		
		return permanences;
    }
    
    public List<Permanence> getPermanenceByWeek(Date start, Date end) {
    	Criteria req = getSession().createCriteria(Permanence.class)
    			.add(
	    				Restrictions.and(
							Restrictions.ge("startDate", start),
							Restrictions.le("endDate", end),
							Restrictions.ne("status", PermanenceStatus.CANCEL)
	    				)
    				);
    	@SuppressWarnings("unchecked")
		List<Permanence> permanences = (List<Permanence>)req.list();
		
		return permanences;
    }
    
    public List<Permanence> getPermanenceByDate(Date start, Date end) {
    	Criteria req = getSession().createCriteria(Permanence.class)
    			.add(
	    				Restrictions.and(
							Restrictions.ge("startDate", start),
							Restrictions.le("endDate", end)
	    				)
    				);
    	@SuppressWarnings("unchecked")
		List<Permanence> permanences = (List<Permanence>)req.list();
		
		return permanences;
    }
    
    public List<Permanence> getReplacementPermanence(Long nobodyId) {
    	Date now = CharivariUtil.getDateFromLocalDateTime(LocalDateTime.now());
    	Criteria req = getSession().createCriteria(Permanence.class)
    			.add(
	    				Restrictions.and(
	    						Restrictions.or(
	    								Restrictions.neProperty("family.id", "originalFamilyId"),
	    								Restrictions.eq("family.id", nobodyId),
	    								Restrictions.eq("originalFamilyId", nobodyId)
	    						),
							Restrictions.ge("endDate", now)
	    				)
    				);
    	@SuppressWarnings("unchecked")
		List<Permanence> permanences = (List<Permanence>)req.list();
		
		return permanences;
    }
    
    public List<Permanence> getOpenedPermanences() {
    	Criteria req = getSession().createCriteria(Permanence.class)
    			.add(
	    				Restrictions.and(
	    					Restrictions.eq("isOpen", true)
	    				)
    				);
    	@SuppressWarnings("unchecked")
		List<Permanence> permanences = (List<Permanence>)req.list();
		
		return permanences;
    }
    
    public List<Permanence> getPermanencesByFamily(Family family) {
    	Date now = CharivariUtil.getDateFromLocalDateTime(LocalDateTime.now());
    	Criteria req = getSession().createCriteria(Permanence.class)
    			.add(Restrictions.disjunction()
    					.add(
		    				Restrictions.and(
		    					Restrictions.eq("originalFamilyId", family.getId()),
		    					Restrictions.ge("startDate", family.getStartDateContract()),
		    					Restrictions.le("endDate", now)
		    				)
	    				)
    					.add(
		    				Restrictions.and(
		    					Restrictions.eq("family.id", family.getId()),
		    					Restrictions.eq("status", PermanenceStatus.DONE),
		    					Restrictions.ge("startDate", family.getStartDateContract()),
		    					Restrictions.le("endDate", now)
		    				)
	    				)
    				);
    	@SuppressWarnings("unchecked")
		List<Permanence> permanences = (List<Permanence>)req.list();
		
		return permanences;
    }	
}
