package com.adi3000.charivariperm.api.dao;

import java.util.List;

import com.adi3000.charivariperm.api.models.Permanence;

public interface PermanenceDao {
	
	void savePermanence(Permanence permanence);
	
	List<Permanence> findAllPermanences();
	
	void deletePermanenceById(Long id);
	
	Permanence findById(Long id);
	
	void updatePermanence(Permanence permanence);
	
}
