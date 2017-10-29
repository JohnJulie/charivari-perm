package com.adi3000.charivariperm.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.charivariperm.model.dataobject.Scheduling;

public interface PermanenceService {
	
	long savePermanence(Permanence permanence);
	
	List<Permanence> findAllPermanences();
	
	void deletePermanenceById(Long id);
	
	Permanence findById(Long id);
	
	void updatePermanence(Permanence permanence);
	
	List<Permanence> getCurrentPermanences();
	
	List<Permanence> getEmptyPermanences();
	
	List<Permanence> getWeekPermanences(LocalDateTime startDate, LocalDateTime endDate);
	
	void generatePermanencesFamily(Scheduling scheduling);
}
