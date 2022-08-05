package practie;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.WebDriverUtility;
import org.vtiger.objectRepository.LoginPage;

public class Login {

	@Test(dataProvider = "getData")
	public void loginTest(String userName,String password) {

		JavaUtility javaUtility = new JavaUtility();

		WebDriverUtility webDriverUtility = new WebDriverUtility();
		FileUtility propertyFileUtility=new FileUtility();

		propertyFileUtility.intiallizationPropertyFile(IConstants.VTIGERPROPERTYFILEPATH);

		String url = propertyFileUtility.getDataFromProperty("url");
		String timeout = propertyFileUtility.getDataFromProperty("timeout");
		String browser = propertyFileUtility.getDataFromProperty("browser");

		long longTimeout = javaUtility.convertStringToLong(timeout);

		WebDriver driver = webDriverUtility.setupDriver(browser);
		webDriverUtility.implicitWait(longTimeout);

		

		SoftAssert soft=new SoftAssert();

		webDriverUtility.openApplication(url);
		LoginPage loginPage= new LoginPage(driver);
		loginPage.loginAction(userName, password);
		soft.assertTrue(driver.getCurrentUrl().equals("http://localhost:8888/index.php?action=index&module=Home"));
		driver.quit();
	}
	@DataProvider(parallel = true)
	public String[][] getData(){
		ExcelUtility excelUtility = new ExcelUtility();
		excelUtility.intiallizeExcellFile(IConstants.VTIGEREXCELFILEPATH);
		return excelUtility.getMultipleDataFromExcel("Login Data");
	}

}