package LoginPackageSRC;

public abstract class Account {
	
	private String username, password, accType;
	
	public Account(String username, String password, String accType) {
		// TODO Auto-generated constructor stub
		setUserName(username);
		setPassWord(password);
		setAccType(accType);
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

	public String getUserName(){
		return username;
	}
	
	public String getPassWord(){
		return password;
	}
	
	public String getAccType(){
		return accType;
	}
}
