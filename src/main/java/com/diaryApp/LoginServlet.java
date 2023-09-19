package com.diaryApp;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usernameOrMail = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		
		int userId = DBA.verifyUser(usernameOrMail, password);
		
		if(userId != -1)
		{

//			adding user inbox information
			DiaryAppUser user = DBA.getUserData(userId);
			
			HttpSession session = request.getSession();
			session.setAttribute("logged_in", true);
			session.setAttribute("user", user);
			session.setAttribute("userInitial", user.getUsername().charAt(0)+"");
			
			response.sendRedirect("inbox");

		}
		else {
			
//			"invalidUser" attribute decides whether the login.jsp show invalid username, password or not
			
			HttpSession session = request.getSession();
			session.setAttribute("invalidUser", true);
			
			response.sendRedirect("loginPage");
		}
	}
	

}
