package practie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewDatabes {

	public static void main(String[] args) {
		Random r=new Random();
		int num=r.nextInt(500);
		String projectName="TestYantra_"+num;
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8084/");
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectName);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("sonu");
		driver.findElement(By.xpath("(//select[@class='form-control' ])[2]")).click();
		//Select s=new Select(driver.findElement(By.xpath("(//select[@class='form-control' ])[2]")));
		//s.selectByValue("On Goging");
		driver.findElement(By.xpath("//option[text()='On Goging']")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();

		MySqlDemo ms=new MySqlDemo();
		ResultSet res=ms.executeQuery("projects", "project");

		try {
			while(res.next()) {
				String s = res.getString("project_name");
				if(s.equals(projectName))
				{
					System.out.println("Project "+projectName+" Successfully added into DataBase");
					break;
				}
			}
		} catch (SQLException e) {
			System.out.println("project not created");
			e.printStackTrace();
		}
		finally {
			try {
				ms.connection.close();
			} catch (SQLException e) {
				System.out.println("DB connection not closed");
				e.printStackTrace();
			}
			driver.quit();
		}
	}
}


