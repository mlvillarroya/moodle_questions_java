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

public class ShortAnswerExcel extends Excel {
	
	private List<ShortAnswerQuestion> ShortAnswerQuestions;
	
	//Constructor
	ShortAnswerExcel(FileInputStream fis){
		super(fis);
		List<ShortAnswerQuestion> ShortAnswerQuestions = new ArrayList<ShortAnswerQuestion>();
		//COMPOSITION OF TRUEFALSE SHEET: QUESTION* - ANSWER* - ALTERNATIVEANSWER1 - ALTERNATIVEANSWER2 - ALTERNATIVEANSWER3 - GENERALFEEDBACK - SUBCATEGORY
		XSSFSheet sheetSA = wb.getSheet("ShortAnswer");     //creating a Sheet object to retrieve object  
		Iterator<Row> itr = sheetSA.iterator();    //iterating over excel file
		itr.next(); //DISCARD FIRST ROW
		int n=2;
		//System.out.println("\n==== LOADING SHORT ANSWER QUESTIONS ====");
		logSuccess = "";
		createdNumber = 0;
		logErrors = "";
		errorNumber = 0;
		while (itr.hasNext())  
		{  
			Row row = itr.next();
			if (row.getCell(0) == null | row.getCell(1) == null) {
				if (logErrors == "") logErrors += "== Short answer questions errors ==\n";
				errorNumber++;
				logErrors += "Error at row " + n +", discarding\n";
				n++;
				continue;
			}
			//LOAD CORRECT ANSWERS
			String correctAnswers[] = new String[4];
			correctAnswers[0] = row.getCell(1).getStringCellValue();
			if (row.getCell(2) != null) correctAnswers[1] = row.getCell(2).getStringCellValue();
			if (row.getCell(3) != null) correctAnswers[2] = row.getCell(3).getStringCellValue();
			if (row.getCell(4)  != null) correctAnswers[3] = row.getCell(4).getStringCellValue();
			//QUESTION
			ShortAnswerQuestion question = new ShortAnswerQuestion(row.getCell(0).getStringCellValue(),correctAnswers);
			if (row.getCell(5) != null) question.setCorrectFeedback(row.getCell(5).getStringCellValue());
			if (n == 2) question.setMainCategory(generalCategory); //FIRST ROW ALWAYS SHOWS CATEGORY
			if ((row.getCell(6) != null)) {
				question.setMainCategory(generalCategory);
				question.setSubCategory(row.getCell(6).getStringCellValue());
			}
			logSuccess += "Row " + n +", added correctly\n";
			createdNumber++;
			n++;
			ShortAnswerQuestions.add(question);
		} 
		this.ShortAnswerQuestions = ShortAnswerQuestions;
	}
	
	public String printSAGIFT() {
		String SAGIFT = "";
		for (ShortAnswerQuestion question : ShortAnswerQuestions) {
			SAGIFT += question.printGIFT() + "\n";
		}
		return SAGIFT;
	}
}
