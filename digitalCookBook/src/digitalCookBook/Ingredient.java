package digitalCookBook;
public class Ingredient {
	//private int ingredientID;
	private String ingredientName;
	private double defaultAmount = 0;
	private double newAmount;
	private String unit;
	private String description = null;
	private int serveAmount = 2;
	
	Ingredient(/*int ingredientID, */String nameIng, double amount, String unit, String description){
		//this.idIngredient = idIngredient;
		this.ingredientName = nameIng;
		this.defaultAmount = amount;
		this.unit = unit;
		this.description = description;
	}
	
	Ingredient(/*int ingredientID,*/ String nameIng, double amount, String unit){
		//this.idIngredient = idIngredient;
		this.ingredientName = nameIng;
		this.defaultAmount = amount;
		this.unit = unit;
	}
	/*
	public int getIdIngredient() {
		return ingredientID;
	}
	
	public void setIdIngredient(int idIngredient) {
		this.ingredientID = idIngredient;
	}
	*/
	public String getIngredientName() {
		return ingredientName;
	}
	
	public void setIngredientName(String name) {
		this.ingredientName = name;
	}
	
	public double getAmount() {
		return defaultAmount;
	}
	
	public void setAmount(double amount) {
		this.defaultAmount = amount;
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
	
	public void setTotalAmount( ) {
		newAmount = (defaultAmount/2.0) * serveAmount;
	}
	
	public double getTotalAmount( ) {
		return newAmount;
	}
}
