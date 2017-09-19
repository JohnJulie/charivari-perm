package com.adi3000.charivariperm.api.models;

import java.util.Date;

import com.adi3000.charivariperm.api.models.enums.PermanenceStatus;

public class Permanence {

	public long idPermanence;
	public Date startDate;
	public Date endDate;
	public Parents parents;
	public PermanenceStatus status;
	
}
