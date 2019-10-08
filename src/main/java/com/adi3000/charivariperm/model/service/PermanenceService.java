package com.adi3000.charivariperm.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.charivariperm.model.dataobject.Scheduling;
import com.adi3000.charivariperm.model.enumeration.PermanenceStatus;

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

	List<Permanence> getNoValidatePermanence();

	void validateMonthPermanences(LocalDate date);

	List<LocalDate> getNotClosedPermanences();

	List<Permanence> getPermCountByFamily(Long familyId);


	Integer countPermanences(Long familyId, LocalDateTime since, LocalDateTime to, PermanenceStatus status);

	void deleteLastPermanence(Family family);

	void createAdditionalPermanence(Family family);
}
