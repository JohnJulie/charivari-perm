package com.adi3000.charivariperm.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import com.adi3000.charivariperm.api.models.enums.PermanenceStatus;

@Entity
@Table(name="PERMANENCE")
public class Permanence {

	@Id
	@Column(name = "PERMANENCE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "START_DATE")
	private LocalDate startDate;
	
	@Column(name = "END_DATE")
	private LocalDate endDate;
	
	@ManyToOne
	@JoinColumn(name = "FAMILY_ID")
	private Family family;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATUS")
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
	public LocalDate getStartDate() {
		return startDate;
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
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the family
	 */
	public Family getFamily() {
		return family;
	}
	/**
	 * @param family the family to set
	 */
	public void setFamily(Family family) {
		this.family = family;
	}
	
	
}
