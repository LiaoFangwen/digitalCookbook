package digitalCookBook;
public class Ingredient {
	private int ingredientID;
	private String ingredientName;
	private double defaultAmount = 0;
	private double newAmount;
	private String unit;
	private String description = null;
	private int serveAmount = 2;
	
	public Ingredient(int ingredientID, String IngredientName, double amount, String unit, String description, int serveAmount){
		this.ingredientID = ingredientID;
		this.ingredientName = IngredientName;
		this.newAmount = amount;
		this.unit = unit;
		this.description = description;
		this.serveAmount = serveAmount;
	}
	
	public Ingredient(String name, double amount, String unit, String description) {
		this.ingredientName = name;
		this.newAmount = amount;
		this.unit = unit;
		this.description = description;
	}
	
	
	public Ingredient(/*int ingredientID,*/ String nameIng, double amount, String unit){
		//this.idIngredient = idIngredient;
		this.ingredientName = nameIng;
		this.newAmount = amount;
		this.unit = unit;
	}
	
	public int getIngredientID() {
		return ingredientID;
	}
	
	public void setIngredientID(int idIngredient) {
		this.ingredientID = idIngredient;
	}
	
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
