package com.diaryApp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		checkLoggedIn(request, response);
		
		String username = request.getParameter("username").trim();
		String emailid = request.getParameter("email").trim();
		String oldPassword = request.getParameter("oldpassword").trim();
		String newPassword = request.getParameter("newpassword").trim();

		HttpSession session = request.getSession();
		DiaryAppUser user = (DiaryAppUser)session.getAttribute("user");
		
		if((user.getPassword()).equals(oldPassword))
		{
			if(newPassword.equals("")) {
				DBA.editUser(user.getUserId(), username, emailid, oldPassword);
			}
			else {
				DBA.editUser(user.getUserId(), username, emailid, newPassword);
				System.out.println("I am here");
				user.setPassword(newPassword);
			}
			
			user.setUsername(username);
			user.setEmailId(emailid);
			response.sendRedirect("inbox");
			
			// Adding new userinformation into the session object
			session.setAttribute("userInitial", user.getUsername().charAt(0)+"");
			session.setAttribute("invalidPassword", false);
			
//			For testing purpose
			session.setAttribute("user", user);
			
		}
		else {
			request.getSession().setAttribute("invalidPassword", true);
			response.sendRedirect("edituserinfopage");
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
