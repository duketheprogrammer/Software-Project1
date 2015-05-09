package LoginPackageSRC;

import java.sql.SQLException;
import java.util.*;

import LoginPackageGUI.LoginScreen;

public class MemberAccount extends Account{

	private ArrayList<ClubEvent> eventList;
//	private ArrayList<Club> registeredClubs;
	private ArrayList<ClubMembership> registeredClubs;
	private CommitteeData committeeData;

	public MemberAccount(String username, String passWd, String accType, String email, String fName, String lName, String pNo, boolean insert) {
		// TODO Auto-generated constructor stub
		super(username, passWd, fName, lName, pNo, accType, email);
		committeeData = null;
		eventList = new ArrayList<ClubEvent>();
		registeredClubs = new ArrayList<ClubMembership>();
		if (insert)
		{
			LoginScreen.DBCon.insertMember(this);
		}
	}

	public void addClub(Club club, boolean insert){
		ClubMembership cm = new ClubMembership(this, club);
		club.addMembership(cm);
		addClubMembership(cm);
		if (insert)
		{
			try {
				LoginScreen.DBCon.insertMemberShip(cm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void removeClub(Club club) {
		Iterator<ClubMembership> iter = registeredClubs.iterator();
		while(iter.hasNext())
		{
			ClubMembership cM = iter.next();
			if(cM.getClub().equals(club))
			{
				cM.remove();
				try {
					LoginScreen.DBCon.removeMemberShip(cM);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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


	public CommitteeData getCommitteeData() {
		return committeeData;
	}

	public void setCommitteeData(CommitteeData committeeData) {
		this.committeeData = committeeData;
	}






}
