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
import com.adi3000.charivariperm.model.dataobject.Image;
import com.adi3000.charivariperm.model.service.FamilyService;
import com.adi3000.common.CharivariUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
@Transactional
public class FamilyServiceTest {

	@Inject
	private transient FamilyService familyService;
	@Inject
	private transient ImageService imageService;
	private Family myFamilyTest;
	private Image myImageTest;
	private long idValue;

	public void setFamilyService(FamilyService familyService) {
		this.familyService = familyService;
	}
	
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}
	
	@Before
	public void setUp () {
		System.out.print("---@Before---");
		this.myImageTest = new Image();
		this.myImageTest.setUrl("asset/william.png");
		this.imageService.saveImage(this.myImageTest);
		
		this.myFamilyTest = new Family();
		this.myFamilyTest.setLabel("William, Julie & John");
		this.myFamilyTest.setImage(this.myImageTest);
		LocalDateTime startDateContract = LocalDate.of(2017, 8, 29).atTime(7, 45);
		this.myFamilyTest.setStartDateContract(CharivariUtil.getDateFromLocalDateTime(startDateContract));
		LocalDateTime endtDateContract = LocalDate.of(2018, 7, 27).atTime(18, 30);
		this.myFamilyTest.setStartDateContract(CharivariUtil.getDateFromLocalDateTime(endtDateContract));
		this.idValue = this.familyService.saveFamily(this.myFamilyTest);
		this.myFamilyTest.setId(this.idValue);
		System.out.print(this.idValue);
	}
	
	@Test
	public void testFindAllFamilies() {
		System.out.print("---testFindAllFamilies---");
		List<Family> families = this.familyService.findAllFamilies();
		assertFalse(families.isEmpty());
	}
	
	@Test
	public void testSaveFamily() {
		System.out.print("---testSaveFamily---");
		Image image = new Image();
		image.setUrl("asset/armand.png");
		this.imageService.saveImage(image);
		
		Family family = new Family();
		family.setLabel("Armand, France & Bastien");
		family.setImage(image);
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
