package com.adi3000.charivariperm.model.service;

import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Person;

public interface PersonService {
	
	void savePerson(Person person);
	
	List<Person> findAllPersons();
	
	void deletePersonById(Long id);
	
	Person findById(Long id);
	
	void updatePerson(Person person);
	
}
