package org.vtiger.documents;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;
public class CreateDocumentTest {
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
		String expectedDocumentName = excelUtility.getDataFromExcel(1,1,"dock2")+randomNumber;
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

		//login to the app
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		System.out.println("LOGIN SUCCESSFUL");
		driver.findElement(By.xpath("//a[normalize-space()='Documents']")).click();
		driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
		driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(expectedDocumentName);
		
		WebElement frame = driver.findElement(By.xpath("//iframe[@title='Rich text editor,notecontent, press ALT 0 for help.']"));
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//body[@class='cke_show_border']")).sendKeys(expectedDocumentName, Keys.CONTROL+"a");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[@id='cke_5']")).click();
		driver.findElement(By.xpath("////a[@id='cke_6']")).click();
		String expectedFilePath = System.getProperty("user.dir")+expectedDocumentName;
		driver.findElement(By.xpath("//input[@name='filename']")).sendKeys(expectedFilePath);
		driver.findElement(By.xpath("//input[@title='Save [Alt+s]'")).click();
		String actualDocumentTitle = driver.findElement(By.xpath("span[@id='dtlview_Title']")).getText();
		String actualDescription = driver.findElement(By.xpath("td[@class='dvtCellInfo']/p")).getText().trim();
		String actualFileName = driver.findElement(By.xpath("//td[@class=['dvtCellInfo']/a")).getText().replace("_"," ");
		String[] splitFilePath = expectedFilePath.split("/");
		String expectedFileName = splitFilePath[splitFilePath.length-1];
		if(actualDocumentTitle.equals(expectedDocumentName) && actualDescription.equals(expectedDocumentName) && actualFileName.equals(expectedFileName));
{
			System.out.println("Document crewted successfully---> Tc is Pass");
			
}
		
		
		
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		System.out.println("DATA ENTERED");
		
		
		String etitle=actualDocumentTitle;
		String atitle=driver.findElement(By.xpath("//span[@id='dtlview_Title']")).getText();
		if(etitle.equals(atitle)) {
			System.out.println("DATA IS CORRECT");
		}
		else {
			System.out.println("DATA IS NOT CORRECT");
		}
		WebElement administratorIcon= driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));
		webdriverUtility.mouseHoverOnElement(administratorIcon);
		driver.findElement(By.xpath("//a[normalize-space()='Sign Out']")).click();
		webdriverUtility.closeBrowser();
	}

}
