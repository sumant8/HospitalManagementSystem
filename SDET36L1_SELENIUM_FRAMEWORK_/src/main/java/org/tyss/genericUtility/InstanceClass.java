package org.tyss.genericUtility;


	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.LoginPage;


	public class InstanceClass {
		public FileUtility fileUtility;
		public JavaUtility javaUtility;
		public WebDriver driver;
		public WebDriverWait wait;
		public ExcelUtility excelUtility;
		public WebDriverUtility webDriverUtility;
		protected String url;
		protected String un;
		protected String pwd;
		protected String timeout;
		protected String browser;
		protected long to;
		public LoginPage loginPage;
		public CommonPage commonPage;
		public int randomNumber;
	}

