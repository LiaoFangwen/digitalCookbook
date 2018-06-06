package digitalCookBook;
public class PreparationStep {
	int preparationStepID; 
	private String content;
	
	public PreparationStep(int id, String content){
		this.preparationStepID = id;
		this.content = content;
	}
	public PreparationStep(String content) {
		this.content = content;
	}
	
	public int getPreparationStepID() {
		return preparationStepID;
	}
	
	public void setPreparationStepID(int idPreparation) {
		this.preparationStepID = idPreparation;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String name) {
		this.content = name;
	}
}
