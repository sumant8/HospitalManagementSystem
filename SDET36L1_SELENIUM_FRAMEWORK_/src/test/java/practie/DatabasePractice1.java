package practie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;
public class DatabasePractice1 {
	public static void main(String[] args) throws SQLException {
		
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tyss","root","root");
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from sdet36;");
		while(result.next()) {
			System.out.println(result.getString(1)+" "+result.getString("empName"));
		}
		Statement statement1 = connection.createStatement();
		int result1=statement1.executeUpdate("insert into sdet36 value(1005,'tonu',22334455,'tonu@gmail.com','bangalore');");
		if(result1==1) {
			System.out.println("data enter into database");
		}
		connection.close();
	}
}