package LoginPackageSRC;

public class Account {
	
	private String username, password, fName, lName, pNo, accType;
	private String email;
	

	public Account(String username, String password, String fName,
			String lName, String pNo, String accType, String email) {
		super();
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.pNo = pNo;
		this.accType = accType;
		this.email = email;
	}

	public void setAccType(String accType) {
		// TODO Auto-generated method stub
		this.accType = accType;
	}

	public void setUserName(String username) {
		// TODO Auto-generated method stub
		this.username = username;
	}

	public void setPassWord(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setPNo(String pNo){
		this.pNo = pNo;
	}
	public String getUserName(){
		return username;
	}
	
	public String getPassWord(){
		return password;
	}
	
	public String getAccType(){
		return accType;
	}	
	public String getEmail(){
		return email;
	}
	public String getPNo(){
		return pNo;
	}
	public void setFName(String fName){
		this.fName = fName;
	}
	public void setLName(String lName){
		this.lName = lName;
	}
	public String getFName(){
		return fName;
	}
	public String getLName(){
		return lName;
	}
}
