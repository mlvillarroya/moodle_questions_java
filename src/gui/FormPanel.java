package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FormPanel extends JPanel implements ActionListener{
	private JLabel generalLabel;
	private JLabel fillExcelLabel;
	private JLabel loadExcelLabel;
	private JButton loadExcelButton;
	private JFileChooser fc;	
	private JFileChooser fc2;
	private JCheckBox viewLog;
	private JLabel giftPathLabel;
	private JButton giftPathBrowse;
	private JButton txtFileCreate;
	private FormListener formListener;
	
	FormPanel(){
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		generalLabel = new JLabel("Moodle question generator");
		fillExcelLabel = new JLabel("Fill the Excel file present in the main folder");
		loadExcelLabel = new JLabel("Load filled Excel");
		loadExcelButton = new JButton("Browse");
		fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("Excel files", "xlsx"));
		fc2 = new JFileChooser();
		fc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		viewLog = new JCheckBox("View error log");
		giftPathLabel = new JLabel("GIFT file output path: ");
		giftPathBrowse = new JButton("Browse");
		txtFileCreate = new JButton("Create GIFT file");
		layoutComponents();
		
		loadExcelButton.addActionListener(this);
		viewLog.addActionListener(this);
		giftPathBrowse.addActionListener(this);
		txtFileCreate.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadExcelButton) {
			int returnVal = fc.showOpenDialog(FormPanel.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				loadExcelLabel.setText(file.getAbsolutePath());
				
				FormEvent ev = new FormEvent(this, file, null,null);
				
				if (formListener != null) {
					formListener.formEventOcurred(ev);
				}
			}
		}
		else if (e.getSource() == giftPathBrowse) {
			int returnVal = fc2.showOpenDialog(FormPanel.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc2.getSelectedFile();
				giftPathLabel.setText(file.getAbsolutePath());
				setTxtFileCreateEnabled();
				
				}
			}
		
		else if (e.getSource() == viewLog) {
			FormEvent ev = new FormEvent(this, null, viewLog.isSelected(),null);
			if (formListener != null) {
				formListener.formEventOcurred(ev);
			}
		}
		else if (e.getSource() == txtFileCreate) {
			FormEvent ev = new FormEvent(this, null, null, giftPathLabel.getText());
			if (formListener != null) {
				formListener.formEventOcurred(ev);
			}				
		}
	}
	
	public void setViewLogDisabled() {
		viewLog.setEnabled(false);
	}
	public void setViewLogEnabled() {
		viewLog.setEnabled(true);
	}

	public void setTxtFileCreateDisabled() {
		txtFileCreate.setEnabled(false);
	}
	public void setTxtFileCreateEnabled() {
		txtFileCreate.setEnabled(true);
	}
	
	public void switchViewLogButton() {
		if (viewLog.isEnabled()) viewLog.setEnabled(false);
		else viewLog.setEnabled(true);
	}
	
	public void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// ////////// First row ///////////////////////////////////

		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.insets = new Insets(10,0,0,0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.PAGE_START;
		add(generalLabel, gc);

		// ////////// Second row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.CENTER;
		add(fillExcelLabel, gc);


		// ////////// Third row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		add(loadExcelLabel, gc);


		gc.gridx = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(loadExcelButton, gc);

		// ////////// Fourth row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.insets = new Insets(10,0,0,0);
		gc.anchor = GridBagConstraints.PAGE_START;
		add(viewLog, gc);

		// ////////// Fifth row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.PAGE_END;
		add(giftPathLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.PAGE_END;
		add(giftPathBrowse, gc);

		// ////////// Sixth row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2;
		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.insets = new Insets(20,0,0,0);
		gc.anchor = GridBagConstraints.PAGE_START;
		add(txtFileCreate, gc);

	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}
