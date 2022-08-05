package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(xpath ="//input[@name='user_name']")
	private WebElement userNameTextField;
	
	@FindBy(xpath = "//input[@name='user_password']")
	private WebElement passwordTextFiled;
	
	@FindBy(xpath = "//input[@id='submitButton']")
	private WebElement LoginBtn;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
//business library
	/**
	 * This method is used to login to the application
	 * @param username
	 * @param password
	 */
	public void loginAction(String username,String password) {
		userNameTextField.sendKeys(username);
		passwordTextFiled.sendKeys(password);
		LoginBtn.click();
	}
}
