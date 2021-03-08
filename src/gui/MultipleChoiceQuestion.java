package gui;

public class MultipleChoiceQuestion extends Question{
	private String correctAnswer;
	private String[] incorrectAnswers;
	private String correctFeedback;
	private String[] incorrectFeedbacks;
	
	MultipleChoiceQuestion(String questionText, String correctAnswer, String[] incorrectAnswers){
		super(questionText);
		this.correctAnswer = correctAnswer;
		this.incorrectAnswers = incorrectAnswers;		
	}
	
	public void setCorrectFeedback(String correctFeedback) {
		this.correctFeedback = correctFeedback;
	}

	public void setIncorrectFeedbacks(String[] incorrectFeedbacks) {
		this.incorrectFeedbacks = incorrectFeedbacks;
	}
	
	@Override
	public String printGIFT() {
		String gift = super.printGIFT();
		gift += "\n=" + correctAnswer + "\n";
		if (correctFeedback != null) gift += "#" + correctFeedback + "\n";
		int index = 0;
		for (String s : incorrectAnswers ) {
			if (s != "") {
				gift += "~" + s + "\n";
				if (incorrectFeedbacks[index] != null) gift += "#" + incorrectFeedbacks[index] + "\n";
			}
			index++;
		}
		gift += "}\n";
		return gift;
	}
}
