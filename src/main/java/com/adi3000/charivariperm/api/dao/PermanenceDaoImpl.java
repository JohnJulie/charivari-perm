package com.adi3000.charivariperm.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.api.models.Permanence;

@Repository("permanenceDao")
public class PermanenceDaoImpl extends AbstractDao implements PermanenceDao {
	
	public void savePermanence(Permanence permanence) {
        persist(permanence);
    }
 
    @SuppressWarnings("unchecked")
    public List<Permanence> findAllPermanences() {
        Criteria criteria = getSession().createCriteria(Permanence.class);
        return (List<Permanence>) criteria.list();
    }
 
    public void deletePermanenceById(Long id) {
        Query query = getSession().createSQLQuery("delete from Permanence where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }
 
     
    public Permanence findById(Long id){
        Criteria criteria = getSession().createCriteria(Permanence.class);
        criteria.add(Restrictions.eq("id",id));
        return (Permanence) criteria.uniqueResult();
    }
     
    public void updatePermanence(Permanence permanence){
        getSession().update(permanence);
    }
    
}
