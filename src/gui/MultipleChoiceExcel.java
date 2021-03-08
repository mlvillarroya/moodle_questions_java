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

public class MultipleChoiceExcel extends Excel {
	
	private List<MultipleChoiceQuestion> multipleChoiceQuestions;
	
	//Constructor
	MultipleChoiceExcel(FileInputStream fis){
		super(fis);
		List<MultipleChoiceQuestion> multipleChoiceQuestions = new ArrayList<MultipleChoiceQuestion>();
		//COMPOSITION OF TRUEFALSE SHEET: QUESTION* - CORRECTANSWER* - CORRECTFEEDBACK - INCORRECTANSWER1* - INCORRECTFEEDBACK1 - INCORRECTANSWER2 - INCORRECTFEEDBACK2 - INCORRECTANSWER3 - INCORRECTFEEDBACK3 - SUBCATEGORY
		XSSFSheet sheetMCQ = wb.getSheet("MultipleChoice");     //creating a Sheet object to retrieve object  
		Iterator<Row> itr = sheetMCQ.iterator();    //iterating over excel file
		itr.next(); //DISCARD FIRST ROW
		int n=2;
		//System.out.println("\n==== LOADING MULTIPLE CHOICE QUESTIONS ====");
		logSuccess = "";
		createdNumber = 0;
		logErrors = "";
		errorNumber = 0;
		while (itr.hasNext())  
		{  
			Row row = itr.next();
			if (row.getCell(0) == null | row.getCell(1) == null | row.getCell(3) == null) {	
				if (logErrors == "") logErrors += "== Multiple choice questions errors ==\n";
				errorNumber++;
				logErrors += "Error at row " + n +", discarding\n";
				continue;
			}
			//LOAD INCORRECT ANSWERS AND INCORRECT FEEDBACK
			String incorrectAnswers[] = new String[3];
			incorrectAnswers[0] = row.getCell(3).getStringCellValue();
			if (row.getCell(5) != null) incorrectAnswers[1] = row.getCell(5).getStringCellValue();
			if (row.getCell(7) != null) incorrectAnswers[2] = row.getCell(7).getStringCellValue();
			String incorrectFeedbacks[] = new String[3];
			if (row.getCell(4) != null | row.getCell(6) != null | row.getCell(8) != null) {
				if (row.getCell(4) != null) incorrectFeedbacks[0] = row.getCell(4).getStringCellValue(); else incorrectFeedbacks[0]="";
				if (row.getCell(8) != null) incorrectFeedbacks[1] = row.getCell(6).getStringCellValue(); else incorrectFeedbacks[1]="";
				if (row.getCell(8) != null) incorrectFeedbacks[2] = row.getCell(8).getStringCellValue(); else incorrectFeedbacks[2]="";
			}
			//QUESTION
			MultipleChoiceQuestion question = new MultipleChoiceQuestion(row.getCell(0).getStringCellValue(),row.getCell(1).getStringCellValue(),incorrectAnswers);
			if (row.getCell(2) != null) question.setCorrectFeedback(row.getCell(2).getStringCellValue());
			if (incorrectFeedbacks != null) question.setIncorrectFeedbacks(incorrectFeedbacks);
			if (n == 2) question.setMainCategory(generalCategory); //FIRST ROW ALWAYS SHOWS CATEGORY
			if ((row.getCell(9) != null)) {
				question.setMainCategory(generalCategory);
				question.setSubCategory(row.getCell(9).getStringCellValue());
			}
			logSuccess += "Row " + n +", added correctly\n";
			createdNumber++;
			n++;
			multipleChoiceQuestions.add(question);
		} 
		this.multipleChoiceQuestions = multipleChoiceQuestions;
	}
	
	public String printMCGIFT() {
		String MCGIFT="";
		for (MultipleChoiceQuestion question : multipleChoiceQuestions) {
			MCGIFT += question.printGIFT() + "\n";
		}
		return MCGIFT;
	}
}
