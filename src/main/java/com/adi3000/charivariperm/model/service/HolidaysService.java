package com.adi3000.charivariperm.model.service;

import java.time.LocalDateTime;
import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Holidays;


public interface HolidaysService {

	long saveHolidays(Holidays holidays);
	
	List<Holidays> findAllHolidays();
	
	void deleteHolidaysById(Long id);
	
	Holidays findById(Long id);
	
	void updateHolidays(Holidays holidays);
	
	void generateHolidaysFromDates(LocalDateTime fromDate, LocalDateTime toDate);
}
