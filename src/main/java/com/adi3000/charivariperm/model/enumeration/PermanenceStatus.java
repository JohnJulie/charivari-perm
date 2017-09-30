package com.adi3000.charivariperm.model.enumeration;

public enum PermanenceStatus {
	DONE(0, "Ok"),
	REPLACEMENT(1, "Remplacement"),
	CANCEL(2, "Pas de remplacement"),
	NOT_CONFIRMED(3, "A confirmer");
	
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
