package practie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class MySqlDemo {


	public Driver driver;
	public Connection connection;
	public Statement statement;
	public ResultSet result;

	public  void setUp(String DBName) {
		try {
			driver=new Driver();
		} catch (SQLException e1) {
			System.out.println("DB driver creation failed");
			e1.printStackTrace();
		}

		try {
			DriverManager.registerDriver(driver);
		} catch (SQLException e) {
			System.out.println("DB driver registration failed");
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBName+"", "root", "root");
		} catch (SQLException e) {
			System.out.println("DB connection failed");
			e.printStackTrace();
		}
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("BD statement creation failed");
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String DBName,String tableName){
		this.setUp(DBName);
		try {
			result = statement.executeQuery("select * from "+tableName);
		} catch (SQLException e) {
			System.out.println("Query execution failed");
			e.printStackTrace();
		}
		/*
		try {
			while(result.next()) {
				System.out.println(result.getString("project_name"));
			}
		} catch (SQLException e) {
			System.out.println("Data retrival from DB failed");
			e.printStackTrace();
		}

		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("DB Connction not closed");
				e.printStackTrace();
			}
		}
*/
		return result;
	}

	public void insertRecord(String DBName, String tableName,String query) {
		this.setUp(DBName);
		try {
			statement.executeUpdate("insert into "+tableName+query);
			System.out.println("Record insertion into BD successful");
		} catch (SQLException e) {
			System.out.println("Record insertion failed");
			e.printStackTrace();
		}

		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("DB Connction not closed");
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		//MySqlDemo m=new MySqlDemo();
	}

}


