package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.AdminBean;

/**
 * Servlet implementation class HomePage
 */
public class HomePage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();


		HttpSession session=req.getSession(false);
		if(session==null)
		{
			out.println("Invalid Session");
			RequestDispatcher dispatcher1=
					req.getRequestDispatcher("AdminLogin.html");
			dispatcher1.forward(req, resp);

		}
		else{
			
			RequestDispatcher dispatcher=
					req.getRequestDispatcher("AdminPage.html");
			dispatcher.include(req, resp);
			out.println("<h3 style=\"background-color:green;text-align:center;color:white\">");
			AdminBean data=(AdminBean)session.getAttribute("adminData");

			out.println(" Welcome  Admin "+data.getAdminName() );

			out.println("</h3><font style=\"text-align:left;color:rgba(26, 34, 123, 0.85)\">");







		}//end of validation

	}

}
