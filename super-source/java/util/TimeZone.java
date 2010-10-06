package java.util;

public class TimeZone {


	 public static final int SHORT = 0;

	 public static final int LONG = 1;

	 private static GwtZoneInfoProvider gwtZoneInfoProvider = new GwtZoneInfoProvider();
	 private TimeZoneContainer timeZoneContainer;

	 private static TimeZone defaultTimeZone;

	 private TimeZone(TimeZoneContainer timeZoneContainer)
	 {
		 this.timeZoneContainer = timeZoneContainer;
	 }

	 public TimeZone()
	 {
		 //TODO, fix this
		 timeZoneContainer = new TimeZoneContainer(null, null, com.google.gwt.i18n.client.TimeZone.createTimeZone((new Date()).getTimezoneOffset()));
	 }

	 public static String[] getAvailableIDs()
	 {
		 return (String[]) gwtZoneInfoProvider.getAvailableIDs().toArray(new String[gwtZoneInfoProvider.getAvailableIDs().size()]);
	 }

	 public static String[] getAvailableIDs(int rawOffset)
	 {
		 int minuteOffset = rawOffset / 3600000;
		 String[] ids = getAvailableIDs();
		 List<String> idsToReturn = new ArrayList<String>();

		 for( String id : ids)
		 {
			 TimeZoneContainer tzc = gwtZoneInfoProvider.getTimeZoneContainer(id);

			 //TODO, test
			 if( tzc.getTimeZone().getStandardOffset()== minuteOffset )
			 {
				 idsToReturn.add(id);
			 }
		 }

		 return (String[]) idsToReturn.toArray(new String[idsToReturn.size()]);

	 }

	 public static TimeZone getDefault()
	 {
		 //TODO, fix
		 return null;
	 }

	 public String getDisplayName()
	 {
		 //if the time zone info isn't null, return the first name in the array
		 if( timeZoneContainer.getTimeZoneInfo() != null )
		 {
			 return timeZoneContainer.getTimeZoneInfo().getNames().get(1);
		 }

		 //otherwise return the id since it's set
		 return timeZoneContainer.getId();
	 }

	 public String getDisplayName(boolean daylight, int style)
	 {

		 //TODO, fix
		 return null;
	 }

	 //TODO, locale issues... no bueno!
	 //public String getDisplayname(boolean daylight, int style, Locale locale)
	 //{
		 //TODO, fix
//		 return null;
//	 }

	 public int getDSTSavings()
	 {

		 //TODO, fix
		 return 0;
	 }

	 public String getID()
	 {
		 return timeZoneContainer.getTheId();
	 }

	 public int getOffset(long date)
	 {
		 return timeZoneContainer.getTimeZone().getOffset(new Date(date));

	 }

	 public static TimeZone getTimeZone(String ID)
	 {
		 return new TimeZone(gwtZoneInfoProvider.getTimeZoneContainer(ID));
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
		 timeZoneContainer.setId(ID);
	 }


}
