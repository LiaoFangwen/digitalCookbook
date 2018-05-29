package digitalCookBook;
public class PreparationStep {
	//int preparationStepID; 
	String content;
	
	PreparationStep(/*int id,*/ String content){
		//this.idPreparation = id;
		this.content = content;
	}
	/*
	public int getPreparationID() {
		return preparationID;
	}
	
	public void setIdPreparation(int idPreparation) {
		this.preparationID = idPreparation;
	}
	*/
	public String getContent() {
		return content;
	}
	
	public void setContent(String name) {
		this.content = name;
	}
}
