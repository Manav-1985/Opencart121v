package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	//creating variables for simplicity
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;//for path of excel file

	
	public ExcelUtility(String path) {
		this.path=path;
		
		
		//crearting this so that we can use excelutility file here
		// TODO Auto-generated constructor stub
	}


	public int getRowCount(String sheetName) throws IOException
	{
		fi =new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet =workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;	
	}
	
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi =new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet =workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount =row.getLastCellNum();
		
		workbook.close();
		fi.close();
		return cellcount;	
	}
	
	
	
	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi =new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet =workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		DataFormatter formatter= new DataFormatter();
		String data;
		try {
		data=formatter.formatCellValue(cell);//regardless of cell type returns the formatted value of cell as string using try catch as somethimes cell value is null
		}
		catch (Exception e) {
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	
	}
	
	
	
	
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	{
		File xlfile =new File(path);//this will go to the the file
		
		                                                  //if file does not exist then create a file
		if (!xlfile.exists())
		{
			workbook=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		                                                       
		if(workbook.getSheetIndex(sheetName)==-1)                            //if sheet with  does not exist then create a new worksheet with name as per sheetName
		     workbook.createSheet(sheetName);
		sheet=workbook.getSheet(sheetName);                                     //capture the sheet
	
		if(sheet.getRow(rownum)==null)                                       //if row doesnt exist
			sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}
	
	
	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException
	{
		fi =new FileInputStream(path);
		workbook =new XSSFWorkbook(fi);	
		sheet=workbook.getSheet(sheetName);
		row =sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}
	
	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException
	{
		fi =new FileInputStream(path);
		workbook =new XSSFWorkbook(fi);	
		sheet=workbook.getSheet(sheetName);
		row =sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}


	
	
	
}
