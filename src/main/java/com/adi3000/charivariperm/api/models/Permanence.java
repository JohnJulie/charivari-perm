package com.adi3000.charivariperm.api.models;

import java.util.Date;

import com.adi3000.charivariperm.api.models.enums.PermanenceStatus;

public class Permanence {

	private Long id;
	private Date startDate;
	private Date endDate;
	private Family parents;
	private PermanenceStatus status;
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the parents
	 */
	public Family getParents() {
		return parents;
	}
	/**
	 * @param parents the parents to set
	 */
	public void setParents(Family parents) {
		this.parents = parents;
	}
	/**
	 * @return the status
	 */
	public PermanenceStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(PermanenceStatus status) {
		this.status = status;
	}
	
}
