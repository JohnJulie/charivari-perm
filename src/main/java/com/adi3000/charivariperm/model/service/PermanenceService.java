package com.adi3000.charivariperm.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.charivariperm.model.dataobject.Scheduling;

public interface PermanenceService {
	
	long savePermanence(Permanence permanence);
	
	long flyingPermanence(Long nobodyFamilyId, Long familyId, LocalDateTime startDate);
	
	List<Permanence> findAllPermanences();
	
	List<Permanence> findAllOpenedPermanences();
	
	void deletePermanenceById(Long id);
	
	Permanence findById(Long id);
	
	void updatePermanence(Permanence permanence);
	
	List<Permanence> getCurrentPermanences();
	
	List<Permanence> getEmptyPermanences();
	
	List<Permanence> getWeekPermanences(LocalDateTime startDate, LocalDateTime endDate);
	
	List<Permanence> getPermanenceToReplace(LocalDate date, String slot);
	
	void generatePermanencesFamily(Long schedulingId);
	
	List<Permanence> getReplacement(Long nobodyId);
	
	void validateMonthPermanences(LocalDate date);
	
	List<LocalDate> getNotClosedPermanences();
	
	List<Permanence> getPermCountByFamily(Long familyId);
}
