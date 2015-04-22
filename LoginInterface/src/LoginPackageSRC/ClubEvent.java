package LoginPackageSRC;

public class ClubEvent {
	
	private String date, eventType, location, info;
	
	public ClubEvent(String eventType, String location, String date, String info){
		setEventType(eventType);
		setLocation(location);
		setDate(date);
		setInfo(info);
	}

	public void setEventType(String eventType){
		this.eventType = eventType;
	}
	
	public void setLocation(String location){
		this.location = location;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setInfo(String info){
		this.info = info;
	}
	
	public String getEventType(){
		return eventType;
	}
	
	public String getLocation(){
		return location;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getInfo(){
		return info;
	}
}
