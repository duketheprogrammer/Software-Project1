package LoginPackageSRC; 


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;


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
		for(int i = 0 ; i<AccountList.size(); i++)
		{
			MemberAccount mA= AccountList.get(i);
			String id = getMemberID(mA);
			if (isCommittee(id))
			{
				ResultSet rs2 = getData(String.format("SELECT * FROM `committemem_table` WHERE `committemem_table`.`memberID` = " +
						"'%s'",id));
				rs2.next();
				String courseYear = rs2.getString("memberCourseYear");
				String courseName = rs2.getString("memberCourse");
				String dateOfBirth = rs2.getString("memberDOB");
				String address = rs2.getString("memberAddress");
				CommitteeData comDat = new CommitteeData(courseYear, courseName, dateOfBirth, address);
				mA.setCommitteeData(comDat);
			}
		}
		return AccountList;	
	} 

	public ArrayList<Club> getClubs() throws SQLException {
		ResultSet rs = getData("SELECT * FROM `club_table`")	;
		ArrayList<Club> clubList = new ArrayList<Club>();
		while(rs.next())
		{
			int clubID = rs.getInt("clubID");
			String clubName = rs.getString("clubName");
			String clubDescription = rs.getString("clubDescription");
			String clubType = rs.getString("clubType");
			Club club = new Club(clubID, clubName, clubDescription, clubType, false);
			clubList.add(club);
		}
		return clubList;	
	}
	public void insertMember(MemberAccount acc)
	{
		updateData(String.format("INSERT INTO `software_project`.`member_table` " +
				"(`memberLogin`, `memberName`, `memberPW`, `memberEmail`, `memberPhone`, `loginLevel`) " +
				"VALUES ('%s', '%s %s', '%s', '%s', '%s', 'Member');",
				acc.getUserName(),acc.getFName(),acc.getLName(),acc.getPassWord(),
				acc.getEmail(), acc.getPNo()));
	}

	public void insertClub(Club club) {
		updateData(String.format("INSERT INTO `software_project`.`club_table` " +
				"(`clubID`, `clubName`, `clubDescription`, `clubType`) " +
				"VALUES ('%s','%s','%s','%s');",
				club.getClubID(),club.getClubName(),club.getClubDescription(),club.getClubType()));
		
	}

	public void insertClubEvent(ClubEvent event) {
		updateData(String.format("INSERT INTO `software_project`.`event_table` " +
				"(`clubID`, `eventType`, `eventLocation`, `eventDateTime`, `eventInfo`) " +
				"VALUES ('%s','%s','%s','%s','%s');",
				event.getClub().getClubID(),event.getEventType(),event.getLocation(),event.getDate(),event.getInfo()));
		
	}

	public void updateMember(MemberAccount mA) throws SQLException {
		updateData(String.format("UPDATE `software_project`.`member_table` " +
				"SET `memberName` = '%s %s', `memberPW` ='%s', `memberEmail` = '%s' " +
				", `memberPhone` = '%s'" +
				"WHERE `member_table`.`memberLogin` = '%s';",mA.getFName(),mA.getLName(),
				mA.getPassWord(),mA.getEmail(), mA.getPNo(), mA.getUserName()));
		if(mA.getCommitteeData()!= null)
		{
			CommitteeData comDat = mA.getCommitteeData();
			String id = getMemberID(mA);			
			if (isCommittee(id))
			{
				updateData(String.format("UPDATE `software_project`.`committemem_table` " +
						"SET `memberAddress` = '%s', `memberDOB` ='%s', `memberCourse` = '%s' " +
						", `memberCourseYear` = '%s'" +
						"WHERE `committemem_table`.`memberID` = '%s';",
						comDat.getAddress(),comDat.getDateOfBirth(),comDat.getCourseName(),comDat.getCourseYear(),id));
			}
			else
			{		
				updateData(String.format("INSERT INTO `software_project`.`committemem_table` " +
						"(`memberID`, `memberAddress`, `memberDOB`, `memberCourse`, `memberCourseYear`) " +
						"VALUES ('%s','%s','%s','%s','%s');",
						id, comDat.getAddress(),comDat.getDateOfBirth(),comDat.getCourseName(),comDat.getCourseYear()));
				
			}
		}
	}

	private boolean isCommittee(String id) throws SQLException {
		ResultSet rs = getData(String.format("SELECT * FROM `committemem_table` WHERE `committemem_table`.`memberID` = " +
				"'%s'",id));
		if (!rs.next())
			return false;
		return true;
	}
	private String getMemberID(MemberAccount mA) throws SQLException
	{
		ResultSet rs = getData(String.format("SELECT `memberID` FROM `member_table` WHERE `member_table`.`memberLogin` = '%s'",
				mA.getUserName()))	;
		rs.next();
		return rs.getString("memberID");
	}
}