package com.adi3000.charivariperm.model.service;

import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Adult;

public interface AdultService {

	long saveAdult(Adult adult);
	
	List<Adult> findAllAdults();
	
	void deleteAdultById(Long id);
	
	Adult findById(Long id);
	
	void updateAdult(Adult adult);
	
}
