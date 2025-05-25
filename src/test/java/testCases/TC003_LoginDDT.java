package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.Myaccountpage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	//data valid login successful-- test passed---logout..............positive test case
	//data valid login unsuccessful--- test failed
	//data invalid login successful-- test failed----logout
	//data invalid login unsuccessful--- test passed..............negative test case
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = "Datadriven")
	//as data provider is not under same class we need to specify the class also------dataProviderClass = DataProviders.class
	public void verify_loginDDT(String email,String pwd,String exp)//exp---expected results
	{
		logger.info("***Starting_TC003_LoginDDT_Test***");
	
		try {
	
		
		HomePage hp =new HomePage(driver);
		hp.clickmyacc();
		hp.clicklogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		Myaccountpage macc=new Myaccountpage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		

		//data valid login successful-- test passed---logout..............positive test case
		//            login unsuccessful--- test failed
		
		
		if(exp.equalsIgnoreCase("Valid"))//make sure that even if the letters are upper case and lower case and contains same value they should be considered equal
		{
			if(targetPage==true)//vaild data and login successful
			{
				//this should be before assert as after assertion no statement is passed as the test is done
				Assert.assertTrue(true);
				macc.clickLogout();//test passed so click on logout
				//we log out to make sure for next data entry we are on the login page
			}
			else//valid data login unsuccessful
			{
				Assert.assertTrue(false);
				//no need to do logout as we are on same login page
			}
		}
		
		
		//data invalid login successful-- test failed----logout
		//				login unsuccessful--- test passed..............negative test case
		
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
				
			}
		}

		}
		catch (Exception e) {
			Assert.fail("An Exception occured : "+e.getMessage());
		}
		
		logger.info("***Finished_TC003_LoginDDT_Test***");
	}
}
