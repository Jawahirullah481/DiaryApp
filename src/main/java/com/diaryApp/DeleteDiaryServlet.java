package com.diaryApp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


public class DeleteDiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int inboxid = Integer.parseInt(request.getParameter("inboxid"));
		DiaryAppUser user = (DiaryAppUser)request.getSession().getAttribute("user");
		int userid = user.getUserId();
		
		DBA.deleteDiary(inboxid, userid);
		user.getInbox().removeIf((i) -> {
			
			return i.getInboxId() == inboxid;
			
		});
		user.setLastEditedDate(LocalDate.now());
		
		response.sendRedirect("inbox");
		
	}

}
