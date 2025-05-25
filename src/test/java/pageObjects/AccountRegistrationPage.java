package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends basepage{
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement  txtfirstname ;
	
	@FindBy(xpath = "//input[@id='input-lastname']")////input[@id='input-lastname']
	WebElement  txtlastname ;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement  txtemail ;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement  txtphone ;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtpwd ;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtconfirmpwd  ;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkpolicy  ;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btncontinue  ;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmmsg;
	
	public void setfirstname(String fname)
	{
		txtfirstname.sendKeys(fname);
	}
	public void setlastname(String lname)
	{
		txtlastname.sendKeys(lname);
	}
	public void setemail(String email)
	{
		txtemail.sendKeys(email);
	}
	public void setphoneno(String tel)
	{
		txtphone.sendKeys(tel);
	}
	public void setpwd(String pwd)
	{
		txtpwd.sendKeys(pwd);
		txtconfirmpwd.sendKeys(pwd);
	}
	public void setpolicy()
	{
		chkpolicy.click(); ;
	}
	public void clickcontinue()
	{
		btncontinue.click();
	}
	public String getconfirmmsg ()
	{
		try {
			return(confirmmsg.getText());
		}
		catch (Exception e) {
			// TODO: handle exception
			return(e.getMessage());
		}
	}

}
