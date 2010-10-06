package java.util;

public class TimeZone {


	 public static final int SHORT = 0;

	 public static final int LONG = 1;

	 private static GwtZoneInfoProvider gwtZoneInfoProvider = new GwtZoneInfoProvider();
	 private com.google.gwt.i18n.client.TimeZone timeZone;
	 private static TimeZone defaultTimeZone;

	 private TimeZone(com.google.gwt.i18n.client.TimeZone timeZone)
	 {
		 this.timeZone = timeZone;
	 }

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


		 //TODO, finish this
		 return null;
	 }

	 public static TimeZone getDefault()
	 {
		 //TODO, fix
		 return null;
	 }

	 public String getDisplayName()
	 {
		 //TODO, fix
		return null;
	 }

	 public String getDisplayName(boolean daylight, int style)
	 {
		 //TODO, fix
		 return null;
	 }

	 public String getDisplayname(boolean daylight, int style, Locale locale)
	 {
		 //TODO, fix
		 return null;
	 }

	 public int getDSTSavings()
	 {

		 //TODO, fix
		 return 0;
	 }

	 public String getID()
	 {
		 return timeZone.getID();
	 }

	 public int getOffset(long date)
	 {
		 //TODO, fix
		 return 0;
	 }

	 public static TimeZone getTimeZone(String ID)
	 {
		 return new TimeZone(gwtZoneInfoProvider.getZone(ID));
	 }

	 public boolean hasSameRules(TimeZone other)
	 {
		 //TODO, fix
		 return false;
	 }

	 public static void setDefault(TimeZone zone)
	 {
		 defaultTimeZone = zone;
	 }

	 public void setID(String ID)
	 {
		 //TODO, fix
	 }


}
