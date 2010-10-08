package java.util;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.client.TimeZoneInfo;

class TimeZoneContainer {

	private TimeZone timeZone;
	private TimeZoneInfo timeZoneInfo;
	private String id;
	private String raw;

	public TimeZoneContainer(String id,  TimeZoneInfo timeZoneInfo, TimeZone timeZone, String raw)
	{
		this.id = id;
		this.timeZoneInfo = timeZoneInfo;
		this.timeZone = timeZone;
		this.raw = raw;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
	public TimeZoneInfo getTimeZoneInfo() {
		return timeZoneInfo;
	}
	public void setTimeZoneInfo(TimeZoneInfo timeZoneInfo) {
		this.timeZoneInfo = timeZoneInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTheId() {
		if( id != null )
		{
			return id;
		}

		return timeZone.getID();
	}

	public TimeZoneContainer copy() {

		if( raw != null)
		{

			TimeZoneInfo tzi = TimeZoneInfo.buildTimeZoneData(raw);
	        TimeZone timeZone = TimeZone.createTimeZone(tzi);
	        return new TimeZoneContainer( id, tzi, timeZone, raw);
		}


		//if it's some TZ like ETC, just recreate it
		return new TimeZoneContainer(id, null, TimeZone.createTimeZone(timeZone.getStandardOffset()), null);

	}



}
