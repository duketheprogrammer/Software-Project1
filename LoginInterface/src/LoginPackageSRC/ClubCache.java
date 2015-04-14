package LoginPackageSRC;

public class ClubCache {

	private int clubID;
	private String clubName, clubDesc, clubType;
	private boolean isCommittee;
	
	public ClubCache(int clubID, String clubName, String clubDesc, String clubType, boolean isCommittee) {
		// TODO Auto-generated constructor stub
		setClubID(clubID);
		setClubName(clubName);
		setClubDescription(clubDesc);
		setClubType(clubType);
		setIsCommittee(isCommittee);
	}

	private void setClubType(String clubType) {
		// TODO Auto-generated method stub
		this.clubType = clubType;
	}

	private void setClubID(int clubID) {
		// TODO Auto-generated method stub
		this.clubID = clubID;
	}

	private void setClubName(String clubName) {
		// TODO Auto-generated method stub
		this.clubName = clubName;
	}
	
	private void setClubDescription(String clubDesc){
		this.clubDesc = clubDesc;
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
	
	public String getClubDesc(){
		return clubDesc;
	}
	
	public String getClubType(){
		return clubType;
	}
	
	public boolean getIsCommittee(){
		return isCommittee;
	}

}
