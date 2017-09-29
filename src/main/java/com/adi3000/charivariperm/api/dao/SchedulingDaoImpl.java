package com.adi3000.charivariperm.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.api.models.Scheduling;

@Repository("schedulingDao")
public class SchedulingDaoImpl extends AbstractDao implements SchedulingDao {
	
	public void saveScheduling(Scheduling scheduling) {
        persist(scheduling);
    }
 
    @SuppressWarnings("unchecked")
    public List<Scheduling> findAllSchedulings() {
        Criteria criteria = getSession().createCriteria(Scheduling.class);
        return (List<Scheduling>) criteria.list();
    }
 
    public void deleteSchedulingById(Long id) {
        Query query = getSession().createSQLQuery("delete from Scheduling where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }
 
     
    public Scheduling findById(Long id){
        Criteria criteria = getSession().createCriteria(Scheduling.class);
        criteria.add(Restrictions.eq("id",id));
        return (Scheduling) criteria.uniqueResult();
    }
     
    public void updateScheduling(Scheduling scheduling){
        getSession().update(scheduling);
    }

}
