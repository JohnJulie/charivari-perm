package com.adi3000.charivariperm.model.dataobject;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.adi3000.charivariperm.common.PersonTypeConst;

@Entity
@DiscriminatorValue(value = PersonTypeConst.CHILD_TYPE)
public class Child extends Person {

	@ManyToOne
	@JoinColumn(name = "IMAGE_ID")
	private Image image;

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	
	
	
}
