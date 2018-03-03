package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IncludeAllServlets
 */
public class IncludeAllServlets extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();


		String page=req.getParameter("page");
		RequestDispatcher dispatcher =null;


		if(page.equals("patientDetails"))
		{
			dispatcher=req.getRequestDispatcher("viewAllPatients");
			dispatcher.include(req, resp);


		}
		else if(page.equals("doctorDetails"))
		{
			dispatcher=req.getRequestDispatcher("ViewAllDoctors");
			dispatcher.include(req, resp);


		}


	}

}
