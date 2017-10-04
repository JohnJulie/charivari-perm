package com.adi3000.charivariperm.model.service;

import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Family;

public interface FamilyService {

	long saveFamily(Family family);
	
	List<Family> findAllFamilies();
	
	void deleteFamilyById(Long id);
	
	Family findById(Long id);
	
	void updateFamily(Family family);
	
}
