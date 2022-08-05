package practie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class createLeads {

	public static void main(String[] args) throws SQLException {
		//create the object for driver class which is given by db vender
		Driver driver=new Driver();
		//register the driver to jdbc
		DriverManager.registerDriver(driver);
		//establish the connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tyss","root","root");
		//create statement
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from sdet36;");
		//itrate the data
		while(result.next()) {
			System.out.println(result.getString(1));
		}
		int count=0;
		while(result.next()) {
			if(result.getString("empName").equals("monu"))
			{
				System.out.println("data is present in data base");
				count++;
				break;
			}
		}
		connection.close();
	}
}