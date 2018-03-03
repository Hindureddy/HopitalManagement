package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dto.AdminBean;
import com.dto.ConnectionPoolManager;
import com.dto.DoctorBean;
import com.dto.PatientBean;

public class HospitalMgmtJDBCImpl implements HospitalMgmtDAO {

	public AdminBean authenticate(String adminId, String password)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ConnectionPoolManager pool = null;
		try
		{

			pool = ConnectionPoolManager.getInstance();
			con = pool.getConnectionFromPool();

			String query = " select * from  adminDetails   "+
					" where adminId = ?  "+
					" and password = ?";

			System.out.println("Query : "+query);


			pstmt =con.prepareStatement(query);
			pstmt.setString(1, adminId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			System.out.println("ooo");

			//4. Process the Results returned by SQL Queries
			if(rs.next())
			{

				AdminBean bean=new AdminBean();


				//set the data
				bean.setAdminId(rs.getString("adminId"));
				bean.setAdminName(rs.getString("adminName")); 
				bean.setAdminEmail(rs.getString("adminEmail"));
				bean.setPassword(rs.getString("password"));

				System.out.println("bean set");

				return bean;	
			}


			else{
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			//5. Close ALL JDBC Objects
			try 
			{
				if(rs!=null){
					con.close();
				}
				pool.returnConnectionToPool(con);

				if(pstmt!=null){
					pstmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//End of outer try-catch
	}//End of authenticate

	public boolean patientRegister(String patientName,
			String patientEmail, String doa, int age, String gender,
			String patientAddress, long patientContact, String maritalStatus,
			String appointmentType) {

		Connection con=null;
		ConnectionPoolManager pool = null; 
		PreparedStatement pstmt=null;

		try{



			pool = ConnectionPoolManager.getInstance();
			con = pool.getConnectionFromPool();

			//3. Issue SQL Queries via Connection
			String query = " insert into patientDetails values(?,?,?,?,?,?,?,?,?)";

			System.out.println("Query : "+query);

			pstmt = con.prepareCall(query);

			pstmt.setString(1, patientName);
			pstmt.setString(2, doa);
			pstmt.setInt(3,age);
			pstmt.setString(4, gender);
			pstmt.setString(5, patientEmail);
			pstmt.setString(6, maritalStatus);
			pstmt.setFloat(7, patientContact);
			pstmt.setString(8, patientAddress);
			pstmt.setString(9, appointmentType);


			int count = pstmt.executeUpdate();
			System.out.println("Rows affected :"+count);

			if(count>0)
			{
				return true;
			}

			//4. Process 																																																																																																																																																																																																																																																																																																																																																																																																	the Results returned by SQL Queries

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally{
			//5. Close ALL JDBC Objects
			try 
			{
				if(con!=null){
					con.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}//outer try-catch block
		return false;	

	}

	public boolean doctorRegister(String doctorname, String doctorEmail,
			 String gender, long doctorContact,
			String specilization, String experience)
	{
		Connection con=null;
		ConnectionPoolManager pool = null; 
		PreparedStatement pstmt=null;

		try{
			pool = ConnectionPoolManager.getInstance();
			con = pool.getConnectionFromPool();

			//3. Issue SQL Queries via Connection
			String query = " insert into doctorDetails values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, doctorname);
			pstmt.setString(2, doctorEmail);
			pstmt.setString(3, gender);
			pstmt.setString(4, specilization);
			pstmt.setDouble(5, doctorContact);
			pstmt.setString(6, experience);


			int count = pstmt.executeUpdate();
			System.out.println("Rows affected :"+count);

			if(count>0)
			{
				return true;
			}else {
				return false;

			}

			//4. Process 																																																																																																																																																																																																																																																																																																																																																																																																	the Results returned by SQL Queries

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally{
			//5. Close ALL JDBC Objects
			try 
			{


				if(con!=null){
					con.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}//outer try-catch block
	}

	public List<PatientBean> viewAllPatients() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ConnectionPoolManager pool = null;
		try
		{

			pool = ConnectionPoolManager.getInstance();
			con = pool.getConnectionFromPool();





			String query =  " SELECT * FROM patientDetails ";

			System.out.println("Query : "+query);


			stmt=con.createStatement();
			rs=stmt.executeQuery(query);

			List<PatientBean> dataList = new ArrayList<PatientBean>();
			//4. Process the Results returned by SQL Queries
			while(rs.next())
			{


				PatientBean  bean=new PatientBean();

				bean.setPatientName(rs.getString("pName"));
				bean.setAge(rs.getInt("age")); 
				bean.setDoa(rs.getString("doa"));
				bean.setEmail(rs.getString("pemail"));
				bean.setMaritalStatus(rs.getString("materialstatus"));
				bean.setPatientContact(rs.getLong("pcontactnum"));
				bean.setPatientGender(rs.getString("gender"));
				bean.setPatientAddress(rs.getString("address"));
				bean.setAppointmentType(rs.getString("appointmenttype"));

				dataList.add(bean);
			}
			return dataList;



		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			//5. Close ALL JDBC Objects
			try 
			{
				if(rs!=null){
					con.close();
				}
				pool.returnConnectionToPool(con);

				if(stmt!=null){
					stmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//End of outer try-catch
	}//End of 

	public List<DoctorBean> viewAllDoctors() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ConnectionPoolManager pool = null;
		try
		{

			pool = ConnectionPoolManager.getInstance();
			con = pool.getConnectionFromPool();





			String query =  " SELECT * FROM doctorDetails ";

			System.out.println("Query : "+query);


			stmt=con.createStatement();
			rs=stmt.executeQuery(query);

			List<DoctorBean> dataList = new ArrayList<DoctorBean>();
			//4. Process the Results returned by SQL Queries
			while(rs.next())
			{


				DoctorBean  bean=new DoctorBean();

				bean.setDoctorName(rs.getString("dName"));
				bean.setGender(rs.getString("gender"));
				bean.setEmail(rs.getString("email"));
				bean.setContactNum(rs.getLong("contactNo"));
				bean.setSpecilization(rs.getString("specilization"));
				bean.setExperience(rs.getString("experience"));

				dataList.add(bean);
			}
			return dataList;



		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			//5. Close ALL JDBC Objects
			try 
			{
				if(rs!=null){
					con.close();
				}
				pool.returnConnectionToPool(con);

				if(stmt!=null){
					stmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//End of outer try-catch
	}//End of 




}
