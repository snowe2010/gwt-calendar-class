package java.util;

public class TimeZone {


	 public static final int SHORT = 0;

	 public static final int LONG = 1;

	 private com.google.gwt.i18n.client.TimeZone timeZone;

	 public TimeZone()
	 {
		 //TODO, fix this
		 timeZone = com.google.gwt.i18n.client.TimeZone.createTimeZone((new Date()).getTimezoneOffset());
	 }

	 public static String[] getAvailableIDs()
	 {
		 //TODO, fix
		 return null;
	 }

	 public static String[] getAvailableIDs(int rawOffset)
	 {
		 //TODO, fix
		 return null;
	 }

	 public static TimeZone getDefault()
	 {
		 //TODO, fix
		 return null;
	 }

	 String getDisplayName()
	 {
		return null;
	 }

}
