package com.adi3000.charivariperm.model.service;

import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Child;

public interface ChildService {

	long saveChild(Child child);
	
	List<Child> findAllChilds();
	
	void deleteChildById(Long id);
	
	Child findById(Long id);
	
	void updateChild(Child child);
	
}
