package java.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;

/**
 * A date-time without time-zone. Currently package private until API is formalized.
 * 
 * @author jonathan
 * 
 */
class LocalDateTime
{
	int year;

	int month;

	int date;

	int hours;

	int minutes;

	int seconds;

	int milliseconds;


	@Override
	public String toString()
	{
		return (year + 1900) + "-" + month + "-" + date + " " + hours + ":" + minutes + "." + seconds + "."
				+ milliseconds;
	}

	static LocalDateTime extractFromDate(Date date, TimeZone timeZone)
	{
		LocalDateTime i = new LocalDateTime();
		i.year = getYearFromDate(date, timeZone);
		i.month = getMonthFromDate(date, timeZone);
		i.date = getDateFromDate(date, timeZone);
		i.hours = getHoursFromDate(date, timeZone);
		i.minutes = getMinutesFromDate(date, timeZone);
		i.seconds = getSecondsFromDate(date, timeZone);
		i.milliseconds = getMillisecondsFromDate(date, timeZone);
		return i;
	}

	private static int getYearFromDate(Date date, TimeZone timeZone)
	{
		
			assert timeZone != null : "TimeZone must be set";
			return Integer.parseInt(DateTimeFormat.getFormat("yyyy").format(date, timeZone)) - 1900;
		

	}

	private static int getMonthFromDate(Date date, TimeZone timeZone)
	{
		
			assert timeZone != null : "TimeZone must be set";
			return Integer.parseInt(DateTimeFormat.getFormat("M").format(date, timeZone)) - 1;
		
	}

	private static int getDateFromDate(Date date, TimeZone timeZone)
	{
		
			assert timeZone != null : "TimeZone must be set";
			return Integer.parseInt(DateTimeFormat.getFormat("d").format(date, timeZone));
		
	}

	private static int getHoursFromDate(Date date, TimeZone timeZone)
	{
		
			assert timeZone != null : "TimeZone must be set";
			return Integer.parseInt(DateTimeFormat.getFormat("H").format(date, timeZone));
		
	}

	private static int getMinutesFromDate(Date date, TimeZone timeZone)
	{
		
			assert timeZone != null : "TimeZone must be set";
			return Integer.parseInt(DateTimeFormat.getFormat("m").format(date, timeZone));
		
	}

	private static int getSecondsFromDate(Date date, TimeZone timeZone)
	{
		
			assert timeZone != null : "TimeZone must be set";
			return Integer.parseInt(DateTimeFormat.getFormat("s").format(date, timeZone));
		
	}

	private static int getMillisecondsFromDate(Date date, TimeZone timeZone)
	{
		
			assert timeZone != null : "TimeZone must be set";
			return Integer.parseInt(DateTimeFormat.getFormat("S").format(date, timeZone));
		
	}
}
