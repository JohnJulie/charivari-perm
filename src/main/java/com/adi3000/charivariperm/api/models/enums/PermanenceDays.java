package com.adi3000.charivariperm.api.models.enums;

public enum PermanenceDays {
	MONDAY(1, "Lundi"),
	TUESDAY(2, "Mardi"),
	WEDNESDAY(3, "Mercredi"),
	THURSDAY(4, "Jeudi"),
	FRIDAY(5, "Vendredi");
	
	private final int num;
	private final String label;
	
	PermanenceDays(int num, String label) {
		this.num = num;
		this.label = label;
	}
	
	public int getNum() {
		return num;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PermanenceDays parse(int id) {
		PermanenceDays result = null;
		for (PermanenceDays status : PermanenceDays.values()) {
			if (status.getNum() == id) {
				result = status;
				break;
			}
		}
		return result;
	}
}
