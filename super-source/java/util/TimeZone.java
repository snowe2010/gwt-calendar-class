package java.util;

public class TimeZone {


	 public static final int SHORT = 0;

	 public static final int LONG = 1;

	 private static GwtZoneInfoProvider gwtZoneInfoProvider = new GwtZoneInfoProvider();
	 protected TimeZoneContainer timeZoneContainer;

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

	 public String getDisplayname(boolean daylight, int style, Locale locale)
	{
		 //TODO, fix
		 return null;
	 }

	 //returns the time in milliseconds if it's a DST time
	 public int getDSTSavings()
	 {
		 if(timeZoneContainer.getTimeZoneInfo() == null || timeZoneContainer.getTimeZoneInfo().getTransitions().length() == 0)
		 {
			 return 0;
		 }

		 int transitionInMinutes = timeZoneContainer.getTimeZoneInfo().getTransitions().get(1);

		 if( transitionInMinutes == 0)
		 {
			 transitionInMinutes = timeZoneContainer.getTimeZoneInfo().getTransitions().get(3);
		 }

		 if( transitionInMinutes < 0 )
		 {
			 transitionInMinutes = Math.abs(transitionInMinutes);
		 }

		 return transitionInMinutes * 60000;

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
		 //if the standard offsets match
		 if( timeZoneContainer.getTimeZone().getStandardOffset() == other.timeZoneContainer.getTimeZone().getStandardOffset())
		 {
			 //if they have the same transition size
			 if( timeZoneContainer.getTimeZoneInfo() != null || timeZoneContainer.getTimeZoneInfo().getTransitions().length() == 0)
			 {

				//TODO, test if the transitions match, if they do, return true
			 }
			 else
			 {
				 //if the timezone has no transitions and the other timezone doesn't have transitions as well, set it to true
				 if( other.timeZoneContainer.getTimeZoneInfo() == null || other.timeZoneContainer.getTimeZoneInfo().getTransitions().length() == 0 )
				 {
					 return true;
				 }
			 }

		 }

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
