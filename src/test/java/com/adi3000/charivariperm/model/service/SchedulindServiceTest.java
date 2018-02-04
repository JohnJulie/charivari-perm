package com.adi3000.charivariperm.model.service;

import static org.junit.Assert.*;

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
import com.adi3000.charivariperm.model.dataobject.Scheduling;
import com.adi3000.charivariperm.model.service.FamilyService;
import com.adi3000.charivariperm.model.service.SchedulingService;
import com.adi3000.common.CharivariUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
@Transactional
public class SchedulindServiceTest {
	
	@Inject
	private transient FamilyService familyService;
	private Family myFamilyTest;
	@Inject
	private transient SchedulingService schedulingService;
	private Scheduling mySchedulingTest;
	private long idScheduling;

	public void setFamilyService(FamilyService familyService) {
		this.familyService = familyService;
	}
	
	public void setSchedulingService(SchedulingService schedulingService) {
		this.schedulingService = schedulingService;
	}
	
	
	@Before
	public void setUp () {
		System.out.print("---@Before---");
		
		this.myFamilyTest = new Family();
		this.myFamilyTest.setLabel("Tano, Olivia");
		this.myFamilyTest.setId(this.familyService.saveFamily(this.myFamilyTest));
		System.out.print(this.myFamilyTest.getId());
		
		this.mySchedulingTest = new Scheduling();
		this.mySchedulingTest.setFamily(this.myFamilyTest);
		this.mySchedulingTest.setDuration(3);
		this.mySchedulingTest.setFrequency(7);
		LocalDateTime startDate = LocalDate.of(2017, 8, 29).atTime(7, 45);
		System.out.print(startDate.toString());
		this.mySchedulingTest.setStartHour(CharivariUtil.getDateFromLocalDateTime(startDate));
		this.idScheduling = this.schedulingService.saveScheduling(this.mySchedulingTest);
		System.out.print(this.idScheduling);
	}
	
	@Test
	public void testFindAllScheduling() {
		System.out.print("---testFindAllScheduling---");
		List<Scheduling> schedulings = this.schedulingService.findAllSchedulings();
		assertTrue(schedulings.isEmpty());
	}
	
	@Test
	public void testSaveScheduling() {
		System.out.print("---testSaveScheduling---");
		// Family set
		Family family = new Family();
		family.setLabel("Elea, Blandine & Amir");
		this.familyService.saveFamily(family);
		
		// Scheduling set
		Scheduling scheduling = new Scheduling();
		scheduling.setFamily(family);
		scheduling.setDuration(3);
		scheduling.setFrequency(7);
		LocalDateTime startDate = LocalDate.of(2017, 8, 30).atTime(10, 0);
		scheduling.setStartHour(CharivariUtil.getDateFromLocalDateTime(startDate));
		
		long idScheduling = this.schedulingService.saveScheduling(scheduling);
		assertNotNull(idScheduling);
	}
	
	@Test
	public void testFindScheduling() {
		System.out.print("---testFindScheduling---");
		Scheduling scheduling = this.schedulingService.findById(this.idScheduling);
		assertNotNull(scheduling);
	}
	
	@Test
	public void testUpdateScheduling() {
		System.out.print("---testUpdateScheduling---");
		this.mySchedulingTest.setStartHour(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 9, 1).atTime(10, 0)));
		this.schedulingService.updateScheduling(this.mySchedulingTest);
		Scheduling scheduling = this.schedulingService.findById(this.idScheduling);
		assertEquals(this.mySchedulingTest, scheduling);
	}
	
	@Test
	public void testDeleteScheduling() {
		System.out.print("---testDeleteScheduling---");
		this.schedulingService.deleteSchedulingById(this.idScheduling);
	}
}
