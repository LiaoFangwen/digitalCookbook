package digitalCookBook;
import java.util.*;
public class CookBook {
	private String cbName;
	private List <Recipe> recipeBook = new ArrayList<Recipe> ();
	private List<Area> areas = new ArrayList<Area>();
	CookBook(String name){
		this.cbName = name;
	}
	
	public void displayCookBookName() {
		System.out.println(cbName);
	}
	
	/**
	 * add new recipe to a cook book
	 * @param recipe the recipe to add
	 */
	public void add(Recipe recipe) {
		recipeBook.add(recipe);
		String areaName = recipe.getAreaName();
		createNewArea(areaName);
		searchArea(recipe.getAreaName()).addRecipe(recipe);
	}
	
	/**
	 * search recipe by its name
	 * @param name the name for search
	 * @return the searched recipe
	 */
	public Recipe getRecipeByName(String name) {
		Recipe recipe = null;
		Iterator <Recipe> iterator = recipeBook.iterator();
		while(iterator.hasNext()) {
			recipe = iterator.next();
			if(recipe.getRecipeName().equals(name)) {
				break;
			}
		}
		return recipe;
	}
	
	/**
	 * search recipes by area and print the list of results
	 * @param areaName the area name for search
	 */
	public void getRecipeByArea(String areaName) {
		Area area = searchArea(areaName);
		List<String> resultList = area.getAreaRecipe();
		Iterator<String> iterator = resultList.iterator();
		while(iterator.hasNext())
			System.out.println(iterator.next());
	}
	
	public void deleteRecipe(Recipe recipe) {
		searchArea(recipe.getAreaName()).getAreaRecipe().remove(recipe.getRecipeName());
		recipeBook.remove(recipe);
		recipe = null;
		System.out.println("Recipe deleted!");
	}
	
	public void deleteArea(Area area) {
		Recipe recipe = null;
		Iterator<String> iterator = area.getAreaRecipe().iterator();
		while(iterator.hasNext()) {
			recipe = getRecipeByName(iterator.next());
			recipe.setAreaName("Unknown");
		}
		area = null;
		System.out.println("Area deleted");
	}
	/**
	 * change the area of a recipe
	 * @param recipe the recipe for change 
	 * @param newName the new name of the desired area
	 */
	public void changeRecipeArea(Recipe recipe, String newName) {
		Area area = searchArea(recipe.getAreaName());
		area.getAreaRecipe().remove(recipe.getRecipeName());
		createNewArea(newName);
		searchArea(newName).addRecipe(recipe);
	}
	
	
	
	
	/**
	 * search the area by its name
	 * @param searchName the area name for search
	 * @return the searched area
	 */
	public Area searchArea(String searchName) {
		String name = null;
		Area area = null;
		Iterator <Area> iterator = areas.iterator();
		while(iterator.hasNext()) {
			area = iterator.next();
			name = area.getAreaName();
			if(name == searchName) {
				break;
			}
		}
		return area;
	}
	
	/**
	 * determine whether an area exists
	 * @param newAreaName the area name for determine
	 * @return whether the area exists
	 */
	public boolean areaExists(String newAreaName) {
		Area area = null;
		boolean exists = false;
		Iterator<Area> iterator = areas.iterator();
		while(iterator.hasNext()) {
			 area = iterator.next();
			if(area.getAreaName() == newAreaName) {
				exists = true;
				break;
			} else
				exists = false;
		}
		return exists;
	}
	
	/**
	 * create a new area for the cook book, if the area already exists, then nothing happens
	 * @param newAreaName the area for create
	 */
	public void createNewArea(String newAreaName) {
		
		if(!areaExists(newAreaName)) {
			Area newArea = new Area(newAreaName);
			areas.add(newArea);
		}		
	}
	
	
	public void printAreas() {
		Iterator<Area> iterator = areas.iterator();
		while(iterator.hasNext()) {
			 System.out.println(iterator.next().getAreaName());
		}
	}
}