package com.dto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPoolManager 
{ 
	private Vector<Connection> pool = new Vector<Connection>();
	private String driverClass = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/hospital?";
	private String userNM = "j2ee";
	private String password = "root";
	private int pool_size = 20;
	
	//singleton class
	private static ConnectionPoolManager instance = null;
	
	public static ConnectionPoolManager getInstance() 
	throws Exception
	{
		if(instance==null){
			instance = new ConnectionPoolManager();
		}
		return instance;
	}
	
	private ConnectionPoolManager() 
	throws Exception
	{
		System.out.println("Creating the Connection Pool ...");
		
		//1. Load the Driver
		Class.forName(driverClass).newInstance();
		
		//2. Get the DB Connection via Driver
		for(int i=0; i<pool_size; i++) {
			Connection con = DriverManager.getConnection(dbUrl, userNM, password);
			pool.add(con);
		}
		
	}//End of Constructor
	
	public Connection getConnectionFromPool() 
	throws SQLException
	{
		System.out.println("Pool Size before getting the Connection : "+pool.size());
		
		Connection con = null;
		if(pool.size()>0)
		{
			con = pool.get(0);
			pool.remove(0);
			
		}else{
			//if more thn 20 users to connect the database tht ll gives one by one connection objects per time
			con = DriverManager.getConnection(dbUrl, userNM, password);
		}
		
		System.out.println("Pool Size after getting the Connection : "+pool.size());
		return con;
	}//End of getConnectionFromPool()
	
	// clode all the connection objects at a time
	public void returnConnectionToPool(Connection con) 
	throws SQLException
	{
		System.out.println("Pool Size before returning the Connection : "+pool.size());
		if(pool.size()<=pool_size)
		{
			pool.add(con);
		}else{
			con.close();
		}
		
		System.out.println("Pool Size after returning the Connection : "+pool.size());
	}//End of returnConnectionToPool()
}//End of Class
