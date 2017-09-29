package com.adi3000.charivariperm.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.api.models.Family;

@Repository("familyDao")
public class FamilyDaoImpl extends AbstractDao implements FamilyDao {
	public void saveFamily(Family family) {
        persist(family);
    }
 
    @SuppressWarnings("unchecked")
    public List<Family> findAllFamilies() {
        Criteria criteria = getSession().createCriteria(Family.class);
        return (List<Family>) criteria.list();
    }
 
    public void deleteFamilyById(Long id) {
        Query query = getSession().createSQLQuery("delete from Family where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }
 
     
    public Family findById(Long id){
        Criteria criteria = getSession().createCriteria(Family.class);
        criteria.add(Restrictions.eq("id",id));
        return (Family) criteria.uniqueResult();
    }
     
    public void updateFamily(Family family){
        getSession().update(family);
    }
}
