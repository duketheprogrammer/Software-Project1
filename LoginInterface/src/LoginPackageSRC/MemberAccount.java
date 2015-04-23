package LoginPackageSRC;

import java.util.*;

public class MemberAccount extends Account{

	private String email, fName, lName, pNo;
	private ArrayList<ClubEvent> eventList;
//	private ArrayList<Club> registeredClubs;
	private ArrayList<ClubMembership> registeredClubs;

	public MemberAccount(String username, String passWd, String accType, String email, String fName, String lName, String pNo) {
		// TODO Auto-generated constructor stub
		super(username, passWd, accType);
		setEmail(email);
		setFName(fName);
		setLName(lName);
		setPNo(pNo);
		eventList = new ArrayList<ClubEvent>();
		registeredClubs = new ArrayList<ClubMembership>();
	}

	public void addClub(Club club){
		ClubMembership cm = new ClubMembership(this, club);
		club.addMembership(cm);
		addClubMembership(cm);
	}
	public void removeClub(Club club) {
		Iterator<ClubMembership> iter = registeredClubs.iterator();
		while(iter.hasNext())
		{
			ClubMembership cM = iter.next();
			if(cM.getClub().equals(club))
			{
				cM.remove();
				return;
			}
		}
	}
	public void addClubMembership(ClubMembership cm) {
		registeredClubs.add(cm);
	}	
	public int getNoOfClubs(){
		return registeredClubs.size();
	}
	
	public Club getClub(int index){
		return registeredClubs.get(index).getClub();
	}
	
	public void removeMembership(ClubMembership clubMembership) {
		registeredClubs.remove(clubMembership);
	}

	public void removeClub(int index){
		registeredClubs.get(index).remove();
	}
	
	public void addEvent(ClubEvent fix){
		eventList.add(fix);
	}

	public ClubEvent removeEvent(int index){
		return eventList.remove(index);
	}

	public ClubEvent getEvent(int i){
		return eventList.get(i);
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setFName(String fName){
		this.fName = fName;
	}

	public void setLName(String lName){
		this.lName = lName;
	}

	public void setPNo(String pNo){
		this.pNo = pNo;
	}

	@Override
	public String getUserName(){
		return super.getUserName();
	}

	@Override
	public String getPassWord(){
		return super.getPassWord();
	}

	@Override
	public String getAccType(){
		return super.getAccType();
	}

	public String getEmail(){
		return email;
	}

	public String getFName(){
		return fName;
	}

	public String getLName(){
		return lName;
	}

	public String getPNo(){
		return pNo;
	}






}
