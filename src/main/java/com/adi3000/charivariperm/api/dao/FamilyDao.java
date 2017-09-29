package com.adi3000.charivariperm.api.dao;

import java.util.List;

import com.adi3000.charivariperm.api.models.Family;

public interface FamilyDao {
	
void saveFamily(Family family);
	
	List<Family> findAllFamilies();
	
	void deleteFamilyById(Long id);
	
	Family findById(Long id);
	
	void updateFamily(Family family);
	
}
