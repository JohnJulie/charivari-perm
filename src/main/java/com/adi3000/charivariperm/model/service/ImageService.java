package com.adi3000.charivariperm.model.service;

import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Image;

public interface ImageService {

	long saveImage(Image image);
	
	List<Image> findAllImages();
	
	void deleteImageById(Long id);
	
	Image findById(Long id);
	
	void updateImage(Image image);
}
