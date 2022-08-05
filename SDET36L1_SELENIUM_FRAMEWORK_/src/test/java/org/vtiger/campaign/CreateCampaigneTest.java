package org.vtiger.campaign;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;
import org.vtiger.objectRepository.LoginPage;
public class CreateCampaigneTest {
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
		String expectedCampaignName = excelUtility.getDataFromExcel(2,1,"dock1")+randomNumber;
		//convert string to long
		long longTimeout=javaUtility.convertStringToLong(timeouts);
		//lounching the browser in run time based on browser key
		WebDriver driver=webdriverUtility.setupDriver(browser);
		//pre-setting for browser
		webdriverUtility.maximixeBrowser();
		webdriverUtility.implicitWait(longTimeout);
		//explicit wait,action
		webdriverUtility.intiallizeActions();
		//navigate the application
		webdriverUtility.openApplication(url);

		//login to the app
		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginAction(username, password);
		
		/*driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();*/

		WebElement moreElement= driver.findElement(By.xpath("//a[@href='javascript:;'][normalize-space()='More']"));
		webdriverUtility.mouseHoverOnElement(moreElement);
		driver.findElement(By.xpath("//a[@name='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();

		driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(expectedCampaignName);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		String actualCampaignName=driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
		if(expectedCampaignName.equals(actualCampaignName)) {
			javaUtility.printStatement("campaign create successfully----> Tc is pass");
		}
		else {
			javaUtility.printStatement("campaign is not created-----> Tc is fail");
		}
		WebElement administratorIcon= driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));
		webdriverUtility.mouseHoverOnElement(administratorIcon);
		driver.findElement(By.xpath("//a[normalize-space()='Sign Out']")).click();
		webdriverUtility.closeBrowser();

	}

}
