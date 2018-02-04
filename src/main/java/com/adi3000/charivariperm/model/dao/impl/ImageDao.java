package com.adi3000.charivariperm.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.model.dataobject.Image;
import com.adi3000.common.orm.dao.AbstractDAO;

@Repository("imageDao")
public class ImageDao extends AbstractDAO<Image> implements com.adi3000.charivariperm.model.dao.ImageDao {

	private static final long serialVersionUID = 5618221216335645228L;
	
}
