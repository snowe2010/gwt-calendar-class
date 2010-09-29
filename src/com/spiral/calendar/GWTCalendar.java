package com.spiral.calendar;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;

/**
 * A wrapper around the java Date object so that accessors for year, month, date, hours, minutes, and day returns values
 * according to the given time zone.
 *
 * @author jonathan
 *
 */
public class GWTCalendar implements DateConstants
{

	private TimeZone timeZone;

	private LocalDateTime localDateTime;

	private Date calculatedDate;

	/**
	 * True if months, dates, hours, minutes, seconds, and milliseconds need to be rolled over and the java.util.Date
	 * needs to be calculated.
	 */
	private boolean needsCalculation;

	/**
	 * Allocates a date and initializes it so that it represents the time at which it was allocated, measured to the
	 * nearest millisecond.
	 */

	protected GWTCalendar()
	{

	}

	GWTCalendar(TimeZone timeZone)
	{
		this(new Date(), timeZone);
	}

	/**
	 * Allocates a date and initializes it so that it represents midnight, in the given time zone, at the beginning of
	 * the day specified by the year, month, and date arguments.
	 *
	 * @param year
	 *            the year minus 1900.
	 * @param month
	 *            the month between 0-11.
	 * @param date
	 *            the day of the month between 1-31.
	 * @param hours
	 *            the hours between 0-23.
	 * @param minutes
	 *            the minutes between 0-59.
	 * @param timeZone
	 *            the time zone of the date
	 */
	public GWTCalendar(int year, int month, int date, TimeZone timeZone)
	{
		this(year, month, date, 0, 0, 0, timeZone);
	}

	/**
	 * Allocates a date and initializes it so that it represents the instant at the start of the minute specified by the
	 * year, month, date, hrs, and min arguments, in the given time zone.
	 *
	 * @param year
	 *            the year minus 1900.
	 * @param month
	 *            the month between 0-11.
	 * @param date
	 *            the day of the month between 1-31.
	 * @param hours
	 *            the hours between 0-23.
	 * @param minutes
	 *            the minutes between 0-59.
	 * @param timeZone
	 *            the time zone of the date
	 */
	public GWTCalendar(int year, int month, int date, int hours, int minutes, TimeZone timeZone)
	{
		this(year, month, date, hours, minutes, 0, timeZone);
	}

	/**
	 * Allocates a date and initializes it so that it represents the instant at the start of the second specified by the
	 * year, month, date, hrs, min, and sec arguments, in the given time zone.
	 *
	 * @param year
	 *            the year minus 1900.
	 * @param month
	 *            the month between 0-11.
	 * @param date
	 *            the day of the month between 1-31.
	 * @param hours
	 *            the hours between 0-23.
	 * @param minutes
	 *            the minutes between 0-59.
	 * @param seconds
	 *            the seconds between 0-59.
	 * @param timeZone
	 *            the time zone of the date
	 */
	public GWTCalendar(int year, int month, int date, int hours, int minutes, int seconds, TimeZone timeZone)
	{
		this.timeZone = timeZone;
		localDateTime = new LocalDateTime();
		localDateTime.year = year;
		localDateTime.month = month;
		localDateTime.date = date;
		localDateTime.hours = hours;
		localDateTime.minutes = minutes;
		localDateTime.seconds = seconds;
		localDateTime.milliseconds = 0;
		needsCalculation = true;
	}

	/**
	 * Allocates a date and initializes it to represent the same "number of milliseconds since epoch" as the given date.
	 *
	 * @param date
	 *            the date which represents an instant in time since epoch
	 */
	public GWTCalendar(Date date, TimeZone timeZone)
	{
		this.timeZone = timeZone;
		setTime(date);
	}

	/**
	 * Allocates a date and initializes it to represent the specified number of milliseconds since the standard base
	 * time known as "the epoch", namely January 1, 1970, 00:00:00 GMT.
	 *
	 * @param millis
	 *            the milliseconds since January 1, 1970, 00:00:00 GMT.
	 */
	public GWTCalendar(long millis, TimeZone timeZone)
	{
		this(new Date(millis), timeZone);
	}

