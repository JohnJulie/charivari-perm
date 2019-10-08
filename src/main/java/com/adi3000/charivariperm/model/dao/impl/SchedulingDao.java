package com.adi3000.charivariperm.model.dao.impl;

import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.charivariperm.model.dataobject.Scheduling;
import com.adi3000.common.orm.dao.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("schedulingDao")
public class SchedulingDao extends AbstractDAO<Scheduling> implements com.adi3000.charivariperm.model.dao.SchedulingDao {

	private static final long serialVersionUID = 5618221216335645228L;

	public Scheduling findByFamily(Family family) {
		Criteria req = getSession().createCriteria(Scheduling.class)
				.add(Restrictions.and(
						Restrictions.eq("family.id", family.getId()))
				)
				.setMaxResults(1);
		return (Scheduling)req.uniqueResult();
	}
}
