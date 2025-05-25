package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportUtility implements ITestListener{
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
//		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//		Date dt= new Date();
//		String currentdatetimestamp=df.format(dt);
//		take it to the base case as we can use the format may a times
		String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());


		
		repName="Test-Report-"+timeStamp+".html";//name created
		new File("reports").mkdirs();  // creates the folder if it doesn’t exist
		sparkreporter =new ExtentSparkReporter(".\\reports\\"+repName);//location specified
		
		sparkreporter.config().setDocumentTitle("OpenCart Automation Report");//title for the report
		sparkreporter.config().setReportName("Opencart Functional Festing");//name of the report
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent =new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application","OpenCart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub Module","Customers");
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		
		//getting parameters from the xml file from which we are running the tests
		String os =testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating system", os);                   
		
		String browser =testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups",includedGroups.toString());
		}
		
	}
		
	
	
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in the report
		test.log(Status.PASS,result.getName()+" got successfully executed");
		
	}
	
	
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+"got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		//taking ss on getting failed test but this ss method is created in base class to be used by all
		try {
			  // ✅ Use static method to capture screenshot
	        String imgPath = BaseClass.captureScreen(result.getName());

	        test.addScreenCaptureFromPath(imgPath); // ✅ Attach to report
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	
	
	
	public void onFinish(ITestContext testContext)
	{
		
		extent.flush();
		System.out.println("Extent Report flushed.");

		//to open log directly fter testing we do this 
		
		String pathOfExtentReport =System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentreport =new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentreport.toURI());
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
