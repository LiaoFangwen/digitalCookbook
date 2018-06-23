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
	/**
	 * Class constructor specifying the name of the cook book. All detailed information of recipes and areas are set to the cook book.
	 * @param name the cook book name
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public CookBook(String name) throws ClassNotFoundException, SQLException {

		this.cbName = name;

		recipeBook = CookBookDB.recipelist();

		areas = CookBookDB.areaList();

		CookBookDB.getConnection();

	}
	/**
	 * Add a new recipe to the cook book and DB.
	 * @param recipe the recipe to add
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void addRecipe(Recipe recipe) throws ClassNotFoundException, SQLException {
		CookBook.createNewArea(recipe.getAreaName());
		CookBookDB.addRecipeDB(recipe);
		CookBookDB.updateCookingTime(recipe, recipe.getCookingTime());
		CookBookDB.updatePreparationTime(recipe, recipe.getPreparationTime());
		int stepIndex = 0;
		Iterator<PreparationStep> iteratorStep = recipe.getPreparationSteps().iterator();
		while(iteratorStep.hasNext()) {
		PreparationStep pre = iteratorStep.next();
		pre.setIndex(stepIndex);
		stepIndex++;
		pre.setRecipeID(recipe.getIdRecipe());
		CookBookDB.addPreparationDB(pre);
		}
		
		Iterator<Ingredient> iteratorIngredient = recipe.getRequiredIngredients().iterator();
		while(iteratorIngredient.hasNext()) {
			Ingredient ingredient = iteratorIngredient.next();
			ingredient.setRecipeID(recipe.getIdRecipe());
			CookBookDB.addIngredientDB(ingredient);
		}
		
		recipeBook.put(recipe.getIdRecipe(), recipe);
		String areaName = recipe.getAreaName();
		searchArea(areaName).addRecipe(recipe);

	}
	/**
	 * Search a recipe by its name, includes key words searching.
	 * @param name the words that may be contained in a recipe name
	 * @return a list of recipes that have the key words in their name
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
	 * Get a recipe by its id, which is unique even when there exist other recipes with the same name. 
	 * @param id the id of the recipe
	 * @return the exact recipe with the unique id
	 */
	public static Recipe getRecipeByID(long id) {
		Iterator<Entry<Long, Recipe>> recipeBookIt = recipeBook.entrySet().iterator();
		Recipe recipe = null;
		while (recipeBookIt.hasNext()) {
			Map.Entry<Long, Recipe> entry = (Map.Entry<Long, Recipe>) recipeBookIt.next();
			if(id == entry.getValue().getIdRecipe()) {
			recipe=entry.getValue();
			}
		}
		return recipe;
	}
	/**
	 *  Search recipes by an area. The name of the recipes will be printed out.
	 * @param areaName the area to search
	 */
	public static void getRecipeByArea(String areaName) {

		Area area = searchArea(areaName);

		List<Long> resultList = area.getAreaRecipe();

		Iterator<Long> iterator = resultList.iterator();

		while (iterator.hasNext())

			System.out.println(recipeBook.get(iterator.next()).getRecipeName());

	}
	/**
	 * Delete a recipe from the cook book and DB.
	 * @param recipe the recipe to delete
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void deleteRecipe(Recipe recipe) throws ClassNotFoundException, SQLException {

		searchArea(recipe.getAreaName()).getAreaRecipe().remove(recipe.getIdRecipe());

		recipeBook.remove(recipe.getIdRecipe());

		CookBookDB.deleteRecipeDB(recipe);

		recipe = null;

	}
	/**
	 * Delete an area from the cook book and DB. The area of the recipes belong to it will be changed to "Unknown".
	 * @param areaName the area to delete
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void deleteArea(String areaName) throws ClassNotFoundException, SQLException {

		Area area = areas.get(areaName);

		Recipe recipe = null;

		Iterator<Long> iterator = area.getAreaRecipe().iterator();

		while (iterator.hasNext()) {

			recipe = getRecipeByID(iterator.next());

			recipe.setAreaName("Unknown");

		}

		CookBookDB.deleteAreaDB(area);
		area = null;
		System.out.println("Area deleted!");

	}
	/**
	 * Search the area by its name.
	 * @param searchName the name to search
	 * @return the area with the name
	 */
	public static Area searchArea(String searchName) {

		return areas.get(searchName);

	}
	/**
	 * Determine whether a area exists.
	 * @param newAreaName the area name to find
	 * @return whether this area exists
	 */
	public static boolean areaExists(String newAreaName) {

		return areas.containsKey(newAreaName);

	}
	/**
	 * Create a new area in the cook book and in the DB. If the area already exists, then nothing happens.
	 * @param newAreaName the area name to create
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void createNewArea(String newAreaName) throws ClassNotFoundException, SQLException {

		if (!areaExists(newAreaName)) {

			Area newArea = new Area(newAreaName);

			areas.put(newAreaName, newArea);
			
			CookBookDB.addNewAreaDB(newArea);

		}
	}
	/**
	 * Update the information of a recipe in the cook book and DB.
	 * @param recipeOld the old recipe
	 * @param recipeNew the changed recipe
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void editRecipe(Recipe recipeOld,Recipe recipeNew) throws ClassNotFoundException, SQLException {
		searchArea(recipeNew.getAreaName()).addRecipe(recipeNew);

		long idRecipe = recipeOld.getIdRecipe();
		recipeNew.setIdRecipe(idRecipe);
		
		for(Ingredient ingredient:recipeNew.getRequiredIngredients()) {
			ingredient.setRecipeID(idRecipe);
		}
		
		for(PreparationStep step:recipeNew.getPreparationSteps()) {
			step.setRecipeID(idRecipe);
		}
		createNewArea(recipeNew.getAreaName());
		
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