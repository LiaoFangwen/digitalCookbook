package digitalCookBook;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CookBook {

	private String cbName;

	private static Map<Long, Recipe> recipeBook;

	private static Map<String, Area> areas;
	
	public CookBook(){
	}

	public CookBook(String name) throws ClassNotFoundException, SQLException {

		this.cbName = name;

		recipeBook = CookBookDB.recipelist();

		areas = CookBookDB.areaList();

		CookBookDB.getConnection();

	}

	public void displayCookBookName() {

		System.out.println("This is " + cbName + "!");

	}

	/**
	 * 
	 * add a new recipe to a cook book
	 * 
	 * @param recipe
	 *            the recipe to add
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */

	public static void addRecipe(Recipe recipe) throws ClassNotFoundException, SQLException {
		CookBook.createNewArea(recipe.getAreaName());
		CookBookDB.addRecipeDB(recipe);
		CookBookDB.updateCookingTime(recipe, recipe.getCookingTime());
		CookBookDB.updatePreparationTime(recipe, recipe.getPreparationTime());
		
		Iterator<PreparationStep> iteratorStep = recipe.getPreparationSteps().iterator();
		while(iteratorStep.hasNext()) {
		PreparationStep pre = iteratorStep.next();
		pre.setRecipeID(recipe.getIdRecipe());
		CookBookDB.addPreparationDB(pre);
		}
		
		Iterator<Ingredient> iteratorIngredient = recipe.getRequiredIngredients().iterator();
		while(iteratorIngredient.hasNext()) {
			Ingredient ingredient = iteratorIngredient.next();
			ingredient.setRecipeID(recipe.getIdRecipe());
		}
		
		recipeBook.put(recipe.getIdRecipe(), recipe);
		String areaName = recipe.getAreaName();
		searchArea(areaName).addRecipe(recipe);

	}

	/**
	 * 
	 * search recipe by its keyword
	 * 
	 * @param name
	 *            the name for search
	 * 
	 * @return the searched recipe
	 * 
	 */

	public static List<Recipe> searchRecipeByKeyword(String name) {
		List<Recipe> searchedRecipeList = new ArrayList<Recipe>();
		String newName = name.replaceAll(" ", "");
		Iterator<Entry<Long, Recipe>> recipeBookIt = recipeBook.entrySet().iterator();
		while (recipeBookIt.hasNext()) {
			Map.Entry<Long, Recipe> entry = (Map.Entry<Long, Recipe>) recipeBookIt.next();
			String recipeName = entry.getValue().getRecipeName();
			Recipe currentRecipe = entry.getValue();
			String recipeNewName = recipeName.replaceAll(" ", "");

			Pattern pattern = Pattern.compile(newName, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(recipeNewName);
			if (matcher.find()) {
				searchedRecipeList.add(currentRecipe);
			}
		}
		return searchedRecipeList;
	}

	/**
	 * get recipe by its real name(probably has the depulicated name)
	 * 
	 * @param name
	 * @return
	 */
	public static Recipe getRecipeByName(String name) {
		Iterator<Entry<Long, Recipe>> recipeBookIt = recipeBook.entrySet().iterator();
		Recipe recipe = null;
		while (recipeBookIt.hasNext()) {
			Map.Entry<Long, Recipe> entry = (Map.Entry<Long, Recipe>) recipeBookIt.next();
			if(name == entry.getValue().getRecipeName()) {
			recipe=entry.getValue();
			}
		}
		return recipe;
	}

	/**
	 * 
	 * search recipes by area and print the list of results
	 * 
	 * @param areaName
	 *            the area name for search
	 * 
	 */

	public static void getRecipeByArea(String areaName) {

		Area area = searchArea(areaName);

		List<String> resultList = area.getAreaRecipe();

		Iterator<String> iterator = resultList.iterator();

		while (iterator.hasNext())

			System.out.println(iterator.next());

	}

	/**
	 * 
	 * delete a recipe from the cook book
	 * 
	 * @param recipe
	 *            the recipe for delete
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */

	public static void deleteRecipe(Recipe recipe) throws ClassNotFoundException, SQLException {

		searchArea(recipe.getAreaName()).getAreaRecipe().remove(recipe.getRecipeName());

		recipeBook.remove(recipe.getIdRecipe());

		CookBookDB.deleteRecipeDB(recipe);

		recipe = null;

		System.out.println("Recipe deleted!");

	}

	/**
	 * 
	 * delete a area from the cook book
	 * 
	 * @param area
	 *            the area for delete
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */

	public static void deleteArea(String areaName) throws ClassNotFoundException, SQLException {

		Area area = areas.get(areaName);

		Recipe recipe = null;

		Iterator<String> iterator = area.getAreaRecipe().iterator();

		while (iterator.hasNext()) {

			recipe = getRecipeByName(iterator.next());

			recipe.setAreaName("Unknown");

		}

		CookBookDB.deleteAreaDB(area);
		area = null;
		System.out.println("Area deleted!");

	}

	/**
	 * 
	 * change the area of a recipe
	 * 
	 * @param recipe
	 *            the recipe for change
	 * 
	 * @param newName
	 *            the new name of the desired area
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */

	/**
	

		Area area = searchArea(recipe.getAreaName());

		area.getAreaRecipe().remove(recipe.getRecipeName());

		createNewArea(newName);

		searchArea(newName).addRecipe(recipe);

	}
*/
	/**
	 * 
	 * search the area by its name
	 * 
	 * @param searchName
	 *            the area name for search
	 * 
	 * @return the searched area
	 * 
	 */

	public static Area searchArea(String searchName) {

		return areas.get(searchName);

	}

	/**
	 * 
	 * determine whether an area exists
	 * 
	 * @param newAreaName
	 *            the area name for determine
	 * 
	 * @return whether the area exists
	 * 
	 */

	public static boolean areaExists(String newAreaName) {

		return areas.containsKey(newAreaName);

	}

	/**
	 * 
	 * create a new area for the cook book, if the area already exists, then nothing
	 * happens
	 * 
	 * @param newAreaName
	 *            the area for create
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */

	public static void createNewArea(String newAreaName) throws ClassNotFoundException, SQLException {

		if (!areaExists(newAreaName)) {

			Area newArea = new Area(newAreaName);

			areas.put(newAreaName, newArea);
			
			CookBookDB.addNewAreaDB(newArea);

		}
	}

	public static void editRecipe(Recipe recipeOld,Recipe recipeNew) throws ClassNotFoundException, SQLException {
		long idRecipe = recipeOld.getIdRecipe();
		recipeNew.setIdRecipe(idRecipe);
	
		CookBookDB.updateRecipe(recipeNew, recipeOld.getRequiredIngredients(), recipeOld.getPreparationSteps());	
		recipeBook.remove(idRecipe);
		recipeBook.put(idRecipe, recipeNew);
		
		
	}
	
	public static Map<Long, Recipe> getRecipeBook() {
		return recipeBook;
	}

	public static void setRecipeBook(Map<Long, Recipe> recipeBook) {
		CookBook.recipeBook = recipeBook;
	}

	public static Map<String, Area> getAreas() {
		return areas;
	}

	public static void setAreas(Map<String, Area> areas) {
		CookBook.areas = areas;
	}

}