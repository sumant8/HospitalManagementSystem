package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage {
	public CreateNewCampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@name='campaignname']")
	private WebElement campaignNameTextField;
	
	@FindBy(xpath = "//input[@title=\\\"Save [Alt+S]\\\"]")
	private WebElement saveBtn;
	
	//business library
	/**
	 * This method is used to click on createCampaignButton
	 * @param expectedCampaignName
	 */
	public void createCampaign(String expectedCampaignName) {
		campaignNameTextField.sendKeys(expectedCampaignName);
		saveBtn.click();
	}
}
