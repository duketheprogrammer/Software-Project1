package LoginPackageSRC;

import java.util.*;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

public class Club {
	
	private int clubID;
	private String clubName, clubDescription, clubType;
	private ArrayList<ClubEvent> eventList;
	private ArrayList<MemberAccount> memberList;
	private ArrayList<MemberAccount> committeeList;
	
	public Club(int clubID, String clubName, String clubDescription, String clubType) {
		// TODO Auto-generated constructor stub
		setClubID(clubID);
		setClubName(clubName);
		setClubDescription(clubDescription);
		setClubType(clubType);
		eventList = new ArrayList<ClubEvent>();
		memberList = new ArrayList<MemberAccount>();
		committeeList = new ArrayList<MemberAccount>();
	}
	
	public MemberAccount removeMember(int index){
		return memberList.remove(index);
	}
	
	public void addMember(MemberAccount m){
		memberList.add(m);
	}
	
	public MemberAccount removeCommittee(int index){
		return committeeList.remove(index);
	}
	
	public void addCommittee(MemberAccount m){
		committeeList.add(m);
	}
	
	public boolean getIsCommittee(MemberAccount mA) 
	{
		Iterator<MemberAccount> iter = committeeList.iterator();
		while(iter.hasNext())
		{
				if(iter.next().equals(mA))
				{
					return true;
				}
		}
		return false;
	}
	
	public ArrayList<String> getCommitteeMails ()
	{
		ArrayList<String> mailList = new ArrayList<String>();
		Iterator<MemberAccount> iter = committeeList.iterator();
		while(iter.hasNext())
		{
			mailList.add(iter.next().getEmail());
		}
		return mailList;
	}
	
	public MemberAccount getMember(int index){
		return memberList.get(index);
	}
	
	public void addEvent(ClubEvent fix){
		eventList.add(fix);
	}
	
	public ClubEvent getEvent(int index){
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
