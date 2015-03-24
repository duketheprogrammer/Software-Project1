package LoginPackageSRC;

public class ClubCache {

	private int clubID;
	private String clubName;
	private boolean isCommittee;
	
	public ClubCache(int clubID, String clubName, boolean isCommittee) {
		// TODO Auto-generated constructor stub
		setClubID(clubID);
		setClubName(clubName);
		setIsCommittee(isCommittee);
	}

	private void setClubID(int clubID) {
		// TODO Auto-generated method stub
		this.clubID = clubID;
	}

	private void setClubName(String clubName) {
		// TODO Auto-generated method stub
		this.clubName = clubName;
	}
	
	private void setIsCommittee(boolean isCommittee){
		this.isCommittee = isCommittee;
	}
	
	public int getClubID(){
		return clubID;
	}
	
	public String getClubName(){
		return clubName;
	}
	
	public boolean getIsCommittee(){
		return isCommittee;
	}

}
