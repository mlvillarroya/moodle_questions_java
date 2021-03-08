package gui;

import java.util.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Gui_master {

	public static void main(String[] args) {
		MainFrame frame = new MainFrame("Moodle Questions creator");
		/*try  
		{  
			File file = new File(".\\prova.xlsx");  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  		
			TrueFalseExcel trueFalseExcel = new TrueFalseExcel(fis);
			
			file = new File(".\\prova.xlsx");  
			fis = new FileInputStream(file);   //obtaining bytes from the file  
			MultipleChoiceExcel multipleChoiceExcel = new MultipleChoiceExcel(fis);

			file = new File(".\\prova.xlsx");  
			fis = new FileInputStream(file);   //obtaining bytes from the file  
			ShortAnswerExcel shortAnswerExcel = new ShortAnswerExcel(fis);
			
			System.out.println(trueFalseExcel.printTFGIFT());
			System.out.println(multipleChoiceExcel.printMCGIFT());
			System.out.println(shortAnswerExcel.printSAGIFT());


		}  
		catch(IOException e)  
		{  
			e.printStackTrace();  
		}  
		*/
	}

}


