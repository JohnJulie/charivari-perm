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
	
	@Before
	public void setUp () {
		System.out.print("---@Before---");
		
		this.myFamilyTest = new Family();
		this.myFamilyTest.setLabel("William, Julie & Adrien");
		this.myFamilyTest.setId(this.familyService.saveFamily(this.myFamilyTest));
		System.out.print(this.myFamilyTest.getId());
		
		this.myPermanenceTest = new Permanence();
		this.myPermanenceTest.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(7, 45)));
		this.myPermanenceTest.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(10, 45)));
		this.myPermanenceTest.setFamily(this.myFamilyTest);
		PermanenceStatus currentStatus = PermanenceStatus.NOT_CONFIRMED;
		this.myPermanenceTest.setStatus(currentStatus);
		this.idPermanence = this.permanenceService.savePermanence(this.myPermanenceTest);
		System.out.print(this.idPermanence);
	}
	
	@Test
	public void testFindAllPermanence() {
		System.out.print("---testFindAllPermanence---");
		List<Permanence> permanences = this.permanenceService.findAllPermanences();
		assertTrue(permanences.isEmpty());
	}
	
	@Test
	public void testSavePermanence() {
		System.out.print("---testSavePermanence---");
		// Family set
		Family family = new Family();
		family.setLabel("Elea, Blandine & Amir");
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
		Family family = new Family();
		family.setLabel("Noé, Julie & Vianney");
		family.setId(this.familyService.saveFamily(family));
		
		Family family2 = new Family();
		family2.setLabel("Louison, Nino, Nolwenn & Arnaud");
		family2.setId(this.familyService.saveFamily(family2));
		
		// Permanence set
		Permanence permanence = new Permanence();
		permanence.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(10, 00)));
		permanence.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(13, 00)));
		permanence.setFamily(family);
		permanence.setStatus(PermanenceStatus.DONE);
		this.permanenceService.savePermanence(permanence);
		
		Permanence permanence2 = new Permanence();
		permanence2.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(15, 30)));
		permanence2.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(18, 30)));
		permanence2.setFamily(family2);
		permanence2.setStatus(PermanenceStatus.DONE);
		this.permanenceService.savePermanence(permanence2);
		
		List<Permanence> currentPermanence = this.permanenceService.getCurrentPermanences();
		System.out.print("family1:");
		System.out.print(currentPermanence.get(0).getFamily().getLabel());
		System.out.print("family2:");
		System.out.print(currentPermanence.get(1).getFamily().getLabel());
		assertEquals(currentPermanence.size(), 2);
	}
	
}
