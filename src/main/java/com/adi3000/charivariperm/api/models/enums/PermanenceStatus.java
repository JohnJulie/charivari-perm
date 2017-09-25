package com.adi3000.charivariperm.api.models.enums;

public enum PermanenceStatus {
	DONE(1, "Ok"),
	REPLACEMENT(2, "Remplacement"),
	CANCEL(3, "Pas de remplacement"),
	NOT_CONFIRMED(4, "A confirmer");
	
	private final int num;
	private final String label;
	
	private PermanenceStatus(int num, String label) {
		this.num = num;
		this.label = label;
	}
	
	
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}


	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}


	public static PermanenceStatus parse(int id) {
		PermanenceStatus result = null;
		for (PermanenceStatus status : PermanenceStatus.values()) {
			if (status.getNum() == id) {
				result = status;
				break;
			}
		}
		return result;
	}
}
