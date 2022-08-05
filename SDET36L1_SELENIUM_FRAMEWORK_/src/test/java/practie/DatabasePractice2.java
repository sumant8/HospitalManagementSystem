package practie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;
public class DatabasePractice2 {
	public static void main(String[] args) throws SQLException {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tyss","root","root");
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from sdet36;");
		while(result.next()) {
			System.out.println(result.getString(1)+" "+result.getString("empName"));
		}
		int count=0;
		ResultSet result1 = statement.executeQuery("select * from sdet36;");
		while(result1.next()) {
			if(result1.getString("empName").equals("monu")){
				System.out.println("data is present in database");
				count++;
				break;
			}
		}
		if(count==0) {
			System.out.println("data is not presnt in database"); 
		}
		connection.close();

	}

}
