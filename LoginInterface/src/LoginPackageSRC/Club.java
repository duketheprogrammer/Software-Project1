package LoginPackageSRC;

import java.util.*;

public class Club {
	
	private int clubID;
	private String clubName, clubDescription, clubType;
	private ArrayList<ClubEvent> eventList;
	private ArrayList<ClubMembership> registeredMembers;
	
	public Club(int clubID, String clubName, String clubDescription, String clubType) {
		// TODO Auto-generated constructor stub
		setClubID(clubID);
		setClubName(clubName);
		setClubDescription(clubDescription);
		setClubType(clubType);
		eventList = new ArrayList<ClubEvent>();
		registeredMembers = new ArrayList<ClubMembership>();
	}

	public void removeMember(ClubMembership clubMembership) {
		registeredMembers.remove(clubMembership);
	}
	public void removeMember(int index){
		registeredMembers.get(index).remove();
	}
	
	public void addMember(MemberAccount m){
		ClubMembership cm = new ClubMembership(m, this);
		m.addClubMembership(cm);
		addMembership(cm);
	}
	public void addMembership(ClubMembership cm) {
		registeredMembers.add(cm);
	}	
//	public MemberAccount removeCommittee(int index){
//		return committeeList.remove(index);
//	}
	
	public void addCommittee(MemberAccount mA){
		Iterator<ClubMembership> iter = registeredMembers.iterator();
		while(iter.hasNext())
		{
			ClubMembership cM = iter.next();
			if(cM.getMemberAccount().equals(mA))
			{
				cM.setCommittee(true);
			}
		}
	}
	
	public boolean getIsCommittee(MemberAccount mA) 
	{
		Iterator<ClubMembership> iter = registeredMembers.iterator();
		while(iter.hasNext())
		{
			ClubMembership cM = iter.next();
			if(cM.getMemberAccount().equals(mA))
			{
				if(cM.isCommittee())
				{
					return true;
				}
				return false;
			}
		}
		return false;
	}
	public boolean getIsMember(MemberAccount mA) 
	{
		Iterator<ClubMembership> iter = registeredMembers.iterator();
		while(iter.hasNext())
		{
			ClubMembership cM = iter.next();
			if(cM.getMemberAccount().equals(mA))
			{
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<MemberAccount> getCommitteeMails ()
	{
		ArrayList<MemberAccount> mailList = new ArrayList<MemberAccount>();
		Iterator<ClubMembership> iter = registeredMembers.iterator();
		while(iter.hasNext())
		{
			ClubMembership cM = iter.next();
			if(cM.isCommittee())
			{
				mailList.add(cM.getMemberAccount());
			}

		}
		return mailList;
	}
	
	public MemberAccount getMember(int index){
		return registeredMembers.get(index).getMemberAccount();
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
		return registeredMembers.size();
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
