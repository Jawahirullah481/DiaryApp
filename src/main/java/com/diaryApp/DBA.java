package com.diaryApp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;


public class DBA {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/diaryapp";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "password";
	
//	PreparedStatement Queries
	
	private static final String querySignupUser = "insert into diaryappuser (Username, EmailId, Password) values(?, ?, ?)";
	private static final String queryLoginUser = "select UserId from diaryappuser where (Username like binary ? or EmailId like binary ?) and Password like binary ?";
	private static final String queryGetUser = "select user.userid, user.username, user.emailid, user.password from diaryappuser as user where user.userId = ?";
	private static final String queryGetInbox = "select inbox.id, inbox.heading, inbox.date, inbox.content from diaryappinbox as inbox inner join diaryappuser as user on inbox.userid = user.userid where inbox.userid = ?";
	private static final String queryAddNewDiary = "insert into diaryappinbox (heading, date, content, userid) values(?, ?, ?, ?)";
	private static final String queryGetLatestDiary = "select id from diaryappinbox where userid = ? order by id desc limit 1";
	private static final String queryDeletDiary = "delete from diaryappinbox where id = ?";
	private static final String queryEditUserInfo = "update diaryappuser set username = ?, emailId = ?, password = ? where userid = ?";
	
	
	public static Connection getConnection(){
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			
		}
		catch(ClassNotFoundException | SQLException exception)
		{
			System.out.println("Unable to establish connection");
			exception.printStackTrace();
		}
		
		return connection;
		
	}
	
	
	public static boolean addUser(DiaryAppUser user) 
	{
		try {
			Connection con = DBA.getConnection();
			
			PreparedStatement pStatement = con.prepareStatement(DBA.querySignupUser);
			pStatement.setString(1, user.getUsername());
			pStatement.setString(2,  user.getEmailId());
			pStatement.setString(3, user.getPassword());
			pStatement.executeUpdate();
;			
			con.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}

	
	public static int verifyUser(String username, String password) {
				
		try {
			Connection connection = DBA.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DBA.queryLoginUser);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
			{
//				If username and password available in the database, then it returns the userId
				return rs.getInt(1);
			}
			
			connection.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return -1;
	}

	
	public static DiaryAppUser getUserData(int userId) {
				
		try {
			
			Connection connection = DBA.getConnection();
			PreparedStatement pst = connection.prepareStatement(queryGetUser);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			
					
			if(rs.next())
			{
				
				DiaryAppUser user = new DiaryAppUser();
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setEmailId(rs.getString(3));
				user.setPassword(rs.getString(4));
				
				Deque<DiaryAppInbox> inbox = DBA.getInboxData(userId);
				user.setInbox(inbox);
				
				connection.close();
				
				return user;
			}
			
		}catch(SQLException exception) {
			exception.printStackTrace();
			System.out.println("Exception while getting inbox information");
		}
		
		return null;
	}

	private static Deque<DiaryAppInbox> getInboxData(int userId) {
		
		Deque<DiaryAppInbox> inbox = new ArrayDeque<DiaryAppInbox>();


		try {
			Connection connection = DBA.getConnection();
			PreparedStatement pst = connection.prepareStatement(queryGetInbox);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			
			DiaryAppInbox note;
			while(rs.next())
			{
				note = new DiaryAppInbox();
				note.setInboxId(rs.getInt(1));
				note.setHeading(rs.getString(2));
				note.setDateEdited(rs.getDate(3));
				note.setContent(rs.getString(4));
				
				inbox.add(note);
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Exception in getting inbox data");
		}
		
		return inbox;
	}


	public static int addNewDiary(int userId, DiaryAppInbox inbox) {
				
		try {
			
			// adding new diary to database
			
			Connection connection = DBA.getConnection();
			PreparedStatement pst = connection.prepareStatement(queryAddNewDiary);
			pst.setString(1, inbox.getHeading());
			pst.setDate(2, Date.valueOf(inbox.getDateEdited()));
			pst.setString(3, inbox.getContent());
			pst.setInt(4, userId);
			
			pst.executeUpdate();
			
			
			// Getting inboxid of newly created diary from database
			PreparedStatement pst2 = connection.prepareStatement(queryGetLatestDiary);
			pst2.setInt(1, userId);
			ResultSet rs = pst2.executeQuery();
			if(rs.next())
			{
				int newDiaryId = rs.getInt(1);
				return newDiaryId;
			}
					
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("error while creating new diary");
		}
		
		return -1;
		
	}


	public static int editDiary(int inboxid, String heading, String content, int userid, Date date) {
				
		try {
			
			Connection connection = DBA.getConnection();
			CallableStatement statement = connection.prepareCall("{call editInbox(?, ?, ?, ?, ?)}");
			statement.setString(1, heading);
			statement.setString(2, content);
			statement.setDate(3, date);
			statement.registerOutParameter(4, Types.INTEGER);
			statement.setInt(4, inboxid);
			statement.setInt(5, userid);
			
			statement.execute();
						
			// Returning new generated inboxid
			return statement.getInt(4);
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Error while calling editDiary procedure");
		}
		
		return -1;
		
	}


	public static void deleteDiary(int inboxid, int userid) {
		
		try {
			
			Connection connection = DBA.getConnection();
			PreparedStatement pst = connection.prepareStatement(queryDeletDiary);
			pst.setInt(1, inboxid);
			pst.executeUpdate();
			
		}catch(SQLException e)
		{
			System.out.println("Error while deleting diary");
			e.printStackTrace();
		}
		
	}


	public static void editUser(int userId, String username, String emailid, String password) {
		
		try {
			
			Connection connection = DBA.getConnection();
			PreparedStatement pst = connection.prepareStatement(queryEditUserInfo);
			pst.setString(1, username);
			pst.setString(2, emailid);
			pst.setString(3, password);
			pst.setInt(4, userId);
			
			pst.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error while editing user information");
		}
		
	}


	public static void deleteUser(int userId) {
		
		try {
			Connection connection = DBA.getConnection();
			CallableStatement cls = connection.prepareCall("{call deleteUser(?)}");
			cls.setInt(1, userId);
			cls.execute();
		}catch (SQLException e) {
			System.out.println("Error while deleting user");
			e.printStackTrace();
		}
		
	}

}
