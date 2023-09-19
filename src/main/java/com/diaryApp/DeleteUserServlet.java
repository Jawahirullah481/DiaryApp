package com.diaryApp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		checkLoggedIn(request, response);
		
		DiaryAppUser user = (DiaryAppUser)request.getSession().getAttribute("user");
		DBA.deleteUser(user.getUserId());
		RequestDispatcher rsDispatcher = request.getRequestDispatcher("/logout");
		rsDispatcher.forward(request, response);
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
