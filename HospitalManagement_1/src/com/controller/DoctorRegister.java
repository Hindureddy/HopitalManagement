package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.HospitalDAOFactory;
import com.dao.HospitalMgmtDAO;


/**
 * Servlet implementation class Register
 */
public class DoctorRegister extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		//get the form data
		String dName = req.getParameter("dName");
		String gender = req.getParameter("gender");
		long pNum = Long.parseLong(req.getParameter("contactNum"));
		String email = req.getParameter("email");
		String specilization = req.getParameter("appointmentType");
		String experience = req.getParameter("experience");


		//3. Interact with DB to Authenticate

		HospitalMgmtDAO dao=null;
		HospitalDAOFactory factory=new HospitalDAOFactory();
		dao=factory.getInstance();

		System.out.println(dName+""+""+email+""+""+gender+""+pNum+""+specilization+""+experience);
		boolean data=dao.doctorRegister(dName, email, gender,pNum, specilization, experience);

		if(data)

		{
			RequestDispatcher dispatcher=
					req.getRequestDispatcher("index.html");
			dispatcher.include(req, resp);
			out.println("<h3 style=\"background-color:green;text-align:center;color:white\">You have Registered Successfully..... </h3>");
			out.println("<font style=\"text-align:left;color:rgba(26, 34, 123, 0.85)\">");

		}else{
			
			out.println("data is not inserted");

			RequestDispatcher dispatcher=
					req.getRequestDispatcher("patientRegister.html");
			dispatcher.include(req, resp);

		}

	}

}
