package digitalCookBook;
public class PreparationStep {

	private long recipeID;

	private String content;
	
	PreparationStep(){
		
	}

	PreparationStep(String content) {

		this.content = content;
	}

	public long getRecipeID() {

		return recipeID;

	}

	public void setRecipeID(long idPreparation) {

		this.recipeID = idPreparation;

	}

	public String getContent() {

		return content;

	}

	public void setContent(String name) {

		this.content = name;

	}

}
