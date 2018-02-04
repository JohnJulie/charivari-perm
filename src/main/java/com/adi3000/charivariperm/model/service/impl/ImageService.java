package com.adi3000.charivariperm.model.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.adi3000.charivariperm.model.dao.impl.ImageDao;
import com.adi3000.charivariperm.model.dataobject.Image;
import com.adi3000.common.orm.dao.DAOException;
import com.adi3000.common.orm.spring.TransactionalReadOnly;
import com.adi3000.common.orm.spring.TransactionalUpdate;

@Service("imageService")
public class ImageService  implements com.adi3000.charivariperm.model.service.ImageService {

	@Inject
    private ImageDao dao;
    
	@TransactionalUpdate
    public long saveImage(Image image) {
		long id = Long.MAX_VALUE;
        try {
			id = (long) dao.save(image);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
    }
 
	@TransactionalReadOnly
    public List<Image> findAllImages() {
        return dao.findAll();
    }
 
	@TransactionalUpdate
    public void deleteImageById(Long id) {
        try {
			dao.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @TransactionalReadOnly
    public Image findById(Long id) {
        return dao.find(id);
    }
 
    @TransactionalUpdate
    public void updateImage(Image image){
        try {
			dao.update(image);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
