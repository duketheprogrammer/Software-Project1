package LoginPackageSRC; 


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.*;


public class DatabaseConnector { 

	//Define attributes 
	private Connection con; 
	private Statement st; 
	private ResultSet rs; 

	//Constructor for db connection 
	public DatabaseConnector() 
	{
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Melvin", "root", ""); 
			st = (Statement) con.createStatement();
		} 
		catch(Exception ex) 
		{ 
			System.out.println("Error: "+ ex); 
		} 
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
}