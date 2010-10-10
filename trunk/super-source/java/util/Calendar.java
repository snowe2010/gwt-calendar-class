package java.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * A wrapper around the java Date object so that accessors for year, month, date, hours, minutes, and day returns values
 * according to the given time zone.
 *
 * @author jonathan
 *
 */
public class Calendar implements DateConstants
{

	private static int firstDayOfWeek;

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

	protected Calendar()
	{

	}

	protected Calendar(TimeZone timeZone, Locale aLocale)
	{
		this(new Date(), timeZone);
	}




	/**
	 * Allocates a date and initializes it to represent the same "number of milliseconds since epoch" as the given date.
	 *
	 * @param date
	 *            the date which represents an instant in time since epoch
	 */
	private Calendar(Date date, TimeZone timeZone)
	{
		this.timeZone = timeZone;
		setTime(date);
	}

	//methods from API
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

	public boolean after(Object when)
	{
		if( when instanceof Calendar)
		{
			return after(((Calendar)when).getTime());
		}

		throw new RuntimeException("unsupported object passed in");
	}

	public boolean after(Calendar when)
	{
		return after(when.getTime());
	}

	public boolean after(Date when)
	{
		ensureDateCalculated();
		return calculatedDate.after(when);
	}

	public boolean before(Object when)
	{
		//TODO, fix
		return false;
	}

	public void clear()
	{
		//TODO, fix
	}

	public void clear( int field)
	{
		//TODO, fix
	}

	public int compareTo(Calendar anotherCalendar)
	{
		if( after(anotherCalendar.getTime()))
		{
			return 1;
		}

		if( equals(anotherCalendar))
		{
			return 0;
		}

		return -1;
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
				return localDateTime.hour;

			case HOUR_OF_DAY:
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

	public int getActualMaximum(int field)
	{
		//TODO, fix
		return 0;
	}

	public int getActualMinimum(int field)
	{
		//TODO, fix
		return 0;
	}

	public static Locale[] getAvailableLocales()
	{
		//TODO, fix
		return null;
	}

	public int getFirstDayOfWeek()
	{
		// Gets what the first day of the week is; e.g., SUNDAY in the U.S., MONDAY in France.
		//TODO, fix
		return 0;
	}

	public static Calendar getInstance()
	{
		return getInstance(TimeZone.getDefault());
	}

	public static Calendar getInstance(Locale aLocale)
	{
		//TODO, should use locale parsing instead
		return getInstance(TimeZone.getDefault());
	}

	public static Calendar getInstance(TimeZone zone)
	{
		Calendar calendar = new Calendar(new Date(), zone);
		return calendar;
	}

	public static Calendar getInstance(TimeZone zone, Locale aLocale)
	{
		Calendar calendar = new Calendar(new Date(), zone);
		return calendar;
	}

	public int getMinimalDaysInFirstWeek()
	{
		//TODO, fix
		return 0;
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

	public long getTimeInMillis()
	{
		ensureDateCalculated();
		return calculatedDate.getTime();
	}

	public TimeZone getTimeZone()
	{
		return timeZone;
	}


	public boolean 	isLenient()
	{
		//TODO, fix
		return false;
	}

	public boolean isSet(int field)
	{
		//TODO, fix
		return false;
	}

	public void roll(int field, int amount)
	{
		//TODO, fix
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
				//TODO, shouldn't behave like hour of day but, will fix later

			case HOUR_OF_DAY:
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

	public void set(int year, int month, int date)
	{
		set(YEAR, year);
		set(MONTH, month);
		set(DATE, date);
	}

	public void	set(int year, int month, int date, int hourOfDay, int minute)
	{
		set(year, month, date);
		set(HOUR_OF_DAY, hourOfDay);
		set(MINUTE, minute);
	}

	public void set(int year, int month, int date, int hourOfDay, int minute, int second)
	{
		set(year, month, date, hourOfDay, minute);
		set(SECOND, second);
	}

	public void setFirstDayOfWeek(int value)
	{
		if( value >= SUNDAY && value <= SATURDAY)
		{
			firstDayOfWeek = value;
		}
		else
		{
			//TODO, see what the jvm does here and emulate, for now throw an exception
			throw new RuntimeException("bad value passed in");
		}
	}

	public void setLenient(boolean lenient)
	{
		//TODO, fix
	}

	public void setMinimalDaysInFirstWeek(int value)
	{
		//TODO, fix
	}

	public void setTime(Date time)
	{
		this.localDateTime = LocalDateTime.extractFromDate(time, timeZone.timeZoneContainer.getTimeZone());
		this.calculatedDate = new Date(time.getTime());
		needsCalculation = false;
	}

	public void setTimeInMillis(long time)
	{
		setTime(new Date(time));
	}

	public void setTimeZone(TimeZone timeZone)
	{
		this.timeZone = timeZone;
		ensureDateCalculated();

	}

	@Override
	public String toString()
	{
		//TODO, fix
		return null;
	}


	//private methods



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
			calculatedDate = Calendar.createDate(
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


	/**
	 * Adjusts the calendar's time to the start, or 0:00.00.000, of it's current day.
	 *
	 * @param cal
	 *            the calendar to adjust
	 */
	public void adjustToStartOfDay()
	{
		set(Calendar.HOUR, 0);
		set(Calendar.MINUTE, 0);
		set(Calendar.SECOND, 0);
		set(Calendar.MILLISECOND, 0);
	}

	/**
	 * Adjusts the calendar's time to the end, or 23:59.59.999 of it's current day.
	 *
	 * @param cal
	 *            the calendar to adjust
	 */
	public void adjustToEndOfDay()
	{
		set(Calendar.HOUR, 23);
		set(Calendar.MINUTE, 59);
		set(Calendar.SECOND, 59);
		set(Calendar.MILLISECOND, 999);
	}



	public boolean before(Calendar when)
	{
		return before(when.getTime());
	}

	public boolean before(Date when)
	{
		ensureDateCalculated();
		return calculatedDate.before(when);
	}



	private int getDay()
	{
		ensureDateCalculated();

			assert timeZone != null : "TimeZone must be set";

			// TODO: See if this can be improved
			String day = DateTimeFormat.getFormat("EEE").format(calculatedDate, timeZone.timeZoneContainer.getTimeZone());
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
		int offsetConversion = d.getTimezoneOffset() - timeZone.timeZoneContainer.getTimeZone().getStandardOffset();
		d.setMinutes(d.getMinutes() - offsetConversion);
		d.setMinutes(d.getMinutes() - timeZone.timeZoneContainer.getTimeZone().getDaylightAdjustment(d));
		return d;
	}

	// TODO: SEE WHY WE NEED THIS
	@SuppressWarnings("deprecation")
	public static Date revertDate(Date date, TimeZone timeZone)
	{

		int offsetConversion = date.getTimezoneOffset() - timeZone.timeZoneContainer.getTimeZone().getStandardOffset();
		long newTime = date.getTime() - (offsetConversion + timeZone.timeZoneContainer.getTimeZone().getDaylightAdjustment(date)) * 60000;
		Date newDate = new Date(newTime);
		return newDate;
	}
}
