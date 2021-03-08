package gui;

public abstract class Question {
	//Attributes
	private String questionText;
	private String mainCategory;
	private String subCategory;
	private static int questionID = 1;
	
	//Methods
	Question(String questionText) {
		this.questionText = questionText;		
	}

	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String printGIFT() {
		String gift = "";
		//CATEGORY, IF EXISTS
		if (mainCategory!=null) {
			gift+="$CATEGORY: $course$/" + mainCategory;
			if (subCategory!=null) gift+="/" + subCategory;
			gift+="\n\n";
		}
		//QUESTION ID
		gift+="::Q"+questionID+"_"+questionText.subSequence(0,5)+"::";
		questionID++;
		//QUESTION TEXT
		gift+=questionText+" {";
		return gift;
	}
}
