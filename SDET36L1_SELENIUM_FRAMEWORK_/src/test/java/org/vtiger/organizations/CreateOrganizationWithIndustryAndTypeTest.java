package org.vtiger.organizations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateOrganizationWithIndustryAndTypeTest {

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
		String expectedOrganizationName = excelUtility.getDataFromExcel(1,2,"dock2")+randomNumber;
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
		System.out.println("LOGIN SUCCESSFUL");
		driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOrganizationName);
		WebElement industry = driver.findElement(By.xpath("//select[@name='industry']"));
		Select element=new Select(industry);
		element.selectByValue("Education");
		WebElement type = driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select element1=new Select(type);
		element1.selectByValue("Press");
	    driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		System.out.println("DATA SAVED");
		String etitle=expectedOrganizationName;
		String eindustry="Education";
		String etype = "Press";
		String egroup = "Marketing Group";
		String atitle=driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		String aindustry=driver.findElement(By.xpath("//font[.='Education']")).getText();
		String atype=driver.findElement(By.xpath("//font[.='Press']")).getText();
		String agroup=driver.findElement(By.xpath("//a[.='Marketing Group']")).getText();
		if((etitle+eindustry+etype+egroup).equals(atitle+aindustry+atype+agroup)) {
			javaUtility.printStatement("CreateOrganizationWithIndustryAndTypeTest is successful----> Tc is pass");
		}
		else {
			javaUtility.printStatement("CreateOrganizationWithIndustryAndTypeTest is not successful----> Tc is fail");
		}
		WebElement administratorIcon= driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));
		webdriverUtility.mouseHoverOnElement(administratorIcon);
		driver.findElement(By.xpath("//a[normalize-space()='Sign Out']")).click();
		webdriverUtility.closeBrowser();
	}

}
