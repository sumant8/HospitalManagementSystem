package org.vtiger.campaign;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateCampaignWithProductTest {
	public static void main(String[] args) throws IOException {
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
		String url =fileUtility.getDataFromProperty("url");
		String timeouts = fileUtility.getDataFromProperty("timeout");
		
		long outs= Long.parseLong(timeouts);
		//featch data from excel file
				String expectedCampaignName = excelUtility.getDataFromExcel(2,2,"dock1")+randomNumber;
				String expectedProductName = excelUtility.getDataFromExcel(2,2,"dock2")+randomNumber;
				//convert string to long
				
				long longTimeout=javaUtility.convertStringToLong(timeouts);
				//lounching the browser in run tim based on browser key
				
				WebDriver driver=webdriverUtility.setupDriver(browser);
				//pre-setting for browser
				
				webdriverUtility.maximixeBrowser();
				webdriverUtility.implicitWait(longTimeout);
				//explicit wait,action
				
				webdriverUtility.intiallizeActions();
				
				//login to the application
				webdriverUtility.openApplication(url);
				
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.xpath("//a[@href='index.php?module=Products&action=index']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		WebElement moreElement= driver.findElement(By.xpath("//a[@href='javascript:;'][normalize-space()='More']"));
		webdriverUtility.mouseHoverOnElement(moreElement);
		driver.findElement(By.xpath("//a[@name='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(expectedCampaignName);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		String parentwh = driver.getWindowHandle();
		Set<String> allwh = driver.getWindowHandles();
		for(String wh:allwh) {
			if(!wh.equals(parentwh)) {
				driver.switchTo().window(wh);
			System.out.println("Control switched to child window");
			}
		}
		driver.findElement(By.xpath("//a[text()='hh']")).click();
		driver.switchTo().window(parentwh);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		String etitle=expectedCampaignName;
		String atitle=driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
		if(etitle.equals(atitle)) {
			javaUtility.printStatement("campign created-------->Tc pass");
		}
		else {
			javaUtility.printStatement("campign not created------->Tc fail");
		}
		WebElement administrator= driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));
		webdriverUtility.mouseHoverOnElement(administrator);
		driver.findElement(By.xpath("//a[normalize-space()='Sign Out']")).click();
		webdriverUtility.closeBrowser();
	}

}
