package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {
public CampaignPage(WebDriver driver) {
	PageFactory.initElements(driver ,this);
}
@FindBy(xpath="//img[@title='Creat Campaign...']")
private WebElement creatCampaignBtn;
//business library
/**
 * This method is use to click on the create campaign button
 */
public void clickOnCampaignBtn() {
	creatCampaignBtn.click();	
}
}
