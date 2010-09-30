package java.util;

public class TimeZone {


	 public static final int SHORT = 0;

	 public static final int LONG = 1;

	 private static GwtZoneInfoProvider gwtZoneInfoProvider = new GwtZoneInfoProvider();
	 private com.google.gwt.i18n.client.TimeZone timeZone;

	 public TimeZone()
	 {
		 //TODO, fix this
		 timeZone = com.google.gwt.i18n.client.TimeZone.createTimeZone((new Date()).getTimezoneOffset());
	 }

	 public static String[] getAvailableIDs()
	 {
		 return (String[]) gwtZoneInfoProvider.getAvailableIDs().toArray(new String[gwtZoneInfoProvider.getAvailableIDs().size()]);
	 }

	 public static String[] getAvailableIDs(int rawOffset)
	 {
		 String[] ids = getAvailableIDs();
		 List<String> idsToReturn = new ArrayList<String>();


		 return null;
	 }

	 public static TimeZone getDefault()
	 {
		 //TODO, fix
		 return null;
	 }

	 String getDisplayName()
	 {
		 //TODO, fix
		return null;
	 }

}
