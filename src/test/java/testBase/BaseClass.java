package testBase;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;//log4j imports
import org.apache.logging.log4j.Logger;


public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	//import from logj4core see carfully
	@BeforeClass(groups = {"Sanity","Master","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException
	{
		//loading config.properties file
		FileReader file =new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());//log4j2 code
		
		switch(browser.toLowerCase()) {
		case "chrome":driver=new ChromeDriver();break;
		case"edge":driver=new EdgeDriver();break;
		default: System.out.println("Browser Not Available");return;//return will end the execcution here  and returns to the top is default case comeby
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		//driver.get("http://localhost/opencart/upload/");
		//now getting from the properties file
		driver.get(p.getProperty("appURL1"));
		driver.manage().window().maximize();
		
		//updation 1 for entering the log2j4 filr
		
	}
		@AfterClass(groups = {"Sanity","Master","Regression"})
		public void teardown()
		{
			driver.close();
		}
		
		//this is from coommon library extension in pomxml
				public String randomstring()
				{
					String generatedstring=RandomStringUtils.randomAlphabetic(5);
					return generatedstring;
				}
				public String randomnumber()
				{
					String generatednumber= RandomStringUtils.randomNumeric(10);
					return generatednumber;
				}
				public String randomalphanumeric()
				{
					String generatedstring=RandomStringUtils.randomAlphabetic(5);
					String generatednumber= RandomStringUtils.randomNumeric(4);
					return (generatednumber+generatedstring);//to add special character (say@) use "@"
				}
				
				//capturing screen and creating timestamp for report file
				
				public static String captureScreen(String tname) {
					String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
					
					TakesScreenshot takesScreenshot =(TakesScreenshot) driver;
					File sourcefile =takesScreenshot.getScreenshotAs(OutputType.FILE);
					
					 String targetFilePath = "C:\\Users\\manav\\OneDrive\\Desktop\\seleniumwebdriver\\Opencart121v\\screenshots\\" + tname + "_" + timeStamp + ".png";
					 try {
				            // Ensure folder exists
				            new File("C:\\Users\\manav\\OneDrive\\Desktop\\seleniumwebdriver\\Opencart121v\\screenshots").mkdirs();

				            // Save screenshot
				            File targetFile = new File(targetFilePath);
				            FileUtils.copyFile(sourcefile, targetFile);
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
				
					
					return targetFilePath;
					
				}
				
				
				
				
				
				
	
}
