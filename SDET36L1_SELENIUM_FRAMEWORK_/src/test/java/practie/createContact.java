package practie;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class createContact {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties p=new Properties();
		p.load(fis);
		WebDriver driver=null;
		String browser = p.getProperty("browser").trim();
		String username = p.getProperty("userName").trim();
		String password = p.getProperty("password").trim();
		String url =p.getProperty("url").trim();
		String timeouts = p.getProperty("timeout").trim();
		FileInputStream fs=new FileInputStream("./src/test/resources/products.xlsx.xlsx");
		Workbook wb = WorkbookFactory.create(fs);
		String data1= wb.getSheet("dock1").getRow(1).getCell(1).getStringCellValue();
		String data2= wb.getSheet("dock1").getRow(5).getCell(5).getStringCellValue();
		long out= Long.parseLong(timeouts);
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver=new ChromeDriver();
			break;

		default:
			System.out.println("this is browser problem");
			break;	}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(out));
		System.out.println("url entered");
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		System.out.println("LOGINE SUCCESSFUL");
		driver.findElement(By.xpath("//a[@href='index.php?module=Leads&action=index']")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(data1);
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys(data2);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		System.out.println("DATA saved");
		WebElement administrator= driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));
		Actions a=new Actions(driver);
		a.moveToElement(administrator).perform();
		driver.findElement(By.xpath("//a[normalize-space()='Sign Out']")).click();
		System.out.println("SUCCESS FULLY LOGOUT");
		driver.quit();


	}

}
