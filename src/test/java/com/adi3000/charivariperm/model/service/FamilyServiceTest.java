package com.adi3000.charivariperm.model.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.charivariperm.model.service.FamilyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
@Transactional
public class FamilyServiceTest {

	@Inject
	private transient FamilyService familyService;
	private Family myFamilyTest;
	private long idValue;

	public void setFamilyService(FamilyService familyService) {
		this.familyService = familyService;
	}
	
	@Before
	public void setUp () {
		System.out.print("---@Before---");
		this.myFamilyTest = new Family();
		this.myFamilyTest.setLabel("William, Julie & John");
		this.idValue = this.familyService.saveFamily(this.myFamilyTest);
		this.myFamilyTest.setId(this.idValue);
		System.out.print(this.idValue);
	}
	
	@Test
	public void testFindAllFamilies() {
		System.out.print("---testFindAllFamilies---");
		List<Family> families = this.familyService.findAllFamilies();
		assertTrue(families.isEmpty());
	}
	
	@Test
	public void testSaveFamily() {
		System.out.print("---testSaveFamily---");
		Family family = new Family();
		family.setLabel("Elea, Blandine & Amir");
		long idFamily = this.familyService.saveFamily(this.myFamilyTest);
		assertNotNull(idFamily);
	}
	
	@Test
	public void testFindFamily() {
		System.out.print("---testFindFamily---");
		Family family = this.familyService.findById(this.idValue);
		assertNotNull(family);
	}
	
	@Test
	public void testUpdateFamily() {
		System.out.print("---testUpdateFamily---");
		this.myFamilyTest.setLabel("William, Julie & Adrien");
		this.familyService.updateFamily(this.myFamilyTest);
		Family family = this.familyService.findById(this.idValue);
		assertEquals(this.myFamilyTest, family);
	}
	
	@Test
	public void testDeleteFamily() {
		System.out.print("---testDeleteFamily---");
		this.familyService.deleteFamilyById(this.idValue);
	}

}
