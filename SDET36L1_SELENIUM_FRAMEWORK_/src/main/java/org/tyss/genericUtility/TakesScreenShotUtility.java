package org.tyss.genericUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
/**
 * this class contain java reusable method
 * @author dell
 *
 */
public class TakesScreenShotUtility {
	TakesScreenshot ts;
	/**
	 * 
	 */
	public TakesScreenshot webDriverToTakesScreenShot(WebDriver driver){
		ts=(TakesScreenshot)driver;
		return ts;
	}
/**
 * 
 * @param driver
 * @return
 */
	public void screenShot(String className,String date){
		File src = ts.getScreenshotAs(OutputType.FILE);
		File desc=new File("./errorshots/"+className+date+".png");
		try {
			FileUtils.copyFile(src, desc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

