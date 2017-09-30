package com.adi3000.charivariperm.model.service;

import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Permanence;

public interface PermanenceService {
	
	void savePermanence(Permanence permanence);
	
	List<Permanence> findAllPermanences();
	
	void deletePermanenceById(Long id);
	
	Permanence findById(Long id);
	
	void updatePermanence(Permanence permanence);

}
