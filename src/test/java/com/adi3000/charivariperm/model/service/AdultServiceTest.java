package com.adi3000.charivariperm.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.charivariperm.model.dataobject.Adult;
import com.adi3000.charivariperm.model.dataobject.Adult;
import com.adi3000.charivariperm.model.dataobject.Adult;
import com.adi3000.charivariperm.model.dataobject.Adult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
@Transactional
public class AdultServiceTest {

	@Inject
	private transient FamilyService familyService;
	private Family myFamilyTest;
	@Inject
	private transient AdultService adultService;
	private Adult myAdultTest;
	private long idAdult;
	
	public void setFamilyService(FamilyService familyService) {
		this.familyService = familyService;
	}
	
	public void setAdultService(AdultService adultService) {
		this.adultService = adultService;
	}
	
	@Before
	public void setUp () {
		System.out.print("---@Before---");
		
		this.myFamilyTest = new Family();
		this.myFamilyTest.setLabel("Tano, Olivia");
		this.myFamilyTest.setId(this.familyService.saveFamily(this.myFamilyTest));
		System.out.print(this.myFamilyTest.getId());
		
		this.myAdultTest = new Adult();
		this.myAdultTest.setFamily(this.myFamilyTest);
		this.myAdultTest.setFirstname("Julie");
		this.myAdultTest.setLastname("Pelletier");
		this.myAdultTest.setBirthday(LocalDate.of(1990, 6, 6));
		this.myAdultTest.setMail("6zuliz6@gmail.com");
		this.myAdultTest.setPhoneNumber("0679307338");
		this.idAdult = this.adultService.saveAdult(this.myAdultTest);
		System.out.print(this.idAdult);
	}
	
	@Test
	public void testFindAllAdult() {
		System.out.print("---testFindAllAdult---");
		List<Adult> adults = this.adultService.findAllAdults();
		assertTrue(adults.isEmpty());
	}
	
	@Test
	public void testSaveAdult() {
		System.out.print("---testSaveAdult---");
		// Family set
		Family family = new Family();
		family.setLabel("Elea, Blandine & Amir");
		this.familyService.saveFamily(family);
		
		// Adult set
		Adult adult = new Adult();
		adult.setFamily(family);
		adult.setFirstname("Adrien");
		adult.setLastname("Ramarojohn");
		adult.setBirthday(LocalDate.of(1989, 3, 26));
		adult.setMail("adi300000@gmail.com");
		adult.setPhoneNumber("0677578933");
		
		long idAdult = this.adultService.saveAdult(adult);
		assertNotNull(idAdult);
	}
	
	@Test
	public void testFindAdult() {
		System.out.print("---testFindAdult---");
		Adult adult = this.adultService.findById(this.idAdult);
		assertNotNull(adult);
	}
	
	@Test
	public void testUpdateAdult() {
		System.out.print("---testUpdateAdult---");
		this.myAdultTest.setLastname("Ramarojohn");
		this.adultService.updateAdult(this.myAdultTest);
		Adult adult = this.adultService.findById(this.idAdult);
		assertEquals(this.myAdultTest, adult);
	}
	
	@Test
	public void testDeleteAdult() {
		System.out.print("---testDeleteAdult---");
		this.adultService.deleteAdultById(this.idAdult);
	}
}
