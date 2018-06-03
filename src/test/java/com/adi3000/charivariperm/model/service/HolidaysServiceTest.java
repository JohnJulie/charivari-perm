package com.adi3000.charivariperm.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.charivariperm.model.dataobject.Holidays;
import com.adi3000.charivariperm.model.dataobject.Image;
import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.common.CharivariUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
@Transactional
public class HolidaysServiceTest {

	@Inject
	private transient HolidaysService holidaysService;
	private Holidays winterHolidays;
	private long idValue;
	
	public void setHolidaysService(HolidaysService holidaysService) {
		this.holidaysService = holidaysService;
	}
	
	@Before
	public void setUp () {
		System.out.print("---@Before---");
		this.winterHolidays = new Holidays();
		LocalDateTime startDateHoliday = LocalDate.of(2017, 12, 25).atTime(7, 45);
		this.winterHolidays.setStartDate(CharivariUtil.getDateFromLocalDateTime(startDateHoliday));
		LocalDateTime endtDateHoliday = LocalDate.of(2018, 1, 2).atTime(18, 30);
		this.winterHolidays.setEndDate(CharivariUtil.getDateFromLocalDateTime(endtDateHoliday));
		this.idValue = this.holidaysService.saveHolidays(this.winterHolidays);
		this.winterHolidays.setId(this.idValue);
		System.out.print(this.idValue);
	}
	
	@Test
	public void testFindAllHolidays() {
		System.out.print("---testFindAllHolidays---");
		List<Holidays> holidays = this.holidaysService.findAllHolidays();
		assertFalse(holidays.isEmpty());
	}
	
	@Test
	public void testSaveHolidays() {
		System.out.print("---testSaveHolidays---");		
		Holidays holidays = new Holidays();
		LocalDateTime startDate = LocalDate.of(2017, 11, 1).atTime(7, 45);
		holidays.setStartDate(CharivariUtil.getDateFromLocalDateTime(startDate));
		LocalDateTime endtDate = LocalDate.of(2017, 11, 1).atTime(18, 30);
		holidays.setEndDate(CharivariUtil.getDateFromLocalDateTime(endtDate));
		long idHolidays = this.holidaysService.saveHolidays(holidays);
		assertNotNull(idHolidays);
	}
	
	@Test
	public void testFindHolidays() {
		System.out.print("---testFindHolidays---");
		Holidays holidays = this.holidaysService.findById(this.idValue);
		assertNotNull(holidays);
	}
	
	@Test
	public void testUpdateHolidays() {
		System.out.print("---testUpdateHolidays---");
		LocalDateTime startDate = LocalDate.of(2017, 12, 26).atTime(7, 45);
		this.winterHolidays.setStartDate(CharivariUtil.getDateFromLocalDateTime(startDate));
		this.holidaysService.updateHolidays(this.winterHolidays);
		Holidays holidays = this.holidaysService.findById(this.idValue);
		assertEquals(this.winterHolidays, holidays);
	}
	
	@Test
	public void testDeleteHolidays() {
		System.out.print("---testDeleteHolidays---");
		this.holidaysService.deleteHolidaysById(this.idValue);
	}
	
	@Test
	public void testGenerateHolidaysFromDates() {
		System.out.println("---testGenerateHolidaysFromDates---");
		LocalDateTime startDate = LocalDate.of(2018, 4, 2).atTime(7,0);
		LocalDateTime endDate = LocalDate.of(2018, 4, 2).atTime(19,0);
		this.holidaysService.generateHolidaysFromDates(startDate, endDate);
		
		List<Holidays> holidays = this.holidaysService.findAllHolidays();
		System.out.println("Size:");
		System.out.println(holidays.size());
		assertEquals(holidays.size(), 2);
	}
	
	
	
}
