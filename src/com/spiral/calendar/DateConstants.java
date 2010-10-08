package com.spiral.calendar;

public interface DateConstants
{
	int SECONDS_PER_HOUR = 3600;

	int HOURS_IN_DAY = 24;

	int MILLISECONDS_FOR_A_DAY = HOURS_IN_DAY * SECONDS_PER_HOUR * 1000;

	int WEEK = 7;

	// taken from the Calendar class, this is to ensure consistency
	public final static int SUNDAY = 1;

	public final static int MONDAY = 2;

	public final static int TUESDAY = 3;

	public final static int WEDNESDAY = 4;

	public final static int THURSDAY = 5;

	public final static int FRIDAY = 6;

	public final static int SATURDAY = 7;

	// months
	public static final int JANUARY = 0;

	public static final int FEBRUARY = 1;

	// time periods

	public static final int ERA = 0;

	public static final int YEAR = 1;

	public static final int MONTH = 2;

	public static final int WEEK_OF_YEAR = 3;

	public static final int WEEK_OF_MONTH = 4;

	public static final int DATE = 5;

	public static final int DAY_OF_MONTH = 5;

	public static final int DAY_OF_YEAR = 6;

	public static final int DAY_OF_WEEK = 7;

	public static final int DAY_OF_WEEK_IN_MONTH = 8;

	public static final int AM_PM = 9;

	public static final int HOUR = 10;

	public static final int MINUTE = 12;

	public static final int SECOND = 13;

	public static final int MILLISECOND = 14;

	public static final int ZONE_OFFSET = 15;

	public static final int DST_OFFSET = 16;

	public static final int FIELD_COUNT = 17;

}