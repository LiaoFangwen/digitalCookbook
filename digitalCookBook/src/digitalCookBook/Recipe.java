package digitalCookBook;
import java.util.*;

public class Recipe {
	//private int recipeID;
	private String recipeName;
	private int serveAmount;
	private int cookingTime;
	private int preparationTime;
	private String areaName;
	private Area area;
	private List<Ingredient> requiredIngredients = new ArrayList<Ingredient>();
	private List<PreparationStep> preparationSteps = new ArrayList<PreparationStep>();

	Recipe(/*int idRecipe, */String nameRe, String areaName, int serveAmount) {
		//this.idRecipe = idRecipe;
		this.recipeName = nameRe;
	    this.areaName = areaName;
		this.serveAmount = serveAmount;
	}

	/*
	 * public int getIdRecipe() {

		return recipeID;
	}

	public void setIdRecipe(int idRecipe) {
		this.recipeID = idRecipe;
	}
*/
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

	public int getServeAmount() {
		return serveAmount;
	}

	public void setServeAmount(int serveAmount) {
		this.serveAmount = serveAmount;
	}

	public int getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

	public int getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
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
	
	public void deletePreparationStep(int index) {
		preparationSteps.remove(index);
	}
	
	public void deletePreparationStep(PreparationStep preparation) {
		preparationSteps.remove(preparation);
	}
	
	public void deletePreparationStep(String preparation) {
		int size = preparationSteps.size();
		for (int i = size - 1; i >= 0; i--) {
			if (preparationSteps.get(i).getContent().equals(preparation)) {
				preparationSteps.remove(preparationSteps.get(i));
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	

	public List<Ingredient> getRequiredIngredients() {
		return requiredIngredients;
	}

	public void addIngredient(Ingredient ingredient) {
		requiredIngredients.add(ingredient);
	}

	public void deleteIngredients(String ingredient) {
		int size = requiredIngredients.size();
		for (int i = size - 1; i >= 0; i--) {
			if (requiredIngredients.get(i).getIngredientName().equals(ingredient)) {
				requiredIngredients.remove(requiredIngredients.get(i));
			}
		}
	}

	/**
	 * deleteIngredients function change the input parameter as Ingredient
	 * 
	 * @param ingredient
	 */
	public void deleteIngredients(Ingredient ingredient) {
		requiredIngredients.remove(ingredient);
	}

	

	
	
	
	
	
	
	
	
	public void setIngredientAmount() {
		Iterator <Ingredient> iterator = requiredIngredients.iterator();
		while(iterator.hasNext()) {
			Ingredient ingre = iterator.next();
			ingre.setServeAmount(serveAmount);
			ingre.setTotalAmount();
		}
		
	}
	
	
	
	
	
	
	public String toString() {
		this.setIngredientAmount();
		String descriptRe="";
		String ingredientDes = "";
		String preparationDes = "";
		int orderIng = 1;
	    int orderPre = 1;
		for(Ingredient ingredient:requiredIngredients) {
			ingredient.getTotalAmount();
			ingredientDes = ingredientDes + orderIng + ". " + ingredient.getIngredientName() + "  " + "Amount: " + ingredient.getTotalAmount() + "  " + "Unit: " +ingredient.getUnit();
			if(ingredient.getDescription() != null) {
				ingredientDes = ingredientDes + "  " +"Description: " + ingredient.getDescription();
			}
			ingredientDes = ingredientDes + "\n";
			orderIng++;
		}
		
		for(PreparationStep step:preparationSteps) {
			preparationDes = preparationDes + orderPre + ". " + step.getContent()+"\n";
			orderPre++;
		}
		
		descriptRe = "Recipe Name: " + this.recipeName + "\n" +  "ServeAmount: " + this.serveAmount + "\n" + "Ingredients: "+ "\n" + ingredientDes + "\n" + "preparationStep:" + "\n" + preparationDes + "\n" + "preparationTime: " + this.preparationTime + "\n" + "cookingTime: " + this.cookingTime;
		
		return descriptRe;
	}
}
