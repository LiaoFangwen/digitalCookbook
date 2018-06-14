package digitalCookBook;
public class Ingredient {

	// private int ingredientID;

	// private static int defaultID = 1;

	private long recipeID;

	private String ingredientName;

	private double quantity = 0;

	private double newAmount = 0;

	private String unit;

	private String description = null;

	private int serveAmount = 2;

	Ingredient() {

	}

	Ingredient(String nameIng, double amount, String unit, String description) {

		this.ingredientName = nameIng;

		this.quantity = amount;

		this.unit = unit;

		this.description = description;

	}

	Ingredient(String nameIng, double amount, String unit) {

		// ingredientID = defaultID;

		this.ingredientName = nameIng;

		this.quantity = amount;

		this.unit = unit;
	}

	public long getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(long recipeid) {
		this.recipeID = recipeid;
	}

	/*
	 * public int getIdIngredient() {
	 * 
	 * return ingredientID;
	 * 
	 * }
	 * 
	 * public void setIdIngredient(int idIngredient) {
	 * 
	 * this.ingredientID = idIngredient;
	 * 
	 * }
	 */

	public String getIngredientName() {

		return ingredientName;

	}

	public void setIngredientName(String name) {

		this.ingredientName = name;

	}

	public double getQuantity() {

		return quantity;

	}

	public void setQuantity(double amount) {

		this.quantity = amount;

	}

	public String getUnit() {

		return unit;

	}

	public void setUnit(String unit) {

		this.unit = unit;

	}

	public String getDescription() {

		return description;

	}

	public void setDescription(String description) {

		this.description = description;

	}

	public int getServeAmount() {

		return serveAmount;

	}

	public void setServeAmount(int serveAmount) {

		this.serveAmount = serveAmount;

	}

	public void setTotalAmount() {

		newAmount = (quantity / 2.0) * serveAmount;

	}

	public double getTotalAmount() {

		return newAmount;

	}

}
