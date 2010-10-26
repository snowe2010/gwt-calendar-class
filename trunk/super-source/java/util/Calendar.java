package java.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.datepicker.client.CalendarUtil;

/**
 * A wrapper around the java Date object so that accessors for year, month, date, hours, minutes, and day returns values
 * according to the given time zone.
 *
 * @author jonathan
 *
 */
public class Calendar implements DateConstants
{

	private int firstDayOfWeek;

	private TimeZone timeZone;

	private LocalDateTime localDateTime;

	private Date calculatedDate;

	private int minimalDaysInFirstWeek = 1;

	private Integer overridenTZ = null;

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
		firstDayOfWeek = 1;
	}


	private void boundsCheck(int field, int amount)
	{
		switch (field)
		{
			case ERA:
			if( amount > getActualMaximum(Calendar.ERA) || amount < getActualMinimum(Calendar.ERA))
			{
				throw new IllegalArgumentException("Invalid era");
			}
		}
	}

	//methods from API
	public void add(int field, int amount)
	{
		switch (field)
		{
			case ERA:
				boundsCheck( field, amount + localDateTime.era);
				localDateTime.era += amount;
				needsCalculation = true;
				break;

			case YEAR:
				localDateTime.year += amount;
				needsCalculation = true;
				break;

			case WEEK_OF_YEAR:
				localDateTime.date += (amount * 7);
				break;

			case WEEK_OF_MONTH:
				localDateTime.date += (amount * 7);
				break;

			case MONTH:
				localDateTime.month += amount;
				needsCalculation = true;
				break;

			case DATE:
				localDateTime.date += amount;
				needsCalculation = true;
				break;


			case DAY_OF_YEAR:
				//TODO, implement
				break;

			case DAY_OF_WEEK:
				//TODO, implement
				break;

			case DAY_OF_WEEK_IN_MONTH:
				//TODO, implement
				break;

			case AM_PM:
				localDateTime.hourOfDay += (amount * 12);
				break;

			case HOUR:
				//TODO, fix

			case HOUR_OF_DAY:
				localDateTime.hourOfDay += amount;
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

				//mimicking the jvm
				throw new ArrayIndexOutOfBoundsException(field);
		}
	}

	public boolean after(Object when)
	{
		if( when instanceof Calendar)
		{
			ensureDateCalculated();
			return calculatedDate.after(((Calendar)when).getTime());

		}

		return false;
	}


	public boolean before(Object when)
	{
		if( when instanceof Calendar )
		{
			ensureDateCalculated();
			return calculatedDate.before(((Calendar)when).getTime());
		}

		return false;
	}

	public void clear()
	{
		localDateTime.era = 1;
		localDateTime.year = 1970;
		localDateTime.month = 0;
		localDateTime.date = 1;
		localDateTime.hourOfDay = 0;
		localDateTime.minutes = 0;
		localDateTime.seconds = 0;
		localDateTime.milliseconds = 0;
		overridenTZ = null;
		firstDayOfWeek = 1;

		needsCalculation = true;
	}

	public void clear( int field)
	{
		switch(field)
		{
			case ERA: localDateTime.era = 1;needsCalculation = true;break;
			case YEAR: localDateTime.year = 1970;needsCalculation = true;break;
			case WEEK_OF_YEAR: break; //TODO, implement
			case WEEK_OF_MONTH: break; //TODO, implement
			case MONTH: localDateTime.month = 1970;needsCalculation = true;break;
			case DATE: localDateTime.date = 1;needsCalculation = true;break;
			case DAY_OF_YEAR: break; //TODO, implement
			case DAY_OF_WEEK: firstDayOfWeek = 1; break; //TODO, implement
			case DAY_OF_WEEK_IN_MONTH: break; //TODO, implement
			case AM_PM: break; //seems to do nothing, implementing through a break
			case HOUR: localDateTime.hourOfDay = 0;needsCalculation = true;break;  //TODO, test to make sure this is correct
			case HOUR_OF_DAY: localDateTime.hourOfDay = 0;needsCalculation = true;break;
			case MINUTE: localDateTime.minutes = 0; needsCalculation = true;break;
			case SECOND: localDateTime.seconds = 0; needsCalculation = true;break;
			case MILLISECOND: localDateTime.milliseconds = 0; needsCalculation = true;break;
			case ZONE_OFFSET: overridenTZ = null; needsCalculation = true; break;
			case DST_OFFSET: break; //TODO, implement

			default:
				//emulating the jvm
				throw new ArrayIndexOutOfBoundsException(field);
		}


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
			case ERA:

				ensureDateCalculated();
				return localDateTime.era;

			case YEAR:
				ensureDateCalculated();
				return localDateTime.year;


			case MONTH:
				ensureDateCalculated();
				return localDateTime.month;

			case WEEK_OF_YEAR:
				ensureDateCalculated();
				Calendar firstDayOfYear = Calendar.getInstance(this.timeZone);
				firstDayOfYear.setTime(this.getTime());
				firstDayOfYear.set(Calendar.MONTH, 0);
				firstDayOfYear.set(Calendar.DATE, 1);
				firstDayOfYear.set(Calendar.HOUR, 0);
				firstDayOfYear.set(Calendar.MINUTE, 0);
				firstDayOfYear.set(Calendar.SECOND, 0);
				firstDayOfYear.set(Calendar.MILLISECOND, 0);
				int dayOfTheYear = firstDayOfYear.get(DAY_OF_WEEK);

				int difference =  getRealFirstDay() - dayOfTheYear;

				if( difference <= 0)
				{
					difference = 7 - dayOfTheYear + getRealFirstDay();
				}

				int daysBetween = CalendarUtil.getDaysBetween(firstDayOfYear.getTime(), calculatedDate);
				if( daysBetween >= difference)
				{
					return ((daysBetween - difference) / 7) + 2;
				}
				return 1;


			case WEEK_OF_MONTH:
				ensureDateCalculated();
				Calendar firstDayOfMonth = Calendar.getInstance(this.timeZone);
				firstDayOfMonth.setTime(this.getTime());
				firstDayOfMonth.set(Calendar.DATE, 1);
				firstDayOfMonth.set(Calendar.HOUR, 0);
				firstDayOfMonth.set(Calendar.MINUTE, 0);
				firstDayOfMonth.set(Calendar.SECOND, 0);
				firstDayOfMonth.set(Calendar.MILLISECOND, 0);

				dayOfTheYear = firstDayOfMonth.get(DAY_OF_WEEK);
				difference =  getRealFirstDay() - dayOfTheYear;

				if( difference <= 0)
				{
					difference = 7 - dayOfTheYear + getRealFirstDay();
				}

				daysBetween = CalendarUtil.getDaysBetween(firstDayOfMonth.getTime(), calculatedDate);
				if( daysBetween >= difference)
				{
					return ((daysBetween - difference) / 7) + 2;
				}
				return 1;

			case DATE:
				ensureDateCalculated();
				return localDateTime.date;

			case DAY_OF_YEAR:

				ensureDateCalculated();
				Calendar firstDay = Calendar.getInstance(this.timeZone);
				firstDay.setTime(this.getTime());
				firstDay.set(Calendar.MONTH, 0);
				firstDay.set(Calendar.DATE, 1);
				firstDay.set(Calendar.HOUR, 0);
				firstDay.set(Calendar.MINUTE, 0);
				firstDay.set(Calendar.SECOND, 0);
				firstDay.set(Calendar.MILLISECOND, 0);

				return CalendarUtil.getDaysBetween(firstDay.getTime(), calculatedDate) + 1;

			case DAY_OF_WEEK:
				return getDay();

			case DAY_OF_WEEK_IN_MONTH:

				//TODO, fix


			case AM_PM: ensureDateCalculated();
					if( localDateTime.hourOfDay < 12)
					{
						return AM;
					}
					else
					{
						return PM;
					}




			case HOUR:
				ensureDateCalculated();
				return localDateTime.getHour();

			case HOUR_OF_DAY:
				ensureDateCalculated();
				return localDateTime.hourOfDay;



			case MINUTE:
				ensureDateCalculated();
				return localDateTime.minutes;

			case SECOND:
				ensureDateCalculated();
				return localDateTime.seconds;

			case MILLISECOND:
				ensureDateCalculated();
				return localDateTime.milliseconds;

			case ZONE_OFFSET:
				ensureDateCalculated();

				if( overridenTZ == null )
				{
					return timeZone.timeZoneContainer.getTimeZone().getStandardOffset() * -60000;
				}
				else
				{
					return overridenTZ;
				}
			case DST_OFFSET:
				//TODO, fix
			case FIELD_COUNT:
				//TODO, fix

			default:
				//emulating the jvm
				throw new ArrayIndexOutOfBoundsException(field);
		}

	}

	public int getActualMaximum(int field)
	{
		switch(field)
		{
			case ERA: return 1;
			case YEAR: return 292278993;
			case MONTH: return 11;
			case WEEK_OF_YEAR: return 52;
			case WEEK_OF_MONTH: return 6;
			case DATE: return 31;
			case DAY_OF_YEAR: return 365;
			case DAY_OF_WEEK: return 7;
			case DAY_OF_WEEK_IN_MONTH: return 5;
			case AM_PM: return PM;

			case HOUR: return 11;
			case HOUR_OF_DAY: return 23;

			case MINUTE: return 59;

			case SECOND: return 59;

			case MILLISECOND: return 999;

			case ZONE_OFFSET: return 50400000;

			case DST_OFFSET: return 7200000;

		}

		//Mimicking the jvm
		throw new ArrayIndexOutOfBoundsException(field);
	}

	public int getActualMinimum(int field)
	{
		switch(field)
		{
			case ERA: return 0;
			case YEAR: return 1;
			case MONTH: return 0;
			case WEEK_OF_YEAR: return 1;
			case WEEK_OF_MONTH: return 0;
			case DATE: return 1;
			case DAY_OF_YEAR: return 1;
			case DAY_OF_WEEK: return 1;
			case DAY_OF_WEEK_IN_MONTH: return 1;
			case AM_PM: return AM;

			case HOUR: return 0;
			case HOUR_OF_DAY: return 0;

			case MINUTE: return 0;

			case SECOND: return 0;

			case MILLISECOND: return 0;

			case ZONE_OFFSET: return -46800000;

			case DST_OFFSET: return 0;

		}

		//Mimicking the jvm
		throw new ArrayIndexOutOfBoundsException(field);
	}

	public static Locale[] getAvailableLocales()
	{
		//TODO, fix
		return null;
	}

	public int getFirstDayOfWeek()
	{
		return firstDayOfWeek;
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
		return minimalDaysInFirstWeek;
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
		switch(field)
		{
			case ERA:

				ensureDateCalculated();
				int modAmount = amount % 2;
				if( modAmount < 0)
				{
					modAmount = modAmount * -1;
				}

				if( localDateTime.era == 1)
				{
					localDateTime.era = ~modAmount;
				}
				else
				{
					localDateTime.era = modAmount;
				}

			//TODO case YEAR: return 1;
			//TODO case MONTH: return 0;
			//TODO case WEEK_OF_YEAR: return 1;
			//TODO case WEEK_OF_MONTH: return 0;
			//TODO case DATE: return 1;
			//TODO case DAY_OF_YEAR: return 1;
			//TODO case DAY_OF_WEEK: return 1;
			//TODO case DAY_OF_WEEK_IN_MONTH: return 1;
			case AM_PM: break; //has no effect

			//TODO case HOUR: return 0;
			//TODO case HOUR_OF_DAY: return 0;

			//TODO case MINUTE: return 0;

			//TODO case SECOND: return 0;

			//TODO case MILLISECOND: return 0;

			//TODO case ZONE_OFFSET: return -46800000;

			//TODO case DST_OFFSET: return 0;

			default:
				//Mimicking the jvm
				throw new ArrayIndexOutOfBoundsException(field);

		}


	}

	public void set(int field, int amount)
	{
		switch (field)
		{
			case ERA:
				boundsCheck( field, amount);
				localDateTime.era = amount;
				needsCalculation = true;
				break;

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

			case AM_PM:
				//TODO, implement
				break;

			case HOUR:
				//TODO, shouldn't behave like hour of day but, will fix later

			case HOUR_OF_DAY:
				localDateTime.hourOfDay = amount;
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

			case ZONE_OFFSET:

				overridenTZ = amount;
				needsCalculation = true;
				break;

			case DST_OFFSET:
				//TODO, fix
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
		firstDayOfWeek = value;
	}

	public void setLenient(boolean lenient)
	{
		//TODO, fix
	}

	public void setMinimalDaysInFirstWeek(int value)
	{
		minimalDaysInFirstWeek = value;
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

			//if its set in AD, use AD, else convert to BC
			int year = localDateTime.year - 1900;
			if( localDateTime.era == 0)
			{
				year = (localDateTime.year + 1899) * -1;
			}


			Date date = new Date(
				year,
				localDateTime.month,
				localDateTime.date,
				localDateTime.hourOfDay,
				localDateTime.minutes,
				localDateTime.seconds);

			// Date class does not have milliseconds manipulation.
			date.setTime(date.getTime() + localDateTime.milliseconds);

			// Store the rolled up fields back in the original LocalDateTime object.
			localDateTime.setValuesFromDate(date);

			// Add time zone to the LocalDateTime to construct a real Date (thus with timezone)
			//TODO, take into account overridden tz value
			calculatedDate = Calendar.createDate(localDateTime.era,
				localDateTime.year,
				localDateTime.month,
				localDateTime.date,
				localDateTime.hourOfDay,
				localDateTime.minutes,
				localDateTime.seconds,
				localDateTime.milliseconds,
				timeZone);


			needsCalculation = false;
		}
	}


	private int getRealFirstDay()
	{
		if( firstDayOfWeek < 0 || firstDayOfWeek > 7)
		{
			if( firstDayOfWeek % 7 == 0)
			{
				return 7;
			}

			if( firstDayOfWeek > 0)
			{

				return firstDayOfWeek % 7;
			}
			else
			{

				return (firstDayOfWeek % 7) - 7;
			}
		}
		return firstDayOfWeek;
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
		if( obj instanceof Calendar)
		{
			ensureDateCalculated();
			return calculatedDate.equals(((Calendar)obj).getTime());
		}

		return false;
	}

	@Override
	public int hashCode()
	{
		ensureDateCalculated();
		return calculatedDate.hashCode();
	}

	@SuppressWarnings("deprecation")
	private static Date createDate(int era, int year, int month, int date, int hours, int minutes, int seconds,
			int milliseconds, TimeZone timeZone)
	{
		int aYear = year - 1900;
		if( era == 0)
		{
			aYear = (year + 1899) * -1;
		}

		// This date is initially incorrect since it will be in browser time zone
		// We shall mutate it to respect the given time zone
		Date d = new Date(aYear, month, date, hours, minutes, seconds);
		d.setTime(d.getTime() + milliseconds);



		// Console.log("GWTDate createDate " + d);
		int offsetConversion = d.getTimezoneOffset() - timeZone.timeZoneContainer.getTimeZone().getStandardOffset();
		d.setMinutes(d.getMinutes() - offsetConversion);
		d.setMinutes(d.getMinutes() - timeZone.timeZoneContainer.getTimeZone().getDaylightAdjustment(d));
		return d;
	}


}
