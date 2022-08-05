package org.tyss.genericUtility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.LoginPage;

public class BaseClass extends InstanceClass{
		@BeforeSuite
		public void suiteSetup() {
			fileUtility = new FileUtility();
			javaUtility = new JavaUtility();
			excelUtility = new ExcelUtility();
		    webDriverUtility = new WebDriverUtility();
			fileUtility=new FileUtility();
			fileUtility.intiallizationPropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
			url = fileUtility.getDataFromProperty("url");
			un = fileUtility.getDataFromProperty("username");
			pwd = fileUtility.getDataFromProperty("password");
			timeout = fileUtility.getDataFromProperty("timeout");
			browser = fileUtility.getDataFromProperty("browser");
			to = javaUtility.convertStringToLong(timeout);
		}
		@BeforeClass
		public void classSetup() {
			driver = webDriverUtility.setupDriver(browser);
			webDriverUtility.maximixeBrowser();
			
			webDriverUtility.implicitWait(to);
			
			
			webDriverUtility.intiallizeActions();
			//wait=new WebDriverWait(driver,Duration.ofSeconds(to));
			webDriverUtility.openApplication(url);
			
			commonPage=new CommonPage(driver);
			loginPage=new LoginPage(driver);
			webDriverUtility.openApplication(url);
		
		}
		@BeforeMethod
		public void methodSetup() {
			randomNumber=javaUtility.getRandomNumber();
			
			loginPage.loginAction(un, pwd);
			
		}
		@AfterMethod
		public void methodTearDown() {
			commonPage.logoutAction(webDriverUtility);
		}
		@AfterClass
		public void classTearDown() {
			webDriverUtility.closeBrowser();
		}

}
