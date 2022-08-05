package org.vtiger.organizations;



import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOrganizationTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties = new Properties();
		properties.load(fis);
		int randomNumber= new Random().nextInt(1000);
		FileInputStream fisExcel=new FileInputStream("./src/test/resources/products.xlsx");
		Workbook workbook=WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("dock1");


		String browser= properties.getProperty("browser").trim();
		String userName= properties.getProperty("username").trim();
		String password= properties.getProperty("password").trim();
		String url= properties.getProperty("url").trim();
		String timeouts= properties.getProperty("timeout").trim();
		long longTimeOut=Long.parseLong(timeouts);

		String ExpectedContactLastName = sheet.getRow(2).getCell(1).getStringCellValue()+randomNumber;
		System.out.println(ExpectedContactLastName);

		WebDriver driver=null;
		//run time polymorphism
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		default:
			break;
		}


		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeOut));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(longTimeOut));
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();


		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		int randomNumber1=new Random().nextInt(100);
		String expectedOrganization = "xyz"+randomNumber1;
		System.out.println(expectedOrganization);
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOrganization);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='small']"))));
		driver.findElement(By.xpath("//a[@class='hdrLink']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[.='Go to Advanced Search']"))));
		String[] arrPageCount = driver.findElement(By.xpath("//span[@name='Accounts_listViewCountContainerName']")).
				getText().split(" ");
		String pageCount = arrPageCount[arrPageCount.length-1];
		driver.findElement(By.xpath("//input[@class='small']")).clear();
		driver.findElement(By.xpath("//input[@class='small']")).sendKeys(pageCount,Keys.ENTER);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='status']"))));


		List<WebElement> listOrganization = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a"));
		System.out.println(listOrganization.size());
		for (int i=0;i<listOrganization.size();i++) {
			String orgName = listOrganization.get(i).getText();

			if(orgName.equals(expectedOrganization)) {

				driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+(i+2)+"]/td[1]/input")).click();
				break;
			}
		}

		driver.findElement(By.xpath("//input[@class='crmbutton small delete']")).click();
		driver.switchTo().alert().accept();
		List<WebElement> listOrganization2 = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a"));
		while(listOrganization2.size()==listOrganization.size()) {
			Thread.sleep(500);
			listOrganization2 = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a"));
		}
		System.out.println(listOrganization2.size());
		int count=0;
		for(int i1=0;i1<listOrganization2.size();i1++) {
			String orgName1 = listOrganization2.get(i1).getText();
			if(orgName1.equals(expectedOrganization)) {
				count++;
				break;
			}
		}

		if(count==0) {
			System.out.println("TC pass");
		}

		driver.quit();

	}

}
