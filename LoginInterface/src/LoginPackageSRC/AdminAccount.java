package LoginPackageSRC;


public class AdminAccount extends Account {
	
	private String email, fName, lName, pNo;
	
	public AdminAccount(String username, String passWd, String accType, String email, String fName, String lName, String pNo) {
		// TODO Auto-generated constructor stub
		super(username, passWd, accType);
		setEmail(email);
		setFName(fName);
		setLName(lName);
		setPNo(pNo);
	
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
