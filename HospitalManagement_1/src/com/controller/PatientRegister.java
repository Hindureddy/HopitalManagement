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
public class PatientRegister extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		//get the form data
		String pName = req.getParameter("pName");
		String dob = req.getParameter("doa");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String maritalStatus= req.getParameter("maritalStatus");
		String pNum = req.getParameter("mobileNo");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String appType = req.getParameter("appointmentType");


		//3. Interact with DB to Authenticate
		
        HospitalMgmtDAO mgmt=null;
        HospitalDAOFactory factory=new HospitalDAOFactory();
        mgmt=factory.getInstance();
        
       boolean data=mgmt.patientRegister(pName,
		email, dob, Integer.parseInt(age),gender,
		address, Long.parseLong(pNum), maritalStatus,
		appType);

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
