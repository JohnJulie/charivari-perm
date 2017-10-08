package com.adi3000.charivariperm.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.model.dataobject.Child;
import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.charivariperm.model.dataobject.Image;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
@Transactional
public class ChildServiceTest {

	@Inject
	private transient FamilyService familyService;
	private Family myFamilyTest;
	
	@Inject
	private transient ImageService imageService;
	private Image myImageTest;
	
	@Inject
	private transient ChildService childService;
	private Child myChildTest;
	private long idChild;
	
	public void setFamilyService(FamilyService familyService) {
		this.familyService = familyService;
	}
	
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}
	
	public void setChildService(ChildService childService) {
		this.childService = childService;
	}
	
	@Before
	public void setUp () {
		System.out.print("---@Before---");
		
		this.myFamilyTest = new Family();
		this.myFamilyTest.setLabel("Tano, Olivia");
		this.myFamilyTest.setId(this.familyService.saveFamily(this.myFamilyTest));
		System.out.print(this.myFamilyTest.getId());
		
		this.myImageTest = new Image();
		this.myImageTest.setUrl("tano-logo.png");
		this.myImageTest.setId(this.imageService.saveImage(this.myImageTest));
		
		this.myChildTest = new Child();
		this.myChildTest.setFamily(this.myFamilyTest);
		this.myChildTest.setFirstname("Tano");
		this.myChildTest.setLastname("Olay");
		this.myChildTest.setBirthday(LocalDate.of(2015, 6, 6));
		this.myChildTest.setImage(this.myImageTest);
		this.idChild = this.childService.saveChild(this.myChildTest);
		System.out.print(this.idChild);
	}
	
	@Test
	public void testFindAllChild() {
		System.out.print("---testFindAllChild---");
		List<Child> childs = this.childService.findAllChilds();
		assertTrue(childs.isEmpty());
	}
	
	@Test
	public void testSaveChild() {
		System.out.print("---testSaveChild---");
		// Family set
		Family family = new Family();
		family.setLabel("William, Julie & Adrien");
		this.familyService.saveFamily(family);
		
		// Image set
		Image image = new Image();
		image.setUrl("william-logo.png");
		
		// Child set
		Child child = new Child();
		child.setFamily(family);
		child.setFirstname("William");
		child.setLastname("Ramarojohn");
		child.setBirthday(LocalDate.of(2016, 7, 7));
		child.setImage(image);
		
		long idChild = this.childService.saveChild(child);
		assertNotNull(idChild);
	}
	
	@Test
	public void testFindChild() {
		System.out.print("---testFindChild---");
		Child child = this.childService.findById(this.idChild);
		assertNotNull(child);
	}
	
	@Test
	public void testUpdateChild() {
		System.out.print("---testUpdateChild---");
		this.myChildTest.setLastname("Guillaume");
		this.childService.updateChild(this.myChildTest);
		Child child = this.childService.findById(this.idChild);
		assertEquals(this.myChildTest, child);
	}
	
	@Test
	public void testDeleteChild() {
		System.out.print("---testDeleteChild---");
		this.childService.deleteChildById(this.idChild);
	}
	
}
