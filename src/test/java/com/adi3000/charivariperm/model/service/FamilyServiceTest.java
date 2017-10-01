package com.adi3000.charivariperm.model.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.adi3000.charivariperm.model.dataobject.Family;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
public class FamilyServiceTest {

	@Inject
	private transient FamilyService familyService;

	/**
	 * @param familyService the familyService to set
	 */
	public void setFamilyService(FamilyService familyService) {
		this.familyService = familyService;
	}
	
	@Test
	public void testFindAllFamilies() {
		List<Family> families = this.familyService.findAllFamilies();
		assertTrue(families.isEmpty());
	}
	
	@Test
	public void testSaveFamily() {
		Family family = new Family();
		family.setLabel("William, Julie et Adrien");
		this.familyService.saveFamily(family);
	}
	
}
