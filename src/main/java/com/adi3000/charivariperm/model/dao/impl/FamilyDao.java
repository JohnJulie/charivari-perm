package com.adi3000.charivariperm.model.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.common.CharivariUtil;
import com.adi3000.common.orm.dao.AbstractDAO;

@Repository("familyDao")
public class FamilyDao extends AbstractDAO<Family>  implements com.adi3000.charivariperm.model.dao.FamilyDao {

	private static final long serialVersionUID = -5225559703545558094L;
	
	public List<Family> findAllWithCurrentContract() {
		Date now = new Date();
    	Criteria req = getSession().createCriteria(Family.class)
    			.add(
	    				Restrictions.and(
							Restrictions.ge("endDateContract", now)
	    				)
    				);
    	@SuppressWarnings("unchecked")
		List<Family> families = (List<Family>)req.list();
		
		return families;
	}
}
