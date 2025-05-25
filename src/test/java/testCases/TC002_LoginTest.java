package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.Myaccountpage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	@Test(groups = {"Sanity","Master"})
	public void verify_login() {
		try {
		logger.info("**** Starting TC_002_LoginTest****");
		HomePage hp =new HomePage(driver);
		hp.clickmyacc();
		hp.clicklogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		Myaccountpage macc=new Myaccountpage(driver);
		boolean target=macc.isMyAccountPageExists();
		//Assert.assertTrue(target); or 
		Assert.assertEquals(target,true,"Login Failed");//here first 2 compared if false 3rd displayed
	}
		catch (Exception e) {
			Assert.fail();
		}
		logger.info("***Finished_TC_002_LoginTest***");
	}
	

}
