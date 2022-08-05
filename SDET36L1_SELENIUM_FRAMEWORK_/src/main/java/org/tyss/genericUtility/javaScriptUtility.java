package org.tyss.genericUtility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

	/**
	 * this class contain java reusable method
	 * @author dell
	 *
	 */
	public class javaScriptUtility {
		private JavascriptExecutor js;
		/**
		 * @return 
		 * 
		 */
			public JavascriptExecutor webDriverToJavaScriptEx(WebDriver driver){
				 js=(JavascriptExecutor)driver;
				return js;
			}
			/**
			 * 
			 * @param url
			 */
			public void getUrl(String url){
				js.executeScript("window.location='"+url+"'");
			}
			/**
			 * 
			 * @param url
			 */
			
			public void sendkeyToElement(WebElement element, String keys){
				js.executeScript("arguments[0].value=arguments[1]",element,keys);
			}
			
			/**
			 * 
			 * @param url
			 */
			public void clickToElement(WebElement element){
				js.executeScript("arguments[0].click()",element);
			}
			/**
			 * 
			 */
			public void scrollTo(int x,int y){
				js.executeScript("window.scrollBy("+x+","+y+")");
			}
			/**
			 * 
			 * @param x
			 */
			public void scrollToHeight(int x){
				js.executeScript("window.scrollBy("+x+",document.body.scrollHeight)");
			}
			/**
			 * 
			 */
			public void scrollToWidth(int y){
				js.executeScript("window.scrollBy(document.body.scrollWidth,"+y+")");
			}
			/**
			 * 
			 */
			public void scrollToElement(WebElement element){
				js.executeScript("arguments[0].scrollIntoView()", element);
			}
}
