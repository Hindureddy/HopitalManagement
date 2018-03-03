package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession  session=request.getSession(false);
		
		if(session!=null)
		{
			session.invalidate();
			
		}
		out.println("<h3 style=\"color:white; background-color:gray\">You have Successfully Loged Out the application hope see u again..!!!</h3>");
		RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");
		dispatcher.include(request, response);
		
	}

}
