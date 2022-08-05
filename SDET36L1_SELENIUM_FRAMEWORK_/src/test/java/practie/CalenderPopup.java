package practie;

	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.tyss.genericUtility.JavaUtility;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class CalenderPopup {

		public static void main(String[] args) {
			JavaUtility javaUtility = new JavaUtility();
			WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
			String reqData="13-October-2023";
			driver.findElement(By.xpath("//input[@id='first_date_picker']")).click();
			WebElement currentMonthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']"));
			wait.until(ExpectedConditions.visibilityOf(currentMonthYear));

			String[] arrMonYear=currentMonthYear.getText().split(" ");
			String currentMonth=arrMonYear[0];
			String currentYear=arrMonYear[1];

			int currentMonthValue = javaUtility.convertMonthFromStringToInt(currentMonth, "MMMM");
			int requiredMonthValue = javaUtility.convertMonthFromStringToInt(reqData.split("-")[1], "MMMM");
			int currentYearValue = Integer.parseInt(currentYear);
			int requiredYearValue = Integer.parseInt(reqData.split("-")[2]);
			try {
				while(requiredMonthValue>currentMonthValue || requiredYearValue>currentYearValue) {
					driver.findElement(By.xpath("//span[.='Next']")).click();
					arrMonYear=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText().split(" ");
					currentMonth=arrMonYear[0];
					currentYear=arrMonYear[1];
					currentMonthValue = javaUtility.convertMonthFromStringToInt(currentMonth, "MMMM");
					currentYearValue = Integer.parseInt(currentYear);
				}
				while(requiredMonthValue<currentMonthValue || requiredYearValue<currentYearValue) {
					driver.findElement(By.xpath("//span[.='Prev']")).click();
					arrMonYear=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText().split(" ");
					currentMonth=arrMonYear[0];
					currentYear=arrMonYear[1];
					currentMonthValue = javaUtility.convertMonthFromStringToInt(currentMonth, "MMMM");
					currentYearValue = Integer.parseInt(currentYear);
				}
				driver.findElement(By.xpath("//a[.='"+reqData.split("-")[0]+"']")).click();
			}
			catch (Exception e) {
				System.out.println("please enter proper format (dd-MMMM-yyyy) or please enter valid data");

			}
			driver.quit();
		} 
	}
