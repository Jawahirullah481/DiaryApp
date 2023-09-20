package com.diaryApp;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse  httpResponse = (HttpServletResponse)response;
		httpResponse.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setHeader("Expires", "0");
		
		if(httpRequest.getSession().getAttribute("logged_in") == null)
		{
			try {
				httpResponse.sendRedirect("index.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		chain.doFilter(request, response);
	}

}
