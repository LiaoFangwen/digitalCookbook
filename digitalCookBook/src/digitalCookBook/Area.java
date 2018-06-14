package digitalCookBook;
import java.sql.SQLException;
import java.util.ArrayList;

//import java.util.Iterator;

import java.util.List;

public class Area {

	private int areaID;

	private String areaName;

	private List<String> recipeNameList = new ArrayList<String>();

	Area() {

	}

	Area(String name) {

		this.areaName = name;
		
	}

	/**
	 * 
	 * add the recipe which belongs to this area to this area
	 * 
	 * @param recipe
	 * 
	 */

	public void addRecipe(Recipe recipe) {

		recipeNameList.add(recipe.getRecipeName());

	}

	public int getIdArea() {

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
	 * 
	 * return the recipes belong to this area
	 * 
	 * @return the list of the recipes
	 * 
	 */

	public List<String> getAreaRecipe() {

		return recipeNameList;

	}

}