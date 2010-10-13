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
	int era;
	
	int year;

	int month;

	int date;

	int hourOfDay;

	private int hour;

	int minutes;

	int seconds;

	int milliseconds;





	public int getHour() {
		return hour;
	}



	@Override
	public String toString()
	{
		return year + "-" + month + "-" + date + " " + hourOfDay + ":" + minutes + "." + seconds + "."
				+ milliseconds;
	}

	static LocalDateTime extractFromDate(Date date, TimeZone timeZone)
	{
		LocalDateTime i = new LocalDateTime();
		i.era = getEraFromDate(date, timeZone);
		i.year = getYearFromDate(date, timeZone);
		i.month = getMonthFromDate(date, timeZone);
		i.date = getDateFromDate(date, timeZone);
		i.hourOfDay = getHourOfDayFromDate(date, timeZone);
		i.hour = getHourFromDate(date, timeZone);
		i.minutes = getMinutesFromDate(date, timeZone);
		i.seconds = getSecondsFromDate(date, timeZone);
		i.milliseconds = getMillisecondsFromDate(date, timeZone);
		return i;
	}

	private static int getEraFromDate(Date date2, TimeZone timeZone) {
		assert timeZone != null : "TimeZone must be set";
		if(DateTimeFormat.getFormat("G").format(date2, timeZone).equals("AD"))
		{
			return 1;
		}
		
		return 0;
	}



	private static int getYearFromDate(Date date, TimeZone timeZone)
	{

			assert timeZone != null : "TimeZone must be set";
			return Integer.parseInt(DateTimeFormat.getFormat("yyyy").format(date, timeZone));


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

	private static int getHourOfDayFromDate(Date date, TimeZone timeZone)
	{

			assert timeZone != null : "TimeZone must be set";
			return Integer.parseInt(DateTimeFormat.getFormat("H").format(date, timeZone));

	}


	private static int getHourFromDate(Date date, TimeZone timeZone)
	{

			assert timeZone != null : "TimeZone must be set";
			return Integer.parseInt(DateTimeFormat.getFormat("h").format(date, timeZone)) % 12;

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



	public void setValuesFromDate(Date aDate) {
		if(DateTimeFormat.getFormat("G").format(aDate).equals("AD"))
		{
			era = 1;
		}
		else
		{
			era = 0;
		}
		
		year = (aDate.getYear() + 1900);
		month = aDate.getMonth();
		date = aDate.getDate();
		hourOfDay = aDate.getHours();
		minutes = aDate.getMinutes();
		seconds = aDate.getSeconds();
		milliseconds = (int) (aDate.getTime() % 1000l);
		
		//now set fields that are calculated
		hour = Integer.parseInt(DateTimeFormat.getFormat("h").format(aDate)) % 12;
		
		
	}
}