	@SuppressWarnings("deprecation")
	private void ensureDateCalculated()
	{
		if (needsCalculation)
		{
			// The local date time is put into a Date object solely for the sole purpose of rolling up fields. For
			// instance, 90 seconds should roll up to 1 minute 30 seconds, which may roll up minutes, etc.
			Date date = new Date(
				localDateTime.year,
				localDateTime.month,
				localDateTime.date,
				localDateTime.hours,
				localDateTime.minutes,
				localDateTime.seconds);

			// Date class does not have milliseconds manipulation.
			date.setTime(date.getTime() + localDateTime.milliseconds);

			// Store the rolled up fields back in the original LocalDateTime object.
			localDateTime.year = date.getYear();
			localDateTime.month = date.getMonth();
			localDateTime.date = date.getDate();
			localDateTime.hours = date.getHours();
			localDateTime.minutes = date.getMinutes();
			localDateTime.seconds = date.getSeconds();
			localDateTime.milliseconds = (int) (date.getTime() % 1000l);

			// Add time zone to the LocalDateTime to construct a real Date (thus with timezone)
			calculatedDate = GWTCalendar.createDate(
				localDateTime.year,
				localDateTime.month,
				localDateTime.date,
				localDateTime.hours,
				localDateTime.minutes,
				localDateTime.seconds,
				localDateTime.milliseconds,
				timeZone);

			needsCalculation = false;
		}
	}

	public static GWTCalendar getInstance(TimeZone zone)
	{
		GWTCalendar calendar = new GWTCalendar(new Date(), zone);
		return calendar;
	}

	/**
	 * Adjusts the calendar's time to the start, or 0:00.00.000, of it's current day.
	 *
	 * @param cal
	 *            the calendar to adjust
	 */
	public void adjustToStartOfDay()
	{
		set(GWTCalendar.HOUR, 0);
		set(GWTCalendar.MINUTE, 0);
		set(GWTCalendar.SECOND, 0);
		set(GWTCalendar.MILLISECOND, 0);
	}

	/**
	 * Adjusts the calendar's time to the end, or 23:59.59.999 of it's current day.
	 *
	 * @param cal
	 *            the calendar to adjust
	 */
	public void adjustToEndOfDay()
	{
		set(GWTCalendar.HOUR, 23);
		set(GWTCalendar.MINUTE, 59);
		set(GWTCalendar.SECOND, 59);
		set(GWTCalendar.MILLISECOND, 999);
	}

	public boolean after(GWTCalendar when)
	{
		return after(when.getTime());
	}

	public boolean after(Date when)
	{
		ensureDateCalculated();
		return calculatedDate.after(when);
	}

	public boolean before(GWTCalendar when)
	{
		return before(when.getTime());
	}

	public boolean before(Date when)
	{
		ensureDateCalculated();
		return calculatedDate.before(when);
	}

	public void set(int field, int amount)
	{
		switch (field)
		{
			case YEAR:
				localDateTime.year = amount;
				needsCalculation = true;
				break;

			case MONTH:
				localDateTime.month = amount;
				needsCalculation = true;
				break;

			case DATE:
				localDateTime.date = amount;
				needsCalculation = true;
				break;

			case HOUR:
				localDateTime.hours = amount;
				needsCalculation = true;
				break;

			case MINUTE:
				localDateTime.minutes = amount;
				needsCalculation = true;
				break;

			case SECOND:
				localDateTime.seconds = amount;
				needsCalculation = true;
				break;

			case MILLISECOND:
				localDateTime.milliseconds = amount;
				needsCalculation = true;
				break;

			default:
				throw new RuntimeException("Field isn't supported yet, or bad value passed in");
		}
	}

	public void add(int field, int amount)
	{
		switch (field)
		{
			case YEAR:
				localDateTime.year = amount;
				needsCalculation = true;
				break;

			case MONTH:
				localDateTime.month += amount;
				needsCalculation = true;
				break;

			case DATE:
				localDateTime.date += amount;
				needsCalculation = true;
				break;

			case HOUR:
				localDateTime.date += amount;
				needsCalculation = true;
				break;

			case MINUTE:
				localDateTime.minutes += amount;
				needsCalculation = true;
				break;

			case SECOND:
				localDateTime.seconds += amount;
				needsCalculation = true;
				break;

			case MILLISECOND:
				localDateTime.milliseconds += amount;
				needsCalculation = true;
				break;

			default:
				throw new RuntimeException("Field isn't supported yet, or bad value passed in");
		}
	}

