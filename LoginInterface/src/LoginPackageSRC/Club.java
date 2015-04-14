package LoginPackageSRC;

import java.util.*;

public class Club {
	
	private int clubID;
	private String clubName, clubDescription, clubType;
	private ArrayList<Event> eventList;
	private ArrayList<MemberAccount> memberList;
	
	public Club(int clubID, String clubName, String clubDescription, String clubType) {
		// TODO Auto-generated constructor stub
		setClubID(clubID);
		setClubName(clubName);
		setClubDescription(clubDescription);
		setClubType(clubType);
		eventList = new ArrayList<Event>();
		memberList = new ArrayList<MemberAccount>();
	}
	
	public MemberAccount removeMember(int index){
		return memberList.remove(index);
	}
	
	public void addMember(MemberAccount m){
		memberList.add(m);
	}
	
	public MemberAccount getMember(int index){
		return memberList.get(index);
	}
	
	public void addEvent(Event fix){
		eventList.add(fix);
	}
	
	public Event getEvent(int index){
		return eventList.get(index);
	}
	
	public int getNoOfEvents(){
		return eventList.size();
	}
	
	public int getMemberSize(){
		return memberList.size();
	}
	
	public void setClubID(int clubID){
		this.clubID = clubID;
	}
	
	public void setClubName(String clubName){
		this.clubName = clubName;
	}
	
	public void setClubDescription(String clubDescription){
		this.clubDescription = clubDescription;
	}
	
	public void setClubType(String clubType){
		this.clubType = clubType;
	}
	
	public int getClubID(){
		return clubID;
	}
	
	public String getClubName(){
		return clubName;
	}
	
	public String getClubDescription(){
		return clubDescription;
	}
	
	public String getClubType(){
		return clubType;
	}
	
	public ClubCache giveClubCacheToMembers(){
		ClubCache cc = new ClubCache(this.getClubID(), this.getClubName(), this.getClubDescription(), this.getClubType(), false);
		return cc;
	}
}
