package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration()
	{
		//adding every event to the log class update 1
		logger.info("*** Started TC001_AccountRegistrationTest *** ");
		try {
		//accessinghomepage
		HomePage hp =new HomePage(driver);
		hp.clickmyacc();
		logger.info("clicked on my account link");
		hp.clickregister();
		logger.info("clicked on register link");
		
		//now opening account register page
		
		AccountRegistrationPage regpage =new AccountRegistrationPage(driver);
		logger.info("providing registration details");
		regpage.setfirstname(randomstring());
		regpage.setlastname(randomstring());
		regpage.setemail(randomstring()+"@gmail.com");
		regpage.setphoneno(randomnumber());
		regpage.setpwd(randomalphanumeric());
		regpage.setpolicy();
		regpage.clickcontinue();
		
		logger.info("Validating expected message");
		String confmsg=regpage.getconfirmmsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			//if test is failed and we need debug level log then only specifying debug in xml is not enough so we add a logger.debug here
			logger.debug("Debug fails");
			Assert.assertTrue(false);
		}
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
	}
	
	catch(Exception e)
		{
		
		Assert.fail();
		 }	
		
	
	}

}
		
		//we will use random numbers to repeat tests again and again
		//wither we have ha static test data that we will enter or we use dynamic/random numbers
		
	//generating random strings and numbers in base class as they can be used further
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	


