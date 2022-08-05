package org.tyss.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;
/**
 * this class contain only database related reusable method
 * @author dell
 *
 */
public class DatabaseUtility {
	private Connection connection;
	/**
	 * this method is use to establish the connection with MYSQL data base
	 * @param url
	 * @param userName
	 * @param Password
	 */
	public void getConnectionWithDB(String url,String userName, String Password) {
		Driver dbDriver=null;
		try {
			dbDriver=new Driver();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			DriverManager.registerDriver(dbDriver);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			connection =DriverManager.getConnection(url,userName,Password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * this method is use to modify the data in database
	 */
	public int modifyDataInDB(String sqlQuery) {
		int count=0;
		try {
			count=connection.createStatement().executeUpdate(sqlQuery);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * this method is use to close the database connection
	 */
	public void coseDBConnction() {
		try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connection closed");
	}
	public List<String>getDtatafromDB(String query,String columnNamOrcolumnIndex){
		ResultSet result=null;
		List<String>list=new ArrayList();
		String s1= " ";
		for(int i=0;i< columnNamOrcolumnIndex.length();i++) {
			if(Character.isDigit(columnNamOrcolumnIndex.charAt(i)))
			{
				s1=s1+columnNamOrcolumnIndex.charAt(i);
			}
			else {
				break;
			}
		}
		try {
			result=connection.createStatement().executeQuery(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			while(result.next())
			{
				try {
				if(columnNamOrcolumnIndex.length()==s1.length())
					list.add(result.getString(Integer.parseInt(s1)));
				
				else {
					list.add(result.getString(columnNamOrcolumnIndex));
				}
				}catch(SQLException e) {
				e.printStackTrace();
			}
		
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return list;
}
/**
 * this method is use to verify whether the expected data is present or not
 * @param query
 * @param columnNameOrcolumnIndex
 * @param expectdData
 * @return
 */
	
public boolean verifyDataInDB(String query,String columnNameOrcolumnIndex,String expectdData) {
	List<String> list=getDtatafromDB(query,columnNameOrcolumnIndex);
	System.out.println(list);
	boolean flag=false;
	for(String data : list) {
		if(data.equalsIgnoreCase(expectdData)) {
			flag=true;
			break;
		}
	}
	return flag;
}

}