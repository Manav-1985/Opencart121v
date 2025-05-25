package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends basepage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkmyacc;
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkregister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement lnklogin;
	
	public void clickmyacc()
	{
		lnkmyacc.click();
	}
	public void clickregister()
	{
		lnkregister.click();
	}
	public void clicklogin()
	{
		lnklogin.click();
	}

}
