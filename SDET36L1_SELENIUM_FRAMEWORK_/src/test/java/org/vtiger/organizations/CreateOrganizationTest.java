package org.vtiger.organizations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateOrganizationTest {

	public static void main(String[] args) {
		//create object from generic utility
		FileUtility fileUtility=new FileUtility();
		JavaUtility javaUtility=new JavaUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		WebDriverUtility webdriverUtility=new WebDriverUtility();
		//data from property file
		fileUtility.intiallizationPropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
		//generate the random number
		int randomNumber=javaUtility.getRandomNumber();
		//get the control for particuler sheetin excel
		excelUtility.intiallizeExcellFile(IConstants.VTIGEREXCELFILEPATH);
		//featch the data from property file
		String browser = fileUtility.getDataFromProperty("browser");
		String username = fileUtility.getDataFromProperty("userName");
		String password = fileUtility.getDataFromProperty("password");
		String url = fileUtility.getDataFromProperty("url");
		String timeouts = fileUtility.getDataFromProperty("timeout");
		//featch data from excel file
		String expectedOrganizationName = excelUtility.getDataFromExcel(2,1,"dock1")+randomNumber;
		//convert string to long
		long longTimeout=javaUtility.convertStringToLong(timeouts);
		//lounching the browser in run tim based on browser key
		WebDriver driver=webdriverUtility.setupDriver(browser);
		//pre-setting for browser
		webdriverUtility.maximixeBrowser();
		webdriverUtility.implicitWait(longTimeout);
		//explicit wait,action
		webdriverUtility.intiallizeActions();
		//navigate the application
		webdriverUtility.openApplication(url);

		//login to the application
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOrganizationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String originalOrganizationName=driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		if(expectedOrganizationName.equals(originalOrganizationName)) {
			javaUtility.printStatement("organization create successfully----> Tc is pass");
		}
		else {
			javaUtility.printStatement("Organization not create successfully----> Tc is fail ");
		}
		
		WebElement administratorIcon= driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));
		webdriverUtility.mouseHoverOnElement(administratorIcon);
		driver.findElement(By.xpath("//a[normalize-space()='Sign Out']")).click();
		webdriverUtility.closeBrowser();

	}

}
