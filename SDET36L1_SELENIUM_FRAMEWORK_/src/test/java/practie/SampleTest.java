package practie;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

public class SampleTest{
	@Test(retryAnalyzer =IRetryAnalyzer.class)
	public void sampleTest() {
		System.out.println("test case 1 pass");
		System.out.println("test case 2 pass");
		System.out.println("test case 3 pass");
		System.out.println("test case 4 pass");

		Assert.fail();
	}
}