	public void setTimeZone(TimeZone timeZone)
	{
		this.timeZone = timeZone;
		ensureDateCalculated();

	}

	/**
	 * Returns the date with respect to time zone. Thus, all accessors on this returned date will return month, date,
	 * year, hours, minutes, seconds with respect to browser time zone.
	 *
	 * @return an equivalent Date in the browser's time zone.
	 */
	public Date getTime()
	{
		ensureDateCalculated();
		return new Date(calculatedDate.getTime());
	}

	public int get(int field)
	{
		switch (field)
		{
			case YEAR:
				ensureDateCalculated();
				return localDateTime.year;

			case MONTH:
				ensureDateCalculated();
				return localDateTime.month;

			case HOUR:
				ensureDateCalculated();
				return localDateTime.hours;

			case DATE:
				ensureDateCalculated();
				return localDateTime.date;

			case DAY_OF_WEEK:
				return getDay();

			case MINUTE:
				ensureDateCalculated();
				return localDateTime.minutes;

			case SECOND:
				ensureDateCalculated();
				return localDateTime.seconds;

			default:
				throw new RuntimeException("Field isn't supported yet, or bad value passed in");
		}
	}

	private int getDay()
	{
		ensureDateCalculated();

			assert timeZone != null : "TimeZone must be set";

			// TODO: See if this can be improved
			String day = DateTimeFormat.getFormat("EEE").format(calculatedDate, timeZone);
			if (day.equalsIgnoreCase("Sun"))
			{
				return 0;
			}
			else if (day.equalsIgnoreCase("Mon"))
			{
				return 1;
			}
			else if (day.equalsIgnoreCase("Tue"))
			{
				return 2;
			}
			else if (day.equalsIgnoreCase("Wed"))
			{
				return 3;
			}
			else if (day.equalsIgnoreCase("Thu"))
			{
				return 4;
			}
			else if (day.equalsIgnoreCase("Fri"))
			{
				return 5;
			}
			else if (day.equalsIgnoreCase("Sat"))
			{
				return 6;
			}
			throw new RuntimeException("Cannot convert day (" + day + ") into numerical day");

	}

	public TimeZone getTimeZone()
	{
		return timeZone;
	}

	public void setTime(Date time)
	{
		this.localDateTime = LocalDateTime.extractFromDate(time, timeZone);
		this.calculatedDate = new Date(time.getTime());
		needsCalculation = false;
	}

	public void setTimeInMillis(long time)
	{
		setTime(new Date(time));
	}

	public long getTimeInMillis()
	{
		ensureDateCalculated();
		return calculatedDate.getTime();
	}

	@Override
	public boolean equals(Object obj)
	{
		ensureDateCalculated();
		return calculatedDate.equals(obj);
	}

	@Override
	public int hashCode()
	{
		ensureDateCalculated();
		return calculatedDate.hashCode();
	}

	@SuppressWarnings("deprecation")
	private static Date createDate(int year, int month, int date, int hours, int minutes, int seconds,
			int milliseconds, TimeZone timeZone)
	{
		// This date is initially incorrect since it will be in browser time zone
		// We shall mutate it to respect the given time zone
		Date d = new Date(year, month, date, hours, minutes, seconds);
		d.setTime(d.getTime() + milliseconds);


		// Console.log("GWTDate createDate " + d);
		int offsetConversion = d.getTimezoneOffset() - timeZone.getStandardOffset();
		d.setMinutes(d.getMinutes() - offsetConversion);
		d.setMinutes(d.getMinutes() - timeZone.getDaylightAdjustment(d));
		return d;
	}

	// TODO: SEE WHY WE NEED THIS
	@SuppressWarnings("deprecation")
	public static Date revertDate(Date date, TimeZone timeZone)
	{

		int offsetConversion = date.getTimezoneOffset() - timeZone.getStandardOffset();
		long newTime = date.getTime() - (offsetConversion + timeZone.getDaylightAdjustment(date)) * 60000;
		Date newDate = new Date(newTime);
		return newDate;
	}
}
