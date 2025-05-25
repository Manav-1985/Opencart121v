package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
//dataprovider1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path="C:\\Users\\manav\\OneDrive\\Desktop\\seleniumwebdriver\\Opencart121v\\testData\\Opencart_LoginData.xlsx";//taking xlsx file
		ExcelUtility xlutil=new ExcelUtility(path);//creating new excelutility object
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);

		
		//creating 2d arrary to store username and password
		String logindata[][]=new String[totalrows][totalcols];
		for(int r=1;r<=totalrows;r++)//starting from 1 as 1st row is header part but we have to change that one into zero as the array index will start from 0
		{
			for(int c=0;c<totalcols;c++)
			{
				logindata[r-1][c]=xlutil.getCellData("Sheet1", r, c); //ass the array index start from zero
			}
		}
			
		
		return logindata;
		
	}
}
