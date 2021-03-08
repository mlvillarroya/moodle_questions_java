package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

import javax.imageio.IIOException;

import java.util.ArrayList;


public abstract class Excel {
	protected XSSFWorkbook wb;
	protected String generalCategory;
	protected String logSuccess;
	protected String logErrors;
	protected int createdNumber;
	protected int errorNumber;
	
	Excel(FileInputStream fis){
		try {
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			this.wb = wb;
			loadOptions();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void loadOptions(){
		String generalCategory = null;
		XSSFSheet sheetG = wb.getSheet("General");     //creating a Sheet object to retrieve object  
		if (sheetG.getRow(1).getCell(0) != null) {
			generalCategory = sheetG.getRow(1).getCell(0).getStringCellValue();
			if (sheetG.getRow(1).getCell(1) != null) generalCategory += "/" + sheetG.getRow(1).getCell(1).getStringCellValue();
		}
		else {
			System.out.println("Error, category must be defined in Excel's \"General\" Sheet");
		}
		this.generalCategory = generalCategory;

	}
	public String getLogSuccess() {
		return logSuccess;
	}
	public String getLogErrors() {
		return logErrors;
	}
	public int getCreatedNumber() {
		return createdNumber;
	}
	public int getErrorNumber() {
		return errorNumber;
	}
}
