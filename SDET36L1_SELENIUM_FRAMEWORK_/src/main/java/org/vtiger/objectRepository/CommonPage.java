package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tyss.genericUtility.WebDriverUtility;

public class CommonPage {
	public CommonPage(WebDriver driver) { 
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath ="//a[.='More']") 
	private WebElement moreTab;
	@FindBy(xpath ="//a[.='Campaigns']")
	private WebElement campaignsTab;
	@FindBy(xpath ="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminstratorIcon;
	@FindBy(xpath ="//a[.='Sign Out']")
	private WebElement signOutLink;
	/*
	 * This method is used to click on campaign tabin common page
	 * @param webDriverUtility
	 */
	public void clickCampaign(WebDriverUtility webDriverUtility) {
		webDriverUtility.mouseHoverOnElement(moreTab);
		campaignsTab.click();
	}
	/*
	 * This method is used to signout from the application
	 * @param webDriverUtility
	 */
	public void logoutAction(WebDriverUtility webDriverUtility) {
		webDriverUtility.mouseHoverOnElement(adminstratorIcon);
		signOutLink.click();
	}
}
