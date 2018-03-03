package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.HospitalDAOFactory;
import com.dao.HospitalMgmtDAO;
import com.dto.AdminBean;


public class Login  extends HttpServlet{


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String adminId = req.getParameter("adminId");
		String password = req.getParameter("password");

		out.println("  <head><title>Library</title><link rel=\"icon\" type=\"image/png\" href=\"li.png\"></head>");

		//3. Interact with DB to Authenticate

		HospitalDAOFactory factory=new HospitalDAOFactory();
		HospitalMgmtDAO dao=factory.getInstance();
		AdminBean data= dao.authenticate(adminId, password);

		System.out.println("1111111111");
		if(data!=null)
		{
			HttpSession session =req.getSession(true);
			session.setMaxInactiveInterval(1*60);
			
			session.setAttribute("adminData", data);

				out.println("<BR><BR>");
				
				RequestDispatcher dispatcher1=
						req.getRequestDispatcher("HomePage");
				dispatcher1.forward(req, resp);


			
			
		}else
		{


			out.println("<h3 style=\"background-color:red;text-align:center;color:white;\">In-Valid usnId or password</h3>");
			RequestDispatcher dispatcher=
					req.getRequestDispatcher("login.html");
			dispatcher.include(req, resp);


		}
	}//end of dopost
}//end of class
