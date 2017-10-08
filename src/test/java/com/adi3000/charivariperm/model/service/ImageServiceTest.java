package com.adi3000.charivariperm.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.model.dataobject.Image;
import com.adi3000.charivariperm.model.dataobject.Image;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
@Transactional
public class ImageServiceTest {

	@Inject
	private transient ImageService imageService;
	private Image myImageTest;
	private long idValue;

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}
	
	@Before
	public void setUp () {
		System.out.print("---@Before---");
		this.myImageTest = new Image();
		this.myImageTest.setUrl("william-logo.png");
		this.idValue = this.imageService.saveImage(this.myImageTest);
		this.myImageTest.setId(this.idValue);
		System.out.print(this.idValue);
	}
	
	@Test
	public void testFindAllImages() {
		System.out.print("---testFindAllImages---");
		List<Image> families = this.imageService.findAllImages();
		assertTrue(families.isEmpty());
	}
	
	@Test
	public void testSaveImage() {
		System.out.print("---testSaveImage---");
		Image image = new Image();
		image.setUrl("armand-logo.png");
		long idImage = this.imageService.saveImage(this.myImageTest);
		assertNotNull(idImage);
	}
	
	@Test
	public void testFindImage() {
		System.out.print("---testFindImage---");
		Image image = this.imageService.findById(this.idValue);
		assertNotNull(image);
	}
	
	@Test
	public void testUpdateImage() {
		System.out.print("---testUpdateImage---");
		this.myImageTest.setUrl("william-logo.jpg");
		this.imageService.updateImage(this.myImageTest);
		Image image = this.imageService.findById(this.idValue);
		assertEquals(this.myImageTest, image);
	}
	
	@Test
	public void testDeleteImage() {
		System.out.print("---testDeleteImage---");
		this.imageService.deleteImageById(this.idValue);
	}

}
