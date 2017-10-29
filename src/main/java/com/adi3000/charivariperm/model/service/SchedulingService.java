package com.adi3000.charivariperm.model.service;

import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.charivariperm.model.dataobject.Scheduling;

public interface SchedulingService {

	long saveScheduling(Scheduling scheduling);
	
	List<Scheduling> findAllSchedulings();
	
	void deleteSchedulingById(Long id);
	
	Scheduling findById(Long id);
	
	void updateScheduling(Scheduling scheduling);
}
