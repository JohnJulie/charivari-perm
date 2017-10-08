package com.adi3000.charivariperm.model.enumeration;

public enum PersonType {
	ADULT("0"),
	CHILD("1");
	
	private final String num;

	private PersonType(String num) {
		this.num = num;
	}
	
	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}
}
