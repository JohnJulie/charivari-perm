package com.adi3000.charivariperm.model.dataobject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FAMILY")
public class Family {
	
	@Id
	@Column(name = "FAMILY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "LABEL", nullable = false)
	private String label;
	
	@ManyToOne
	@JoinColumn(name = "IMAGE_ID")
	private Image image;
	
	@Column(name = "START_DATE_CONTRACT")
	private Date startDateContract;
	
	@Column(name = "END_DATE_CONTRACT")
	private Date endDateContract;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
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
	/**
	 * @return the startDateContract
	 */
	public Date getStartDateContract() {
		return startDateContract;
	}
	/**
	 * @param startDateContract the startDateContract to set
	 */
	public void setStartDateContract(Date startDateContract) {
		this.startDateContract = startDateContract;
	}
	/**
	 * @return the endDateContract
	 */
	public Date getEndDateContract() {
		return endDateContract;
	}
	/**
	 * @param endDateContract the endDateContract to set
	 */
	public void setEndDateContract(Date endDateContract) {
		this.endDateContract = endDateContract;
	}
	
}
