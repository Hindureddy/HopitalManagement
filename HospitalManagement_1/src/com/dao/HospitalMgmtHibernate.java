package com.dao;

import java.util.List;

import com.dto.AdminBean;
import com.dto.DoctorBean;
import com.dto.PatientBean;

public class HospitalMgmtHibernate implements HospitalMgmtDAO
{


	public boolean patientRegister(String patientName,
			String patientEmail,String dob,int age,String gender,
			String patientAddress,long patientContact,
			String maritalStatus,String appointmentType){
		
	// TODO Auto-generated method stub
	return false;
}


public AdminBean authenticate(String adminId, String password) {
	// TODO Auto-generated method stub
	return null;
}

public boolean doctorRegister(String doctorname, String doctorEmail,
		String gender, long doctorContact, String specilization,
		String experience) {
	// TODO Auto-generated method stub
	return false;
}

public List<PatientBean> viewAllPatients() {
	// TODO Auto-generated method stub
	return null;
}

public List<DoctorBean> viewAllDoctors() {
	// TODO Auto-generated method stub
	return null;
}



}
