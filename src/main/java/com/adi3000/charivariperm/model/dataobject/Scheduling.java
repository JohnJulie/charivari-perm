package com.adi3000.charivariperm.model.dataobject;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SCHEDULING")
public class Scheduling {
	
	public static final TimeUnit FREQUENCY_UNIT = TimeUnit.DAYS;
	public static final TimeUnit DURATION_UNIT = TimeUnit.MINUTES;

	@Id
	@Column(name = "SCHEDULING_ID")
	private Long id;
	
	@Column(name = "START_DAY")
	private LocalDateTime startHour;
	
	@Column(name = "DURATION")
	private Integer duration;
	
	@Column(name = "FREQUENCY")
	private Integer frequency;
	
	@ManyToOne
	@JoinColumn(name = "FAMILY_ID")
	private Family family;
	
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
	 * @return the startHour
	 */
	public LocalDateTime getStartHour() {
		return startHour;
	}
	/**
	 * @param startHour the startHour to set
	 */
	public void setStartHour(LocalDateTime startHour) {
		this.startHour = startHour;
	}
	/**
	 * @return the duration : for now duration is set in minutes
	 */
	public Integer getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	/**
	 * @return the frequency : for now duration is set in days
	 */
	public Integer getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
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
