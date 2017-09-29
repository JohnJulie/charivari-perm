package com.adi3000.charivariperm.api.services;

import java.util.List;

import com.adi3000.charivariperm.api.models.Person;

public interface PersonService {
	
	void savePerson(Person person);
	
	List<Person> findAllPersons();
	
	void deletePersonById(Long id);
	
	Person findById(Long id);
	
	void updatePerson(Person person);
	
}
