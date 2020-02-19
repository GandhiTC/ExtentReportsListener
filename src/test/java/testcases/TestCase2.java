package testcases;



import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;



public class TestCase2
{
	@Test(priority=1)
	public void doLogin()
	{
		System.out.println("Executing Login Test");
	}


	@Test(priority=2)
	public void doUserReg()
	{
		Assert.fail("User Reg Test Failed");
	}


	@Test(priority=3)
	public void doSkip()
	{
		throw new SkipException("Skipping the test case");
	}
	
	
	@Test(priority=4, dependsOnMethods={"doSkip"})
	public void doDepending()
	{
		System.out.println("This test was not skipped.");
	}
}