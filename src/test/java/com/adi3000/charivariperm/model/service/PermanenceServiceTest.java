package com.adi3000.charivariperm.model.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
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
import com.adi3000.charivariperm.model.dataobject.Holidays;
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
	private Family nobodyTest;
	@Inject
	private transient HolidaysService holidaysService;
	private Holidays winterHolidays;
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
		
		this.winterHolidays = new Holidays();
		LocalDateTime startDateHoliday = LocalDate.of(2017, 12, 25).atTime(7, 45);
		this.winterHolidays.setStartDate(CharivariUtil.getDateFromLocalDateTime(startDateHoliday));
		LocalDateTime endtDateHoliday = LocalDate.of(2018, 1, 2).atTime(18, 30);
		this.winterHolidays.setEndDate(CharivariUtil.getDateFromLocalDateTime(endtDateHoliday));
		this.winterHolidays.setId(this.holidaysService.saveHolidays(this.winterHolidays));
		
		this.myFamilyTest = new Family();
		this.myFamilyTest.setLabel("William, Julie & Adrien");
		this.myFamilyTest.setImage(this.myImageTest);
		LocalDateTime startDateContract = LocalDate.of(2017, 8, 29).atTime(7, 45);
		this.myFamilyTest.setStartDateContract(CharivariUtil.getDateFromLocalDateTime(startDateContract));
		LocalDateTime endtDateContract = LocalDate.of(2018, 6, 1).atTime(18, 30);
		this.myFamilyTest.setEndDateContract(CharivariUtil.getDateFromLocalDateTime(endtDateContract));
		this.myFamilyTest.setId(this.familyService.saveFamily(this.myFamilyTest));
		System.out.print(this.myFamilyTest.getId());
		
		this.nobodyTest = new Family();
		this.nobodyTest.setLabel("Personne");
		this.nobodyTest.setImage(this.myImageTest);
		LocalDateTime startDateContract2 = LocalDate.of(2018, 8, 29).atTime(7, 45);
		this.nobodyTest.setStartDateContract(CharivariUtil.getDateFromLocalDateTime(startDateContract2));
		LocalDateTime endtDateContract2 = LocalDate.of(2019, 6, 1).atTime(18, 30);
		this.nobodyTest.setEndDateContract(CharivariUtil.getDateFromLocalDateTime(endtDateContract2));
		this.nobodyTest.setId(this.familyService.saveFamily(this.nobodyTest));
		System.out.print(this.nobodyTest.getId());
		
		this.myPermanenceTest = new Permanence();
		this.myPermanenceTest.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(7, 45)));
		this.myPermanenceTest.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(10, 45)));
		this.myPermanenceTest.setFamily(this.myFamilyTest);
		this.myPermanenceTest.setIsOpen(true);
		this.idPermanence = this.permanenceService.savePermanence(this.myPermanenceTest);
		
		Family family = new Family();
		family.setLabel("No\uFFFD, Julie & Vianney");
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
		permanence.setIsOpen(true);
		this.permanenceService.savePermanence(permanence);
		
		Permanence permanence2 = new Permanence();
		permanence2.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(15, 30)));
		permanence2.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(18, 30)));
		permanence2.setFamily(family2);
		permanence2.setStatus(PermanenceStatus.HELP);
		permanence2.setIsOpen(true);
		this.permanenceService.savePermanence(permanence2);
		
		Permanence permanence3 = new Permanence();
		permanence3.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 9, 5).atTime(15, 30)));
		permanence3.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 9, 5).atTime(18, 30)));
		permanence3.setFamily(family2);
		permanence3.setStatus(PermanenceStatus.REPLACEMENT);
		permanence3.setIsOpen(true);
		this.permanenceService.savePermanence(permanence3);
		
		System.out.print(this.idPermanence);
	}
	
	@Test
	public void testFindAllPermanence() {
		System.out.println("---testFindAllPermanence---");
		List<Permanence> permanences = this.permanenceService.findAllPermanences();
		assertFalse(permanences.isEmpty());
	}
	
	@Test
	public void testFindAllOpenedPermanences() {
		System.out.println("---testFindAllOpenedPermanences---");
		List<Permanence> permanences = this.permanenceService.findAllOpenedPermanences();
		assertFalse(permanences.isEmpty());
	}
	
	@Test
	public void testSavePermanence() {
		System.out.println("---testSavePermanence---");
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
		permanence.setIsOpen(true);
		permanence.setStatus(PermanenceStatus.DONE);
		
		long idPerm = this.permanenceService.savePermanence(permanence);
		assertNotNull(idPerm);
	}
	
	@Test
	public void testFlyingPermanence() {
		System.out.println("---testFlyingPermanence---");
		
		long idPerm = this.permanenceService.flyingPermanence(
				this.nobodyTest.getId(),
				this.myFamilyTest.getId(),
				LocalDate.of(2019, 2, 22).atTime(15, 30)
		);
		assertNotNull(idPerm);
	}
	
	@Test
	public void testFindPermanence() {
		System.out.println("---testFindPermanence---");
		Permanence permanence = this.permanenceService.findById(this.idPermanence);
		assertNotNull(permanence);
	}
	
	@Test
	public void testUpdatePermanence() {
		System.out.println("---testUpdatePermanence---");
		this.myPermanenceTest.setStatus(PermanenceStatus.DONE);
		this.permanenceService.updatePermanence(this.myPermanenceTest);
		Permanence permanence = this.permanenceService.findById(this.idPermanence);
		assertEquals(this.myPermanenceTest, permanence);
	}
	
	@Test
	public void testDeletePermanence() {
		System.out.println("---testDeletePermanence---");
		this.permanenceService.deletePermanenceById(this.idPermanence);
	}
	
	@Test
	public void testGetCurrentPermanence() {
		System.out.println("---testGetCurrentPermanence---");
		List<Permanence> currentPermanence = this.permanenceService.getCurrentPermanences();
		assertEquals(currentPermanence.size(), 0);
	}
	
	@Test
	public void testGetEmptyPermanences() {
		System.out.println("---testGetEmptyPermanences---");
		List<Permanence> toReplacePermanence = this.permanenceService.getEmptyPermanences();
		assertEquals(toReplacePermanence.size(), 2);
	}
	
	@Test
	public void testGetWeekPermanences() {
		System.out.println("---testGetEmptyPermanences---");
		LocalDateTime startDate = LocalDate.of(2017, 8, 28).atTime(7,0);
		LocalDateTime endDate = LocalDate.of(2017, 9, 3).atTime(19,0);
		List<Permanence> weekPermanences = this.permanenceService.getWeekPermanences(startDate, endDate);
		System.out.println("Size:");
		System.out.println(weekPermanences.size());
		assertEquals(weekPermanences.size(), 3);
	}
	
	@Test
	public void testGetPermanencesBySlot() {
		System.out.println("---testGetPermanencesBySlot---");
		// Permanence set
		Permanence permanence = new Permanence();
		permanence.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 11, 13).atTime(7, 45)));
		permanence.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 11, 13).atTime(9, 15)));
		permanence.setFamily(this.myFamilyTest);
		permanence.setIsOpen(true);
		this.permanenceService.savePermanence(permanence);
		
		// Family set
		Family family = new Family();
		family.setLabel("Arya, Rachel & J\uFFFDr\uFFFDme");
		family.setImage(this.myImageTest);
		this.familyService.saveFamily(family);
		Permanence perm2 = new Permanence();
		perm2.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 11, 13).atTime(7, 45)));
		perm2.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 11, 13).atTime(10, 45)));
		perm2.setFamily(family);
		perm2.setIsOpen(true);
		this.permanenceService.savePermanence(perm2);
				
		LocalDate startDate = LocalDate.of(2017, 11, 13);
		List<Permanence> toReplace = this.permanenceService.getPermanenceToReplace(startDate, "0");
		System.out.println("Size:");
		System.out.println(toReplace.size());
		assertEquals(toReplace.size(), 2);
	}
	
	@Test
	public void testGeneratePermanencesFamily() {
		System.out.println("---testSetPermanencesFamily---");
		Scheduling scheduling = new Scheduling();
		scheduling.setFamily(this.myFamilyTest);
		scheduling.setStartHour(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 8, 29).atTime(7,45)));
		scheduling.setDuration(3);
		scheduling.setFrequency(7);
		Long schedulingId = this.schedulingService.saveScheduling(scheduling);
		System.out.print("schedulingId:");
		System.out.println(schedulingId);
		
		this.permanenceService.generatePermanencesFamily(schedulingId);
		
		System.out.print("test holiday generation:");
		LocalDateTime startDate = LocalDate.of(2017, 12, 26).atTime(7, 45);
		LocalDateTime endDate = LocalDate.of(2018, 1, 2).atTime(18, 30);
		List<Permanence> holidayPerm = this.permanenceService.getWeekPermanences(startDate, endDate);
		System.out.println(holidayPerm.size());
		assertEquals(holidayPerm.size(), 0);
		
		System.out.print("test contract generation:");
		LocalDateTime startDateContractTest = LocalDate.of(2018, 6, 4).atTime(7, 45);
		LocalDateTime endDateContractTest = LocalDate.of(2018, 6, 8).atTime(18, 30);
		List<Permanence> contractPerm = this.permanenceService.getWeekPermanences(startDateContractTest, endDateContractTest);
		System.out.println(contractPerm.size());
		assertEquals(contractPerm.size(), 0);

		List<Permanence> permanences = this.permanenceService.findAllPermanences();
		System.out.print("Size:");
		System.out.println(permanences.size());
		assertTrue(permanences.size() > 10);
	}
	
	@Test
	public void testGetReplacementPermanence() {
		System.out.println("---testGetReplacementPermanence---");
		Family family2 = new Family();
		family2.setLabel("Elea, Blandine, Amir");
		family2.setId(this.familyService.saveFamily(family2));
		family2.setImage(this.myImageTest);
		
		// Permanence set
		Permanence permanence = new Permanence();
		permanence.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2018, 7, 12).atTime(10, 00)));
		permanence.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2018, 7, 12).atTime(13, 00)));
		permanence.setFamily(this.myFamilyTest);
		permanence.setStatus(PermanenceStatus.NOT_CONFIRMED);
		permanence.setIsOpen(true);
		this.permanenceService.savePermanence(permanence);
		
		permanence.setFamily(family2);
		this.permanenceService.updatePermanence(permanence);
		Long nobodyId = (long) 1;
		List<Permanence> permanences = this.permanenceService.getReplacement(nobodyId);
		System.out.print("Size:");
		System.out.println(permanences.size());
		assertEquals(permanences.size(), 1);
	}
	
	@Test
	public void testValidateMonthPermanences() {
		System.out.println("---testGetReplacementPermanence---");
		Family family2 = new Family();
		family2.setLabel("Elea, Blandine, Amir");
		family2.setId(this.familyService.saveFamily(family2));
		family2.setImage(this.myImageTest);
		
		Long permanenceId;
		
		// Permanence set
		Permanence permanence = new Permanence();
		permanence.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2018, 1, 12).atTime(10, 00)));
		permanence.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2018, 1, 12).atTime(13, 00)));
		permanence.setFamily(this.myFamilyTest);
		permanence.setStatus(PermanenceStatus.NOT_CONFIRMED);
		permanence.setIsOpen(true);
		permanenceId = this.permanenceService.savePermanence(permanence);
		
		permanence.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2018, 1, 13).atTime(10, 00)));
		permanence.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2018, 1, 13).atTime(13, 00)));
		this.permanenceService.savePermanence(permanence);
		
		this.permanenceService.validateMonthPermanences(LocalDate.of(2018, 1, 1));
		
		Permanence permanenceToTest = this.permanenceService.findById(permanenceId);
		
		assertEquals(permanenceToTest.getIsOpen(), false);
	}

	@Test
	public void testCreateAdditionalPermanence(){
		Family family2 = new Family();
		family2.setLabel("Elea, Blandine, Amir");
		family2.setStartDateContract(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 1, 1).atTime(10, 00)));
		family2.setEndDateContract(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2018, 1, 1).atTime(10, 00)));
		family2.setId(familyService.saveFamily(family2));
		family2.setImage(this.myImageTest);


		Scheduling scheduling = new Scheduling();
		scheduling.setFamily(family2);
		scheduling.setFrequency(7);
		scheduling.setStartHour(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2017, 1, 1).atTime(10, 00)));
		scheduling.setDuration(180);
		schedulingService.saveScheduling(scheduling);


		Permanence permanence = new Permanence();
		permanence.setStartDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2018, 1, 12).atTime(10, 00)));
		permanence.setEndDate(CharivariUtil.getDateFromLocalDateTime(LocalDate.of(2018, 1, 12).atTime(13, 00)));
		permanence.setFamily(family2);
		permanence.setOriginalFamilyId(family2.getId());
		permanence.setStatus(PermanenceStatus.NOT_CONFIRMED);
		permanence.setIsOpen(true);
		this.permanenceService.savePermanence(permanence);

		permanenceService.createAdditionalPermanence(family2);

	}
}
