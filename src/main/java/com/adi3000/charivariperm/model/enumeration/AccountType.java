package com.adi3000.charivariperm.model.enumeration;

public enum AccountType {
	ADMIN("0"),
	PARENT("1");
	
	private final String num;

	private AccountType(String num) {
		this.num = num;
	}
	
	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}
}
