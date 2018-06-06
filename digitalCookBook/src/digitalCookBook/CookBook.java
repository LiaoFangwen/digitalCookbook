package digitalCookBook;
import java.util.*;
public class CookBook {
	private String cbName;
	private Map<String, Recipe> recipeBook = new HashMap<String, Recipe>();
	private Map<String, Area> areaBook = new HashMap<String, Area>();
	
	public CookBook(String name){
		this.cbName = name;
	}
	
	public void displayCookBookName() {
		System.out.println("This is " + cbName + "!");
	}
	
	/**
	 * add a new recipe to a cook book
	 * @param recipe the recipe to add
	 */
	public void add(Recipe recipe) {
		
		recipeBook.put(recipe.getRecipeName(), recipe);
		String areaName = recipe.getAreaName();
		createNewArea(areaName);
		searchArea(areaName).addRecipe(recipe);
	}
	
	/**
	 * search recipe by its name
	 * @param name the name for search
	 * @return the searched recipe
	 */
	public Recipe getRecipeByName(String name) {		
		return recipeBook.get(name);
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
	
	/**
	 * delete a recipe from the cook book
	 * @param recipe the recipe for delete
	 */
	public void deleteRecipe(Recipe recipe) {
		recipe.getArea().getAreaRecipe().remove(recipe.getRecipeName());
		recipeBook.remove(recipe.getRecipeName());
		recipe = null;
		System.out.println("Recipe deleted!");
	}
	
	/**
	 * delete a area from the cook book
	 * @param area the area for delete
	 */
	public void deleteArea(Area area) {
		Recipe recipe = null;
		Iterator<String> iterator = area.getAreaRecipe().iterator();
		while(iterator.hasNext()) {
			recipe = getRecipeByName(iterator.next());
			recipe.setArea(new Area("Unknown"));
			recipe.setAreaName("Unknown");
		}
		area = null;
		System.out.println("Area deleted!");
	}
	
	/**
	 * change the area of a recipe
	 * @param recipe the recipe for change 
	 * @param newName the new name of the desired area
	 */
	public void changeRecipeArea(Recipe recipe, String newName) {
		Area area = recipe.getArea();
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
		return areaBook.get(searchName);
	}
	
	/**
	 * determine whether an area exists
	 * @param newAreaName the area name for determine
	 * @return whether the area exists
	 */
	public boolean areaExists(String newAreaName) {
		return areaBook.containsKey(newAreaName);
	}
	
	/**
	 * create a new area for the cook book, if the area already exists, then nothing happens
	 * @param newAreaName the area for create
	 */
	public void createNewArea(String newAreaName) {
		
		if(!areaExists(newAreaName)) {
			Area newArea = new Area(newAreaName);
			areaBook.put(newAreaName, newArea);
		}		
	}
	
}