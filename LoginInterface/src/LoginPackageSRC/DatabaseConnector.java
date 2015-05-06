package LoginPackageSRC; 


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseConnector { 

	//Define attributes 
	private static DatabaseConnector instance ;
	private Connection con; 
	private Statement st; 
	private ResultSet rs; 

	//Constructor for db connection 
	private DatabaseConnector() 
	{
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/software_project", "root", ""); 
			st = (Statement) con.createStatement();
		} 
		catch(Exception ex) 
		{ 
			System.out.println("Error: "+ ex); 
		} 
	} 

	public static DatabaseConnector getInstance() {
		if (instance == null) {
			synchronized (DatabaseConnector.class) {
				if (instance == null) {
					instance = new DatabaseConnector();
				}
			}
		}
		return instance;
	}

	//Function to get data from database 
	public ResultSet getData(String query) 
	{ 	
		try {
			rs = st.executeQuery(query);
			return rs; 
		} 
		catch(SQLException e)
		{ 
			e.printStackTrace(); 
			return null; 
		}

	} //Function to update and insert data into db 
	public void updateData (String query) 
	{ 
		try { 
			st.executeUpdate(query); 
		} 
		catch (Exception ex) { 
			System.out.println(ex); 
		}
	} 
	
	//Function to close connection from db 
	public void closeDB()
	{ 
		try { 
			con.close(); 
		} 
		catch (SQLException e) 
		{ 
			e.printStackTrace();
		} 
	}

	public ArrayList<MemberAccount> getMemberAccounts() throws SQLException {
		ResultSet rs = getData("SELECT * FROM `member_table`")	;
		ArrayList<MemberAccount> AccountList = new ArrayList<MemberAccount>();
		while(rs.next())
		{
			String username = rs.getString("memberLogin");
			String passWd = rs.getString("memberPW");
			String accType = rs.getString("loginLevel");
			String email = rs.getString("memberEmail");
			String fName = rs.getString("memberName");
			String lName = rs.getString("memberName");
			String pNo = "" + rs.getInt("memberPhone");
			MemberAccount mA = new MemberAccount(username, passWd, accType, email, fName, lName, pNo,false);
			AccountList.add(mA);
		}
		return AccountList;	
	} 
	
	public void insertMember(MemberAccount acc)
	{
		updateData(String.format("INSERT INTO `software_project`.`member_table` " +
				"(`memberLogin`, `memberName`, `memberPW`, `memberEmail`, `memberPhone`, `loginLevel`) " +
				"VALUES ('%s', '%s %s', '%s', '%s', '%s', 'Member');",
				acc.getUserName(),acc.getFName(),acc.getLName(),acc.getPassWord(),
				acc.getEmail(), acc.getPNo()));
	}
}