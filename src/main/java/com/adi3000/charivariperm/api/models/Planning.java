package com.adi3000.charivariperm.api.models;

import java.util.Date;

import com.adi3000.charivariperm.api.models.enums.PermanenceDays;

public class Planning {

	public long idPlanning;
	public PermanenceDays day;
	public Date startHour;
	public int duration;
	public int frequency;
	
}
