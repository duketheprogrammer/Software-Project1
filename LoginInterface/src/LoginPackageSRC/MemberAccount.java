package LoginPackageSRC;

import java.util.*;

public class MemberAccount extends Account{

	private String email, fName, lName, pNo;
	private ArrayList<ClubEvent> eventList;
	private ArrayList<Club> registeredClubs;

	public MemberAccount(String username, String passWd, String accType, String email, String fName, String lName, String pNo) {
		// TODO Auto-generated constructor stub
		super(username, passWd, accType);
		setEmail(email);
		setFName(fName);
		setLName(lName);
		setPNo(pNo);
		eventList = new ArrayList<ClubEvent>();
		registeredClubs = new ArrayList<Club>();
	}

	public void addClub(Club cc){
		registeredClubs.add(cc);
	}
	
	public int getNoOfClubs(){
		return registeredClubs.size();
	}
	
	public Club getClub(int index){
		return registeredClubs.get(index);
	}
	
	public Club removeClub(int index){
		return registeredClubs.remove(index);
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
