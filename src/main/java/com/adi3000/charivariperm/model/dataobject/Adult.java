package com.adi3000.charivariperm.model.dataobject;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.adi3000.charivariperm.common.PersonTypeConst;
import com.adi3000.charivariperm.model.enumeration.PersonType;

@Entity
@DiscriminatorValue(value = PersonTypeConst.ADULT_TYPE)
public class Adult extends Person {
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name = "EMAIL")
	private String mail;

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
