package com.adi3000.charivariperm.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.api.models.Person;

@Repository("personDao")
public class PersonDaoImpl extends AbstractDao implements PersonDao {

	public void savePerson(Person person) {
        persist(person);
    }
 
    @SuppressWarnings("unchecked")
    public List<Person> findAllPersons() {
        Criteria criteria = getSession().createCriteria(Person.class);
        return (List<Person>) criteria.list();
    }
 
    public void deletePersonById(Long id) {
        Query query = getSession().createSQLQuery("delete from Person where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }
 
     
    public Person findById(Long id){
        Criteria criteria = getSession().createCriteria(Person.class);
        criteria.add(Restrictions.eq("id",id));
        return (Person) criteria.uniqueResult();
    }
     
    public void updatePerson(Person person){
        getSession().update(person);
    }
    
}
