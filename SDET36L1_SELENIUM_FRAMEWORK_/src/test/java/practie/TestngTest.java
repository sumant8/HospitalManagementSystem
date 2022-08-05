package practie;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestngTest {
	@BeforeSuite
	public void test() {
		Reporter.log("hii1");
	}
	@BeforeTest
	public void test1() {
		Reporter.log("hii2");
	}
	@BeforeClass
	public void test2() {
		Reporter.log("hii3");
	}
	@BeforeMethod
	public void test3() {
		Reporter.log("hii4");
	}
	@Test
	public void loginTest0() {
		System.out.println("hii5");
	}
	@AfterMethod
	public void test4() {
		Reporter.log("hii6");
	}
	@AfterClass
	public void test5() {
		Reporter.log("hii7");
	}
	@AfterTest
	public void test6() {
		Reporter.log("hii8");
	}
	@AfterSuite
	public void test7() {
		Reporter.log("hii9");
	}
}
