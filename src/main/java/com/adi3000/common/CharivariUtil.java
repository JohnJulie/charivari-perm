package com.adi3000.common;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class CharivariUtil {

	private CharivariUtil() {}
	
	public static Date getDateFromLocalDateTime(LocalDateTime dateTime) {
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static LocalDateTime getLocalDateTimeFromDate(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
}
