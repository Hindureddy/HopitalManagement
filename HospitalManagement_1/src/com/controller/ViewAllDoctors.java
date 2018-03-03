package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.HospitalDAOFactory;
import com.dao.HospitalMgmtDAO;
import com.dto.DoctorBean;
import com.dto.PatientBean;

public class ViewAllDoctors extends HttpServlet
{


	public void doGet(HttpServletRequest req,HttpServletResponse resp)
			throws IOException ,ServletException{
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();

		HttpSession session = req.getSession(false);
		if(session==null)
		{
			out.println("Invalid session ");
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
			dispatcher.include(req, resp);	
		}

		else
		{
		HospitalDAOFactory factory=new HospitalDAOFactory();
		HospitalMgmtDAO dao=factory.getInstance();

		List<DoctorBean> doctorData = dao.viewAllDoctors();

		RequestDispatcher dispatcher=
				req.getRequestDispatcher("AdminPage.html");
		dispatcher.include(req, resp);
		out.println("<font style=\"text-align:left;color:rgba(26, 34, 123, 0.85)\">");
		
		
		out.println("<BR>");
		out.println("<h2 align=\"center\"> View All Doctor Information</h2>");
		out.println("<BR>");


		out.println("<html>								");
		out.println("<body>                             ");
		out.println("	<table height=\"80%\" width=\"1000px\"align=\"center\" border=\"1px\">             " );
		out.println("			<tr height=\"40px\" style=\"background-color:orange;font-size:20px;font-family:cambria;text-align:center; color:white;\">     ");
		out.println("			<td>doctorName </td>      ");
		out.println("			<td>Email</td>      ");
		out.println("			<td>Gender</td>      ");
		out.println("			<td>ConatctNumber</td>     ");
		out.println("			<td>Specilization</td>    ");
		out.println("			<td>Experience</td>    ");



		out.println("		</tr>                       ");
		for(DoctorBean bean:doctorData)
		{
			out.println("		<tr style=\"text-align:center;font-family:cambria;\">                        ");
			out.println("         <td>"+bean.getDoctorName()+"</td>   ");
			out.println("          <td> "+bean.getEmail()+"</td>");
			out.println("			<td>"+bean.getGender()+"</td>    ");
			out.println("			<td>"+bean.getContactNum()+"</td>   ");
			out.println("			<td>"+bean.getSpecilization()+"</td>   ");
			out.println("			<td>"+bean.getExperience()+"</td>   ");

			out.println("		</tr>                       ");



		}
		out.println("	</table>             " );

		out.println(" <BR><BR><BR><BR> <BR>");
		RequestDispatcher footer = req.getRequestDispatcher("footer.html");
		footer.include(req, resp);
		out.println("</html>								");
		out.println("</body>                             ");

		}//end of sesion validation

	}// end of do-Get


}// end of class
