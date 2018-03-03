package com.dao;

import java.util.List;

import com.dto.AdminBean;
import com.dto.DoctorBean;
import com.dto.PatientBean;

public interface HospitalMgmtDAO 
{
	public boolean patientRegister(String patientName,
			String patientEmail,String dob,int age,String gender,
			String patientAddress,long patientContact,
			String maritalStatus,String appointmentType);


	public AdminBean authenticate(String adminId, String password);



	public boolean doctorRegister(String doctorname,
			String doctorEmail,String gender,
			long doctorContact,
			String specilization,String experience);

	public List<PatientBean> viewAllPatients();
	public List<DoctorBean> viewAllDoctors();


}
