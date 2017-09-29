package com.adi3000.charivariperm.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.api.dao.PersonDao;
import com.adi3000.charivariperm.api.models.Person;

@Service("personService")
@Transactional
public class PersonServiceImpl implements PersonService {
	@Autowired
    private PersonDao dao;
     
    public void savePerson(Person person) {
        dao.savePerson(person);
    }
 
    public List<Person> findAllPersons() {
        return dao.findAllPersons();
    }
 
    public void deletePersonById(Long id) {
        dao.deletePersonById(id);
    }
 
    public Person findById(Long id) {
        return dao.findById(id);
    }
 
    public void updatePerson(Person person){
        dao.updatePerson(person);
    }
}
