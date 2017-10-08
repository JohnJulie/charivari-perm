package com.adi3000.charivariperm.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.model.dataobject.Person;
import com.adi3000.common.orm.dao.AbstractDAO;

@Repository("personDao")
public class PersonDao extends AbstractDAO<Person>  implements com.adi3000.charivariperm.model.dao.PersonDao {
		
	private static final long serialVersionUID = 5618221216335645228L;
	
}
