package digitalCookBook;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Recipe {

	private long recipeID;

	private String recipeName;

	private int serveAmount;

	private long cookingTime;

	private long preparationTime;

	private String areaName;
	
	private int stepIndex;

	// private Area area;

	private List<Ingredient> requiredIngredients = new ArrayList<Ingredient>();

	private List<PreparationStep> preparationSteps = new ArrayList<PreparationStep>();

	public Recipe() {
		// TODO Auto-generated constructor stub
	}

	Recipe(String nameRe, String areaName, int serveAmount) {

		this.recipeName = nameRe;

		this.areaName = areaName;

		this.serveAmount = serveAmount;

	}

	public long getIdRecipe() {

		return recipeID;

	}

	public void setIdRecipe(long idRecipe) {

		this.recipeID = idRecipe;

	}

	public String getRecipeName() {

		return recipeName;

	}

	public void setRecipeName(String name) {

		this.recipeName = name;

	}

	public String getAreaName() {

		return areaName;

	}

	public void setAreaName(String areaName) {

		this.areaName = areaName;

	}
	
	public int getAreaID() {
		return CookBook.getAreas().get(areaName).getIdArea();
	}

	public int getServeAmount() {

		return serveAmount;

	}

	public void setServeAmount(int serveAmount) {

		this.serveAmount = serveAmount;

	}

	public long getCookingTime() {

		return cookingTime;

	}

	public void setCookingTime(long cookingTime) {

		this.cookingTime = cookingTime;

	}

	public long getPreparationTime() {

		return preparationTime;

	}

	public void setPreparationTime(long preparationTime) {

		this.preparationTime = preparationTime;

	}

	public List<PreparationStep> getPreparationSteps() {

		return preparationSteps;

	}

	public void setPreparationStep(List<PreparationStep> preparationSteps) {

		this.preparationSteps = preparationSteps;

	}

	public void addPreparationStep(int index, PreparationStep step) {

		preparationSteps.add(index, step);

	}

	public void addPreparationStep(PreparationStep step) {

		preparationSteps.add(step);

	}

	public void deletePreparationStep(int index) throws ClassNotFoundException, SQLException {

		PreparationStep step = preparationSteps.get(index - 1);

		preparationSteps.remove(index);

		CookBookDB.deletePreparationDB(step);

	}

	public void deletePreparationStep(PreparationStep preparation) throws ClassNotFoundException, SQLException {

		preparationSteps.remove(preparation);

		CookBookDB.deletePreparationDB(preparation);

	}

	public void deletePreparationStep(String preparation) throws ClassNotFoundException, SQLException {

		int size = preparationSteps.size();

		for (int i = size - 1; i >= 0; i--) {

			if (preparationSteps.get(i).getContent().equals(preparation)) {
				
				CookBookDB.deletePreparationDB(preparationSteps.get(i));

				preparationSteps.remove(preparationSteps.get(i));
			}
		}

	}

	public void setRequiredIngredients(List<Ingredient> ingredient) {
		
		this.requiredIngredients = ingredient;
		
	}

	public List<Ingredient> getRequiredIngredients() {

		return requiredIngredients;

	}

	public void addIngredient(Ingredient ingredient) {

		requiredIngredients.add(ingredient);


	}

	public void deleteIngredients(String ingredient) throws ClassNotFoundException, SQLException {

		int size = requiredIngredients.size();

		for (int i = size - 1; i >= 0; i--) {

			if (requiredIngredients.get(i).getIngredientName().equals(ingredient)) {

				requiredIngredients.remove(requiredIngredients.get(i));

				CookBookDB.deleteIngredientDB(requiredIngredients.get(i));

			}

		}

	}

	/**
	 * 
	 * deleteIngredients function change the input parameter as Ingredient
	 * 
	 * 
	 * 
	 * @param ingredient
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */

	public void deleteIngredients(Ingredient ingredient) throws ClassNotFoundException, SQLException {

		requiredIngredients.remove(ingredient);

		CookBookDB.deleteIngredientDB(ingredient);

	}

	public void setIngredientAmount() {

		Iterator<Ingredient> iterator = requiredIngredients.iterator();

		while (iterator.hasNext()) {

			Ingredient ingre = iterator.next();

			ingre.setServeAmount(serveAmount);

			ingre.setTotalAmount();

		}

	}

	public String toString() {
		int length = this.getRequiredIngredients().size();
		String ing = "";
		if(length <= 5) {
			for(int i = 0; i<4; i++) {
				ing = ing + this.getRequiredIngredients().get(i).getIngredientName() + ", ";
			}
			ing = ing + this.getRequiredIngredients().get(4).getIngredientName();
			return ing;
			
		} else {
			for(int j = 0; j<5; j++) {
				ing = ing + this.getRequiredIngredients().get(j).getIngredientName() + ", ";
			}
			ing = ing + this.getRequiredIngredients().get(5).getIngredientName() + "...";
			return ing;	
		}
		/*
		this.setIngredientAmount();

		String descriptRe = "";

		String ingredientDes = "";

		String preparationDes = "";

		int orderIng = 1;

		int orderPre = 1;

		for (Ingredient ingredient : requiredIngredients) {

			ingredient.getTotalAmount();

			ingredientDes = ingredientDes + orderIng + ". " + ingredient.getIngredientName() + "  " + "Amount: "
					+ ingredient.getTotalAmount() + "  " + "Unit: " + ingredient.getUnit();

			if (ingredient.getDescription() != null) {

				ingredientDes = ingredientDes + "  " + "Description: " + ingredient.getDescription();

			}

			ingredientDes = ingredientDes + "\n";

			orderIng++;

		}

		for (PreparationStep step : preparationSteps) {

			preparationDes = preparationDes + orderPre + ". " + step.getContent() + "\n";

			orderPre++;

		}

		descriptRe = "Recipe Name: " + this.recipeName + "\n" + "ServeAmount: " + this.serveAmount + "\n"
				+ "Ingredients: " + "\n" + ingredientDes + "\n" + "preparationStep:" + "\n" + preparationDes + "\n"
				+ "preparationTime: " + this.preparationTime + "\n" + "cookingTime: " + this.cookingTime;

		return descriptRe;
	*/
	}

}
