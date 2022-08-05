package org.tyss.genericUtility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class WebDriverUtility {
private WebDriver driver;
private WebDriverWait wait;
private Actions act;
public WebDriver setupDriver(String browser) {
if(browser.equals("chrome")) {
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
}
else if(browser.equals("firefox")) {
	WebDriverManager.firefoxdriver().setup();
	driver=new FirefoxDriver();
}
else if(browser.equals("ie")) {
	WebDriverManager.iedriver().setup();
	driver=new InternetExplorerDriver();
}
else {
	System.out.println("YOU ENTER WRONG BROWSER KEY IN THE PROPERTY FILE");
}
return driver;
}
public void maximixeBrowser() {
	driver.manage().window().maximize();
}
public void implicitWait(long longTimeout) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
}
public void openApplication(String url) {
	driver.get(url);
}
public void intiallizeActions() {
	act =new Actions(driver);
}
public void mouseHoverOnElement(WebElement element) {
	act.moveToElement(element).perform();
}
public void closeBrowser() {
	driver.quit();
}
public void closeTab() {
	driver.close();
}
public void switchWindow(String partialText,String strategy) {
	Set<String> winIds = driver.getWindowHandles();
	for(String id:winIds) {
		driver.switchTo().window(id);
		if(strategy.equalsIgnoreCase("url")) {
			if(driver.getCurrentUrl().contains(partialText)) {
				break;
			}
		}
		else if(strategy.equalsIgnoreCase("title")) {
			if(driver.getTitle().contains(partialText)) {
				break;
			}
		}
	}
}
/*
 * This method will wait till the element is clickable(custom wait)
 * @param totalDuration
 * @param pollingTime
 * @param element
 */
public void waitTillElementClickable(int totalDuration, long pollingTime, WebElement element ) {
	int currentTime=0;
	while(currentTime<=totalDuration) {
		try {
			element.click();
			break;
		}
		catch(Exception e) {
			try {
				Thread.sleep(pollingTime);
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
			currentTime++;
		}
	}
}
/*
 * This method is used to initialize the Explicit wait or WebDriverWait
 * @param timeOuts
 * @param pollingTime
 */
public void initiallizeExplicitWait(long timeOuts,long pollingTime) {
	wait=new WebDriverWait(driver, Duration.ofSeconds(timeOuts));
	wait.pollingEvery(Duration.ofMillis(pollingTime));
	wait.ignoring(Exception.class);
	
}
/*
 * This method is used to wait untill element is visible 
 */
public void waitTillElementVisible(WebElement element) {
	wait.until(ExpectedConditions.visibilityOf(element));
}
/*
 * This method is used to wait untill element is invisible 
 */
public void waitTillElementInvisible(WebElement element) {
	wait.until(ExpectedConditions.invisibilityOf(element));
}
/*
 * This method is use to handle alert popup
 */
public void jsPopupaccept() {
	driver.switchTo().alert().accept();
}
/*
 * This method is use to handle confirmation popup
 */
public void jsPopupdecline() {
	driver.switchTo().alert().dismiss();
}
/*
 * This method is use to handle enter the data in alert popup
 */
public void jsPopupSendData(String data) {
	driver.switchTo().alert().sendKeys(data);
}
/*
 * This method is use to handle gettext
 */

public void jsPopupgetText() {
	driver.switchTo().alert().getText();
}

}
