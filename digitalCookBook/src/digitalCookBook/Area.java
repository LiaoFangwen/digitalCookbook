package digitalCookBook;

import java.util.ArrayList;

//import java.util.Iterator;
import java.util.List;

public class Area {
	private int areaID;
	private String areaName;
	private List<String> recipeNameList = new ArrayList<String>();
	
	
	public Area(/*int id, */String name){
		//this.idArea = id;
		this.areaName = name;
	}
	public Area(int id, String name){
		this.areaID = id;
		this.areaName = name;
	}
	
	/**
	 * add the recipe which belongs to this area to this area
	 * @param recipe
	 */
	public void addRecipe(Recipe recipe) {
		recipeNameList.add(recipe.getRecipeName());
	}
	
	public int getAreaID() {

		return areaID;
	}
	
	public void setIdArea(int idArea) {
		this.areaID = idArea;
	}
		 
	public String getAreaName() {
		return areaName;
	}
	
	public void setAreaName(String nameArea) {
		this.areaName = nameArea;
	}
	
	/**
	 * return the recipes belong to this area
	 * @return the list of the recipes
	 */
	public List<String> getAreaRecipe() {
		return recipeNameList;
	}
	
	public void setAreaRecipe(List<String> recipeNameList) {
		this.recipeNameList = recipeNameList;
	}
	
	
}
