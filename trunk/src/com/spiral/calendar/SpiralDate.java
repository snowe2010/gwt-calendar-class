package com.spiral.calendar;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents a date without time zone. That is a partial date that represents day, month, and year. It is
 * intended to be used soley for when you need to handle dates without time zone.
 * 
 * It is possible to use this class to build an instant in time (a date and time with time zone) using the
 * java.util.Calendar class or spiral GWTCalendar and spiral Time class, however, doing this is error prone and not
 * recommended.
 * 
 * You should NOT use the datestring for formatting purposes. For instance, never do this!!
 * 
 * label.setText(spiralDate.getDateString());
 * 
 * Instead, do this:
 * 
 * label.setText(SpiralDateTimeFormat.getShortDateFormat().format(spiralDate));
 * 
 * @author Jonathan Janisch
 * 
 */
public class SpiralDate implements Serializable, Comparable<SpiralDate>
{

	private static final long serialVersionUID = 948559253828140475L;

	private String dateString;

	/**
	 * @deprecation this exists only as a serialization artifact
	 */
	@Deprecated
	public SpiralDate()
	{
	}

	/**
	 * Creates a Spiral date using the specified date string in MM/dd/yyyy. For example, July 5, 2009 would be
	 * "07/05/2009"
	 * 
	 * @param dateString
	 *            the date string in MM/dd/yyyy format
	 */
	public SpiralDate(String dateString)
	{
		this.dateString = dateString;
	}

	/**
	 * Creates a Spiral date using the given Date object.
	 * 
	 * @param date
	 *            the Date to create the Spiral date from.
	 */
	@Deprecated
	public SpiralDate(Date date)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(date.getMonth() + 1);
		sb.append("/");
		sb.append(date.getDate());
		sb.append("/");
		sb.append(date.getYear() + 1900);
		this.dateString = sb.toString();
	}

	/**
	 * Returns true if this date is before another spiral date.
	 * 
	 * @param date
	 * @return
	 */
	public boolean before(SpiralDate date)
	{
		return this.createDateInSystemTimeZoneInternal().before(date.createDateInSystemTimeZoneInternal());
	}

	/**
	 * Returns true if this date is after another spiral date.
	 * 
	 * @param date
	 * @return
	 */
	public boolean after(SpiralDate date)
	{
		return this.createDateInSystemTimeZoneInternal().after(date.createDateInSystemTimeZoneInternal());
	}

	public void setDateString(String dateString)
	{
		this.dateString = dateString;
	}

	/**
	 * Returns the year of this SpiralDate minus 1900.
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int getYear()
	{
		// This is legal because it's "in-place". Using Date.getXXX() is much more reliable than simply parsing the year
		// out of the date string. For example, "1/31/84" may be legal and it should represent 1984 not 84.
		return createDateInSystemTimeZoneInternal().getYear();
	}

	/**
	 * Returns the month of this SpiralDate where 0 is January.
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int getMonth()
	{
		// This is legal because it's "in-place". Using Date.getXXX() is much more reliable than simply parsing the
		// month out of the date string. For example, "1/31/84" may be legal and it should represent 1984 not 84.
		return createDateInSystemTimeZoneInternal().getMonth();
	}

	/**
	 * Returns the date in the month.
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int getDate()
	{
		// This is legal because it's "in-place". Using Date.getXXX() is much more reliable than simply parsing the date
		// out of the date string. For example, "1/31/84" may be legal and it should represent 1984 not 84.
		return createDateInSystemTimeZoneInternal().getDate();
	}

	/**
	 * Returns the date string in MM/dd/yyyy format. Do not use this for formatting purposes!
	 * 
	 * @return
	 */
	public String getDateString()
	{
		return dateString;
	}

	@Deprecated
	public Date createDateInSystemTimeZone()
	{
		return new Date(Date.parse(dateString));
	}

	/**
	 * This is an internal method. It used to be public but was abused because it's use wasn't fully understood. You
	 * should never compare a SpiralDate to a java.util.Date directly. You CAN use the SpiralDate getMonth, getDate,
	 * getYear with a GWTCalendar to construct an instance in time to compare to a java.util.Date. See references of
	 * these methods for example usage.
	 * 
	 * This method returns the date in JVM (or browser) time zone. The hours, minutes, seconds are undefined. It should
	 * be solely used for persisting in database.
	 * 
	 * @return
	 */
	private Date createDateInSystemTimeZoneInternal()
	{
		return new Date(Date.parse(dateString));
	}

	@Override
	public String toString()
	{
		return dateString;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateString == null) ? 0 : dateString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		SpiralDate other = (SpiralDate) obj;
		if (dateString == null)
		{
			if (other.dateString != null)
			{
				return false;
			}
		}
		else if (!dateString.equals(other.dateString))
		{
			return false;
		}
		return true;
	}

	public int compareTo(SpiralDate date)
	{
		return createDateInSystemTimeZoneInternal().compareTo(date.createDateInSystemTimeZoneInternal());
	}
}
