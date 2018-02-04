package com.adi3000.common.orm.dao;

/**
 * 
 * @author adi
 *
 */
public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6472192227926562607L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

}