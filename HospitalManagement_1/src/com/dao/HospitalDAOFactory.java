package com.dao;

public class HospitalDAOFactory {

	public static String interactionType="JDBC";
	
	static HospitalMgmtDAO dao=null;
public static HospitalMgmtDAO	getInstance()
{
	if(interactionType.equals("JDBC"))
	{
		
		dao=new HospitalMgmtJDBCImpl();
		return dao;
	}else if(interactionType.equals("Hibernate"))
	{
		
		dao=new HospitalMgmtHibernate();
		return dao;
	}else{
		return null;
	}
	
	}
	
}
