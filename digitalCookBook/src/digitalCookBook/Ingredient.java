package digitalCookBook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ingredient {
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
		String newName = this.ingredientName.replaceAll(" ", "");
		Pattern pattern = Pattern.compile(newName,Pattern.CASE_INSENSITIVE);
		Matcher matcherWater = pattern.matcher("water");
		Matcher matcherSauce = pattern.matcher("sauce");
		Matcher matcherOil = pattern.matcher("oil");
		Matcher matcherWine = pattern.matcher("wine");
		Matcher matcherBeer = pattern.matcher("beer");
		Matcher matcherVinegar = pattern.matcher("vinegar");
		Matcher matcherStock = pattern.matcher("stock");

		if(matcherWater.find()||matcherSauce.find()||matcherOil.find()||matcherWine.find()||matcherBeer.find()||matcherVinegar.find()||matcherStock.find()) {
		newAmount = (quantity/2.0)*0.7;
		}
		newAmount = (quantity / 2.0) * serveAmount;

		//newAmount = (quantity / 2.0) * serveAmount;
		
	}

	public double getTotalAmount() {

		return newAmount;

	}

}
