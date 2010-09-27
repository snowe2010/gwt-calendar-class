package com.spiral.calendar;

import java.util.Date;

import com.google.gwt.i18n.client.TimeZone;

public class Time implements Comparable<Time>
{
	public int seconds;

	public int minutes;

	public int hours;

	public Time(int totalSeconds)
	{
		setTotalSeconds(totalSeconds);
	}

	public Time(Date date, TimeZone timeZone)
	{
		GWTCalendar calendar = new GWTCalendar(date, timeZone);
		setTotalSeconds(calendar.get(GWTCalendar.SECOND) + calendar.get(GWTCalendar.MINUTE) * 60
				+ calendar.get(GWTCalendar.HOUR) * 3600);
	}

	public Time(int hours, int minutes, int seconds)
	{
		this(hours * 3600 + minutes * 60 + seconds);
	}

	public void setTotalSeconds(int totalSeconds)
	{
		seconds = totalSeconds % 60;
		minutes = totalSeconds % 3600 / 60;
		hours = totalSeconds / 3600;
	}

	public int getTotalSeconds()
	{
		return seconds + minutes * 60 + hours * 3600;
	}

	public int getSeconds()
	{
		return seconds;
	}

	public int getMinutes()
	{
		return minutes;
	}

	public int getHours()
	{
		return hours;
	}

	public void setSeconds(int seconds)
	{
		this.seconds = seconds;
	}

	public void setMinutes(int minutes)
	{
		this.minutes = minutes;
	}

	public void setHours(int hours)
	{
		this.hours = hours;
	}

	public int compareTo(Time o)
	{
		return getTotalSeconds() - o.getTotalSeconds();
	}

}
