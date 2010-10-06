package java.util;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.client.TimeZoneInfo;

class TimeZoneContainer {

	private TimeZone timeZone;
	private TimeZoneInfo timeZoneInfo;
	private String id;

	public TimeZoneContainer(String id,  TimeZoneInfo timeZoneInfo, TimeZone timeZone)
	{
		this.id = id;
		this.timeZoneInfo = timeZoneInfo;
		this.timeZone = timeZone;
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



}
