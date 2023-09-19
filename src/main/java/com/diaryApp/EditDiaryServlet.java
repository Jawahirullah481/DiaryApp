package com.diaryApp;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Deque;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EditDiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		checkLoggedIn(request, response);
		
		String heading = request.getParameter("heading");
		String content =  request.getParameter("content");
		int inboxid = Integer.parseInt(request.getParameter("inboxid"));
		HttpSession session = request.getSession();
		DiaryAppUser user = (DiaryAppUser)session.getAttribute("user");
		int userid = user.getUserId();
		LocalDate date = LocalDate.now();
		Date sqlDate = Date.valueOf(date);
		
		int newInboxId = DBA.editDiary(inboxid, heading, content, userid, sqlDate);
		user.setLastEditedDate(date);
		
		// Removing edited diary from queue
		Deque<DiaryAppInbox> inbox = user.getInbox();
		inbox.removeIf((i) -> (i.getInboxId() == inboxid));
		
		// Adding new diary into the queue
		DiaryAppInbox diary = new DiaryAppInbox(newInboxId, heading, content, date);
		inbox.add(diary);
				
		try {
			
			response.sendRedirect("inbox");
			 
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Error while forwarding to inbox from EditDiaryServlet");
		}
		
	}


	private void checkLoggedIn(HttpServletRequest request, HttpServletResponse response) {
		
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		if(request.getSession().getAttribute("logged_in") == null)
		{
			try {
				response.sendRedirect("index.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}


}
