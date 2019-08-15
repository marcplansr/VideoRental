package com.videorental.utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateHelpers {

	public static Date datePlusDays(Date initialDate, int daysToAdd) {
		LocalDateTime localDateTimePlusOneDay = initialDate.toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDateTime()
				.plusDays(daysToAdd);
		Date currentDatePlusDays = Date.from(localDateTimePlusOneDay
				.atZone(ZoneId.systemDefault()).toInstant());
		return currentDatePlusDays;
	}

	public static Date todayDate() {
		return new Date();
	}

	public static int daysBetween(Date date1, Date date2) {
		return (int) ((date2.getTime() - date1.getTime())
				/ (1000 * 60 * 60 * 24));
	}
}
