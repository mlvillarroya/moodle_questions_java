package gui;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.ArrayList;

public class TrueFalseExcel extends Excel {
	
	private List<TrueFalseQuestion> trueFalseQuestions;
	
	//Constructor
	public TrueFalseExcel(FileInputStream fis){
		super(fis);
		List<TrueFalseQuestion> trueFalseQuestions = new ArrayList<TrueFalseQuestion>();
		//COMPOSITION OF TRUEFALSE SHEET: QUESTION* - ANSWER* - INCORRECTFEEDBACK - CORRECTFEEDBACK
		XSSFSheet sheetTF = wb.getSheet("TrueFalse");     //creating a Sheet object to retrieve object  
		Iterator<Row> itr = sheetTF.iterator();    //iterating over excel file
		itr.next(); //DISCARD FIRST ROW
		int n=2;
		//System.out.println("==== LOADING TRUE/FALSE QUESTIONS ====");
		logSuccess = "";
		createdNumber = 0;
		logErrors = "";
		errorNumber = 0;		
		while (itr.hasNext())  
		{  
			Row row = itr.next();
			if (row.getCell(0) == null | row.getCell(1) == null) {
				if (logErrors == "") logErrors += "== True False questions errors ==\n";
				errorNumber++;
				logErrors += "Error at row " + n +", discarding\n";
				continue;
			}
			TrueFalseQuestion question = new TrueFalseQuestion(row.getCell(0).getStringCellValue(),row.getCell(1).getStringCellValue());
			if (row.getCell(2) != null) question.setIncorrectFeedback(row.getCell(2).getStringCellValue());
			if (row.getCell(3) != null) question.setCorrectFeedback(row.getCell(3).getStringCellValue());
			if (n == 2) question.setMainCategory(generalCategory); //FIRST ROW ALWAYS SHOWS CATEGORY
			if ((row.getCell(4) != null)) {
				question.setMainCategory(generalCategory);
				question.setSubCategory(row.getCell(4).getStringCellValue());
			}
			logSuccess += "Row " + n +", added correctly\n";
			createdNumber++;
			n++;
			trueFalseQuestions.add(question);
		} 
		this.trueFalseQuestions = trueFalseQuestions;
	}
	
	public String printTFGIFT() {
		String TFGIFT = "";
		for (TrueFalseQuestion question : trueFalseQuestions) {
			TFGIFT += question.printGIFT() + "\n";
		}
		return TFGIFT;
	}
}
