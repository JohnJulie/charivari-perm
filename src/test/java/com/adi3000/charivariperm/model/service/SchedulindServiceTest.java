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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
public class SchedulindServiceTest {
	
	@Inject
	private transient FamilyService familyService;
	private Family myFamilyTest;
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
		this.mySchedulingTest.setStartHour(startDate);
		//this.idScheduling = this.schedulingService.saveScheduling(this.mySchedulingTest);
		System.out.print(this.idScheduling);
	}
	
	@Test
	public void testFindAllScheduling() {
		System.out.print("---testFindAllScheduling---");
		List<Scheduling> schedulings = this.schedulingService.findAllSchedulings();
		assertFalse(schedulings.isEmpty());
	}
	
	
}
