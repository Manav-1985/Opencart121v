package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class basepage {
	
	WebDriver driver;
	//creating a constructor
	public basepage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
		
	}

}
