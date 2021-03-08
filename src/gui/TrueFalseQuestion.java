package gui;

public class TrueFalseQuestion extends Question{
	private String correctAnswer;
	private String correctFeedback;
	private String incorrectFeedback;
	
	TrueFalseQuestion(String questionText, String correctAnswer){
		super(questionText);
		this.correctAnswer = correctAnswer;	
	}
	
	public void setCorrectFeedback(String correctFeedback) {
		this.correctFeedback = correctFeedback;
	}

	public void setIncorrectFeedback(String incorrectFeedback) {
		this.incorrectFeedback = incorrectFeedback;
	}
	
	@Override
	public String printGIFT() {
		String gift = super.printGIFT();
		if (correctAnswer.toLowerCase().equals("t") | correctAnswer.toLowerCase().equals("true")) gift += "T";
		else gift += "F";
		if (incorrectFeedback != null) {
			gift += "#" + incorrectFeedback;
			if (correctFeedback != null) {
				gift += "#" + correctFeedback;
			}
		}
		gift += "}\n";
		return gift;
	}
}
