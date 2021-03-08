package gui;

public class ShortAnswerQuestion extends Question{
	private String[] correctAnswers;
	private String correctFeedback;
	
	ShortAnswerQuestion(String questionText, String[] correctAnswers){
		super(questionText);
		this.correctAnswers = correctAnswers;
	}
	
	public void setCorrectFeedback(String correctFeedback) {
		this.correctFeedback = correctFeedback;
	}
	
	@Override
	public String printGIFT() {
		String gift = super.printGIFT();
		gift += "\n";
		for (String s : correctAnswers ) {
			if (s != null) gift += "=%100%" + s + " ";
		}
		if (correctFeedback != null) gift += "\n####" + correctFeedback + "\n";
		gift += "}\n";
		return gift;
	}
}
