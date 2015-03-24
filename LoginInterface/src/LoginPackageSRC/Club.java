package LoginPackageSRC;

import java.util.*;

public class Club {
	
	private int clubID;
	private String clubName, clubDescription;
	private ArrayList<Event> eventList;
	private ArrayList<MemberAccount> memberList;
	
	public Club(int clubID, String clubName, String clubDescription) {
		// TODO Auto-generated constructor stub
		setClubID(clubID);
		setClubName(clubName);
		setClubDescription(clubDescription);
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
	
	public void addFixture(Event fix){
		eventList.add(fix);
	}
	
	public Event getFixture(int index){
		return eventList.get(index);
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
	
	public int getClubID(){
		return clubID;
	}
	
	public String getClubName(){
		return clubName;
	}
	
	public String getClubDescription(){
		return clubDescription;
	}
	
	public ClubCache giveClubCacheToMembers(){
		ClubCache cc = new ClubCache(this.getClubID(), this.getClubName(), false);
		return cc;
	}
}
