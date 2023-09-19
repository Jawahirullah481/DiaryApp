package com.diaryApp;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class AddNewDiary extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	{
        // retreive parameters and creating inbox object
				
		String diaryHeading = request.getParameter("heading");
		String diaryContent = request.getParameter("content");
		LocalDate date = LocalDate.now();
		
		DiaryAppInbox inbox = new DiaryAppInbox();
		inbox.setHeading(diaryHeading);
		inbox.setContent(diaryContent);
		inbox.setDateEdited(date);
		
		HttpSession session = request.getSession();
		DiaryAppUser user = (DiaryAppUser)session.getAttribute("user");
		
		// adding inbox object into database and get generated inboxid
		int newdiaryid = DBA.addNewDiary(user.getUserId(), inbox);
		
		// if new diary succesfully add into database, then add this into user inbox
		if(newdiaryid != -1)
		{
		inbox.setInboxId(newdiaryid);
		user.getInbox().add(inbox);
		}
		
		try {
			response.sendRedirect("inbox");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error while creaating the diary");
		}
	}

	
}