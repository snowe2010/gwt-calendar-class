package java.util;

import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.datepicker.client.CalendarUtil;

public class TimeZone {


	 public static final int SHORT = 0;

	 public static final int LONG = 1;

	 private static GwtZoneInfoProvider gwtZoneInfoProvider = new GwtZoneInfoProvider();

	 private static TimeZone defaultTimeZone;

	 protected TimeZoneContainer timeZoneContainer;


	 private TimeZone(TimeZoneContainer timeZoneContainer)
	 {
		 this.timeZoneContainer = timeZoneContainer;
	 }

	 public TimeZone()
	 {
		 if( defaultTimeZone == null )
		 {
			 defaultTimeZone = getDefaultTimeZone();
		 }

		 timeZoneContainer = defaultTimeZone.timeZoneContainer.copy();

	 }

	 private static TimeZone getDefaultTimeZone()
	 {

		 List<TimeZone> possibleTimeZones = new ArrayList<TimeZone>();
		 int offset = new Date().getTimezoneOffset();
		 //Window.alert("offset is: " + offset);
		 for( String value : getAvailableIDs())
		 {
			 TimeZone tz = TimeZone.getTimeZone(value);
			if( tz.timeZoneContainer.getTimeZone().getOffset(new Date()) == offset)
			{
				boolean isValid = true;
				for(int i = 1; i < 366; i++)
				{
					Date d = new Date();
					CalendarUtil.addDaysToDate(d,i);
					int tzOffset = d.getTimezoneOffset();
					if(tzOffset != tz.timeZoneContainer.getTimeZone().getOffset(d))
					{
						isValid = false;
						break;
					}
				}

				if( isValid)
				{

					//Give priority to NY TZ for now, use googles geolocation later
					if(tz.getID().equals("America/New_York"))
					{
						return tz;
					}
					//else if(tz.getID().equals("America/New_York"))

					possibleTimeZones.add(tz);
					//Window.alert(value);
				}



			}

		 }

		//default case
		if(possibleTimeZones.size() == 0)
		{
			return getTimeZone("Etc/GMT+0");
		}



		return possibleTimeZones.get(0);


	}

	public static String[] getAvailableIDs()
	 {
		 return (String[]) gwtZoneInfoProvider.getAvailableIDs().toArray(new String[gwtZoneInfoProvider.getAvailableIDs().size()]);
	 }

	 public static String[] getAvailableIDs(int rawOffset)
	 {
		 int minuteOffset = rawOffset / 60000 * -1;
		 String[] ids = getAvailableIDs();
		 List<String> idsToReturn = new ArrayList<String>();

		 for( String id : ids)
		 {
			 TimeZoneContainer tzc = gwtZoneInfoProvider.getTimeZoneContainer(id);
			 if( tzc.getTimeZone().getStandardOffset()== minuteOffset )
			 {
				 idsToReturn.add(id);
			 }
		 }

		 return (String[]) idsToReturn.toArray(new String[idsToReturn.size()]);

	 }

	 public static TimeZone getDefault()
	 {
		 if(defaultTimeZone == null)
		 {
			 defaultTimeZone = getDefaultTimeZone();
		 }

		 return defaultTimeZone.copy();
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

		 int place = 0;
		 if(getDSTSavings() > 0 && daylight == true && timeZoneContainer.getTimeZoneInfo().getNames().length() > 2)
		 {
			 place = 2;
		 }

		 if(style == LONG)
		 {
			 place++;
		 }
		 else
		 {
			 if( style != SHORT)
			 {
				 throw new RuntimeException("style value is not supported");
			 }
		 }


		 return timeZoneContainer.getTimeZoneInfo().getNames().get(place);
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
		 return timeZoneContainer.getTimeZone().getOffset(new Date(date)) * -60000;

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
			 if( timeZoneContainer.getTimeZoneInfo() != null || timeZoneContainer.getTimeZoneInfo().getTransitions().length() != 0)
			 {
				 JsArrayInteger transitions = timeZoneContainer.getTimeZoneInfo().getTransitions();
				 JsArrayInteger otherTransitions =  other.timeZoneContainer.getTimeZoneInfo().getTransitions();
				 if( transitions.length() == otherTransitions.length())
				 {
					 for( int i = 0; i < transitions.length(); i++)
					 {
						 if( transitions.get(i) != otherTransitions.get(i))
						 {
							 return false;
						 }
					 }

					 return true;
				 }

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

	 //method is named copy since clone isn't supported yet, hopefully this will change
	 private TimeZone copy()
	 {
		 return new TimeZone(timeZoneContainer.copy());
	 }


}
