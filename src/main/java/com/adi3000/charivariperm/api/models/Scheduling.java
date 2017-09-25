package com.adi3000.charivariperm.api.models;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.adi3000.charivariperm.api.models.enums.PermanenceDays;

public class Scheduling {
	
	public static final TimeUnit FREQUENCY_UNIT = TimeUnit.DAYS;
	public static final TimeUnit DURATION_UNIT = TimeUnit.MINUTES;

	private Long id;
	private PermanenceDays day;
	private Date startHour;
	private Integer duration;
	private Integer frequency;
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
	 * @return the day
	 */
	public PermanenceDays getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(PermanenceDays day) {
		this.day = day;
	}
	/**
	 * @return the startHour
	 */
	public Date getStartHour() {
		return startHour;
	}
	/**
	 * @param startHour the startHour to set
	 */
	public void setStartHour(Date startHour) {
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
	
}
