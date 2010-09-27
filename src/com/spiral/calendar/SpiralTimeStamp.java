package com.spiral.calendar;

import java.util.Date;

import com.google.gwt.i18n.client.TimeZone;

/*
 * This class represents an instance in time, this is a date and a timezone
 */
public class SpiralTimeStamp
{
	private Date date;

	private TimeZone timezone;

	protected SpiralTimeStamp(Date date, TimeZone timezone)
	{
		this.date = date;
		this.timezone = timezone;
	}

	public Date getDate()
	{
		return date;
	}

	public TimeZone getTimeZone()
	{
		return timezone;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		SpiralTimeStamp other = (SpiralTimeStamp) obj;
		if (date == null)
		{
			if (other.date != null)
			{
				return false;
			}
		}
		else if (!date.equals(other.date))
		{
			return false;
		}
		return true;
	}

	

	public static SpiralTimeStamp getTimeStamp(Date date, TimeZone timeZone)
	{
		return new SpiralTimeStamp(date, timeZone);
	}

	public static SpiralTimeStamp getTimeStampFromCalendar(GWTCalendar calendar)
	{
		return new SpiralTimeStamp(calendar.getTime(), calendar.getTimeZone());
	}
}
