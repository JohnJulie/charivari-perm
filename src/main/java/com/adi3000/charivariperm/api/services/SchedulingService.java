package com.adi3000.charivariperm.api.services;

import java.util.List;

import com.adi3000.charivariperm.api.models.Scheduling;

public interface SchedulingService {

	void saveScheduling(Scheduling scheduling);
	
	List<Scheduling> findAllSchedulings();
	
	void deleteSchedulingById(Long id);
	
	Scheduling findById(Long id);
	
	void updateScheduling(Scheduling scheduling);
}
