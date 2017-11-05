package com.adi3000.charivariperm.model.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.charivariperm.model.dataobject.Image;
import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.charivariperm.model.dataobject.Scheduling;
import com.adi3000.charivariperm.model.enumeration.PermanenceStatus;
import com.adi3000.charivariperm.model.service.FamilyService;
import com.adi3000.charivariperm.model.service.PermanenceService;
import com.adi3000.common.CharivariUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
@Transactional
public class PermanenceServiceTest {

	@Inject
	private transient ImageService imageService;
	private Image myImageTest;
	@Inject
	private transient SchedulingService schedulingService;
	@Inject
	private transient FamilyService familyService;
	private Family myFamilyTest;
	@Inject
	private transient PermanenceService permanenceService;
	private Permanence myPermanenceTest;
	private long idPermanence;
	
	public void setFamilyService(FamilyService familyService) {
		this.familyService = familyService;
	}
	
	public void setPermanenceService(PermanenceService permanenceService) {
		this.permanenceService = permanenceService;
	}
	
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}
	
	@Before
	public void setUp () {
		System.out.print("---@Before---");
		this.myImageTest = new Image();
		this.myImageTest.setUrl("asset/unknow.png");
		this.imageService.saveImage(this.myImageTest);
		
		this.myFamilyTest = new Family();
		this.myFamilyTest.setLabel("William, Julie & Adrien");
		this.myFamilyTest.setImage(this.myImageTest);
		this.myFamilyTest.setId(this.familyService.saveFamily(this.myFamilyTest));
		System.out.print(this.myFamilyTest.getId());
		
		this.myPermanenceTest = new Permanence();
		this.myPermanenceTest.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(7, 45)));
		this.myPermanenceTest.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(10, 45)));
		this.myPermanenceTest.setFamily(this.myFamilyTest);
		this.idPermanence = this.permanenceService.savePermanence(this.myPermanenceTest);
		
		Family family = new Family();
		family.setLabel("Noé, Julie & Vianney");
		family.setId(this.familyService.saveFamily(family));
		family.setImage(this.myImageTest);
		
		Family family2 = new Family();
		family2.setLabel("Louison, Nino, Nolwenn & Arnaud");
		family2.setId(this.familyService.saveFamily(family2));
		family2.setImage(this.myImageTest);
		
		// Permanence set
		Permanence permanence = new Permanence();
		permanence.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(10, 00)));
		permanence.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(13, 00)));
		permanence.setFamily(family);
		permanence.setStatus(PermanenceStatus.HELP);
		this.permanenceService.savePermanence(permanence);
		
		Permanence permanence2 = new Permanence();
		permanence2.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(15, 30)));
		permanence2.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(18, 30)));
		permanence2.setFamily(family2);
		permanence2.setStatus(PermanenceStatus.HELP);
		this.permanenceService.savePermanence(permanence2);
		
		Permanence permanence3 = new Permanence();
		permanence3.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 9, 5).atTime(15, 30)));
		permanence3.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 9, 5).atTime(18, 30)));
		permanence3.setFamily(family2);
		permanence3.setStatus(PermanenceStatus.REPLACEMENT);
		this.permanenceService.savePermanence(permanence3);
		
		System.out.print(this.idPermanence);
	}
	
	@Test
	public void testFindAllPermanence() {
		System.out.print("---testFindAllPermanence---");
		List<Permanence> permanences = this.permanenceService.findAllPermanences();
		assertFalse(permanences.isEmpty());
	}
	
	@Test
	public void testSavePermanence() {
		System.out.print("---testSavePermanence---");
		// Family set
		Family family = new Family();
		family.setLabel("Elea, Blandine & Amir");
		family.setImage(this.myImageTest);
		this.familyService.saveFamily(family);
		
		// Permanence set
		Permanence permanence = new Permanence();
		permanence.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(10, 00)));
		permanence.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(13, 00)));
		permanence.setFamily(family);
		permanence.setStatus(PermanenceStatus.DONE);
		
		long idPerm = this.permanenceService.savePermanence(permanence);
		assertNotNull(idPerm);
	}
	
	@Test
	public void testFindPermanence() {
		System.out.print("---testFindPermanence---");
		Permanence permanence = this.permanenceService.findById(this.idPermanence);
		assertNotNull(permanence);
	}
	
	@Test
	public void testUpdatePermanence() {
		System.out.print("---testUpdatePermanence---");
		this.myPermanenceTest.setStatus(PermanenceStatus.DONE);
		this.permanenceService.updatePermanence(this.myPermanenceTest);
		Permanence permanence = this.permanenceService.findById(this.idPermanence);
		assertEquals(this.myPermanenceTest, permanence);
	}
	
	@Test
	public void testDeletePermanence() {
		System.out.print("---testDeletePermanence---");
		this.permanenceService.deletePermanenceById(this.idPermanence);
	}
	
	@Test
	public void testGetCurrentPermanence() {
		System.out.print("---testGetCurrentPermanence---");
		List<Permanence> currentPermanence = this.permanenceService.getCurrentPermanences();
		assertEquals(currentPermanence.size(), 2);
	}
	
	@Test
	public void testGetEmptyPermanences() {
		System.out.print("---testGetEmptyPermanences---");
		List<Permanence> toReplacePermanence = this.permanenceService.getEmptyPermanences();
		assertEquals(toReplacePermanence.size(), 2);
	}
	
	@Test
	public void testGetWeekPermanences() {
		System.out.print("---testGetEmptyPermanences---");
		LocalDateTime startDate = LocalDate.of(2017, 8, 28).atTime(7,0);
		LocalDateTime endDate = LocalDate.of(2017, 9, 3).atTime(19,0);
		List<Permanence> weekPermanences = this.permanenceService.getWeekPermanences(startDate, endDate);
		System.out.println("Size:");
		System.out.println(weekPermanences.size());
		assertEquals(weekPermanences.size(), 3);
	}
	
	@Test
	public void testGeneratePermanencesFamily() {
		System.out.print("---testSetPermanencesFamily---");
		Scheduling scheduling = new Scheduling();
		scheduling.setFamily(this.myFamilyTest);
		scheduling.setStartHour(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(7,45)));
		scheduling.setDuration(3);
		scheduling.setFrequency(7);
		Long schedulingId = this.schedulingService.saveScheduling(scheduling);
		System.out.print("schedulingId:");
		System.out.println(schedulingId);
		
		this.permanenceService.generatePermanencesFamily(schedulingId);
		List<Permanence> permanences = this.permanenceService.findAllPermanences();
		System.out.print("Size:");
		System.out.println(permanences.size());
		assertTrue(permanences.size() > 10);
	}
}
