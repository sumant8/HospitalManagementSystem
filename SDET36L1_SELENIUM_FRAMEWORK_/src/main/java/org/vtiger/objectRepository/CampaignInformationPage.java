package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInformationPage {
	   public CampaignInformationPage(WebDriver driver) {             
		   PageFactory.initElements(driver, this);
	  }

	   @FindBy(xpath = "//span[@id='dtlview_Campaign Name']")
	   private WebElement actualCamapaignNametext;
	   /**
	    * This method is use to click on the create ca,paign button
	    * @return
	    */

	public String getActualCamapaignName() {
		return actualCamapaignNametext.getText();
}
}