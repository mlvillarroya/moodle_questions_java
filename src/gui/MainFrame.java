package gui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame{
	
	private JTextArea terminal;
	private JScrollPane terminal_scroll;
	private FileInputStream fis;
	private FormPanel formPanel;
	private TrueFalseExcel trueFalseExcel;
	private ShortAnswerExcel shortAnswerExcel;
	private MultipleChoiceExcel multipleChoiceExcel;
	
	MainFrame(String name){
		super(name);
		setLayout(new BorderLayout());
		terminal = new JTextArea();
		terminal_scroll = new JScrollPane(terminal);
		formPanel = new FormPanel();
		add(formPanel,BorderLayout.WEST);
		add(new JScrollPane(terminal),BorderLayout.CENTER);
		formPanel.setViewLogDisabled();
		formPanel.setTxtFileCreateDisabled();
		setSize(600,600);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setVisible(true);
		
		formPanel.setFormListener(new FormListener() {

			public void formEventOcurred(FormEvent e) {
				if (e.getFile() != null) {
					try {
						terminal.selectAll();
						terminal.replaceSelection("");
						fis = new FileInputStream(e.getFile());
						trueFalseExcel = new TrueFalseExcel(fis);
						terminal.append("==== True/False Questions ====\n" + trueFalseExcel.getCreatedNumber() + " questions added correctly\n" + trueFalseExcel.getErrorNumber() + " errors found\n\n");
						fis = new FileInputStream(e.getFile());
						shortAnswerExcel = new ShortAnswerExcel(fis);
						terminal.append("==== Short Answer Questions ====\n" + shortAnswerExcel.getCreatedNumber() + " questions added correctly\n" + shortAnswerExcel.getErrorNumber() + " errors found\n\n");
						fis = new FileInputStream(e.getFile());
						multipleChoiceExcel = new MultipleChoiceExcel(fis);
						terminal.append("==== Multiple Choice Questions ====\n" + multipleChoiceExcel.getCreatedNumber() + " questions added correctly\n" + multipleChoiceExcel.getErrorNumber() + " errors found\n\n");
						formPanel.setViewLogEnabled();
					} catch(Exception ex) {
						System.out.println("Error" + ex.getMessage());
					}   //obtaining bytes from the file  
				}
				else if (e.getViewLog() != null) {
					if (e.getViewLog().booleanValue() == true) {
						terminal.selectAll();
						terminal.replaceSelection("");
	
						terminal.append(trueFalseExcel.getLogErrors());
						terminal.append(shortAnswerExcel.getLogErrors());
						terminal.append(multipleChoiceExcel.getLogErrors());
					}
					else {
						terminal.selectAll();
						terminal.replaceSelection("");
						
						terminal.append("==== True/False Questions ====\n" + trueFalseExcel.getCreatedNumber() + " questions added correctly\n" + trueFalseExcel.getErrorNumber() + " errors found\n\n");
						terminal.append("==== Short Answer Questions ====\n" + shortAnswerExcel.getCreatedNumber() + " questions added correctly\n" + shortAnswerExcel.getErrorNumber() + " errors found\n\n");
						terminal.append("==== Multiple Choice Questions ====\n" + multipleChoiceExcel.getCreatedNumber() + " questions added correctly\n" + multipleChoiceExcel.getErrorNumber() + " errors found\n\n");

					}
				}
				else if (e.getExportRoute() != null) {
					System.out.println(e.getExportRoute());
					try {
					      File giftFile = new File(e.getExportRoute() + "\\questions.txt");
					      terminal.selectAll();
					      terminal.replaceSelection("");
					      if (giftFile.createNewFile()) {
					        terminal.append("File created: " + giftFile.getName() + "\n");
					      } else {
					        terminal.append("File " + giftFile.getName() + " already exists and will be erased\n");
					      }
						    try {
						        FileWriter giftFileWriter = new FileWriter(giftFile);
						        String buffer = "";
						        if (trueFalseExcel != null) buffer += trueFalseExcel.printTFGIFT() + "\n";
						        if (multipleChoiceExcel != null) buffer += multipleChoiceExcel.printMCGIFT() + "\n";
						        if (shortAnswerExcel != null) buffer += shortAnswerExcel.printSAGIFT() + "\n";
						        giftFileWriter.write(buffer);
						        terminal.append("Questions writed succesfully\n");
						        giftFileWriter.close();
						      } catch (IOException exc) {
						        terminal.append("An error occurred.\n");
						        exc.printStackTrace();
						      }
					    } catch (IOException ex) {
					      terminal.append("An error occurred.\n");
					      ex.printStackTrace();
					    }
			
					  }
			
				
			}

			
		});
	}
}
