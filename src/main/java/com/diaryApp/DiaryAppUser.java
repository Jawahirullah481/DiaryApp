package com.diaryApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Deque;

public class DiaryAppUser {

	private int userId;
	private String username;
	private String emailId;
	private String password;
	private Deque<DiaryAppInbox> inbox;
	private LocalDate lastEditedDate;
	
	public DiaryAppUser()
	{
		
	}
	
	public DiaryAppUser(int userId, String username, String emailId, String password)
	{
		this.userId = userId;
		this.username = username;
		this.emailId = emailId;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Deque<DiaryAppInbox> getInbox() {
		return inbox;
	}

	public void setInbox(Deque<DiaryAppInbox> inbox) {
		this.inbox = inbox;
		if(!inbox.isEmpty())
		 this.setLastEditedDate(inbox.getLast().getDateEdited());
		else
	     this.setLastEditedDate(LocalDate.now());
	}

	public LocalDate getLastEditedDate() {
		return lastEditedDate;
	}
	
	public String getEditedDateinFormat()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return lastEditedDate.format(formatter);
	}

	public void setLastEditedDate(LocalDate lastEditedDate) {
		this.lastEditedDate = lastEditedDate;
	}
	
	@Override
	public String toString()
	{
		String str = String.format("[userid : %d, username : %s, emailid : %s, password : %s]", this.userId, this.username, this.emailId, this.password);
		
		return str;
	}

	
}
