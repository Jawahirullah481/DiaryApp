package com.diaryApp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;


public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		
		DiaryAppUser user = new DiaryAppUser();
		user.setUsername(username);
		user.setEmailId(email);
		user.setPassword(password);
		
		RequestDispatcher requestDispatcher;
		
		if(DBA.addUser(user))
		{
			// make login
			
			requestDispatcher = request.getRequestDispatcher("/login");
			requestDispatcher.forward(request, response);
			
		}
		else {
			// error page
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
