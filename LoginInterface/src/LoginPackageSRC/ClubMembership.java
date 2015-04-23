package LoginPackageSRC;

public class ClubMembership {
	private MemberAccount mA;
	private Club club;
	private boolean isCommittee;
	public MemberAccount getMemberAccount() {
		return mA;
	}
	public void setmA(MemberAccount mA) {
		this.mA = mA;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	public boolean isCommittee() {
		return isCommittee;
	}
	public void setCommittee(boolean isCommittee) {
		this.isCommittee = isCommittee;
	}
	public ClubMembership(MemberAccount mA, Club club) {
		super();
		this.mA = mA;
		this.club = club;
	}

	public void remove()
	{
		club.removeMember(this);
		mA.removeMembership(this);
	}

}
