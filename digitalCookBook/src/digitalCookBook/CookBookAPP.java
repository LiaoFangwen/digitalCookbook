
package digitalCookBook;

import java.sql.SQLException;
import java.util.Iterator;

import javafx.stage.Stage;

public class CookBookAPP {
/*
	/**
	 * Creates a Gong Bao Jiding recipe.
	 * 
	 * @return  the new recipe
	 */
	/*
	private static Recipe createGongBaoJiding() {
		Recipe recipe = new Recipe("Gong Bao Jiding", "Sichuan Dish", 4);		
		
		recipe.addIngredient(new Ingredient("cornstarch", 1.0, "tablespoon"));
		recipe.addIngredient(new Ingredient("soy sauce", 4.0, "tablespoon"));
		recipe.addIngredient(new Ingredient("chicken breast", 0.5, "kg"));
		recipe.addIngredient(new Ingredient("Shaoxin rice wine", 3.0, "tablespoon"));
		recipe.addIngredient(new Ingredient("sugar", 2.0, "tablespoon"));
		recipe.addIngredient(new Ingredient("chicken stock", 3.0, "tablespoon"));
		recipe.addIngredient(new Ingredient("Chiangang vinegar", 4.0, "teaspoon"));
		recipe.addIngredient(new Ingredient("sesame oil", 4.0, "tablespoon"));
		recipe.addIngredient(new Ingredient("dark soy sauce", 2.0, "teaspoon"));
		recipe.addIngredient(new Ingredient("peanut oil", 3.0, "tablespoon"));
		recipe.addIngredient(new Ingredient("dried red chilis", 12.0, "pieces", "stemmed, halved crosswise, and seeded"));
		recipe.addIngredient(new Ingredient("scallions", 5.0, "pieces", "white part onyl, thickly sliced crosswise"));
		recipe.addIngredient(new Ingredient("garlic", 1.0, "cloves", "peeled, thinly sliced"));
		recipe.addIngredient(new Ingredient("ginger", 0.5, "pieces", "peeled, minced"));
		recipe.addIngredient(new Ingredient("peanuts", 0.5, "cups", "peeled, thinly sliced"));		
	
		recipe.addPreparationStep(new PreparationStep("Mix together cornstarch and 1 tbsp. of the soy sauce in a medium bowl."));
		recipe.addPreparationStep(new PreparationStep("Add chicken, toss well, and set aside to marinate for 30 minutes."));
		recipe.addPreparationStep(new PreparationStep("Meanwhile, mix together the remaining 3 tbsp. soy sauce, rice wine, sugar, stock, vinegar, sesame oil, and dark soy sauce."));
		recipe.addPreparationStep(new PreparationStep("Set aside."));
		recipe.addPreparationStep(new PreparationStep("Heat peanut oil in a wok or large nonstick skillet over high heat until just beginning to smoke."));
		recipe.addPreparationStep(new PreparationStep("Add chilis, half the scallions, garlic, ginger, and chicken and stir-fry until chicken is golden, 3-5 minutes."));
		recipe.addPreparationStep(new PreparationStep("Add soy sauce mixture and stir-fry until sauce thickens, about 2 minutes."));
		recipe.addPreparationStep(new PreparationStep("Stir in peanuts."));
		recipe.addPreparationStep(new PreparationStep("Garnish with remaining scallions."));

		recipe.setPreparationTime(30);
		recipe.setCookingTime(10);
		
		return recipe;
	
	}
	
	/*
	/**
	 * Creates a Hong Shao Rou recipe.
	 * 
	 * @return  the recipe
	 */

	private static void createHongShaoRou(CookBook cb, CookBookDB db) throws SQLException {
		Recipe recipe = new Recipe("Hong Shao Rou", "Hu Nan", 4);	
		String areaName = recipe.getAreaName();
		int id_A;
		if(cb.areaExists(areaName)) {
			id_A = cb.searchArea(areaName).getAreaID();
		} else {
			cb.createNewArea(areaName);
			db.addNewAreaToDB(cb.searchArea(areaName));
			id_A = db.getAreaFromDB(areaName).getAreaID();
		}
		
	    recipe.setCookingTime(100);
		recipe.setPreparationTime(5);
		recipe.setRecipeDescription("A Hu Nan Dish");
		recipe.setAreaID(id_A);
		db.addNewRecipeToDB(recipe);
		Recipe newRecipe = db.getRecipeFromDB(recipe.getRecipeName(), recipe.getRecipeDescription());
		cb.add(newRecipe);
		Ingredient ingredient;
		ingredient = new Ingredient("pork belly", 0.5, "kg", "cut into 2cm pieces");
		newRecipe.addIngredient(ingredient);
		db.addIngredientToDB(newRecipe, ingredient);
		ingredient = new Ingredient("cooking oil", 2.0, "tablespoon");
		newRecipe.addIngredient(ingredient);
		db.addIngredientToDB(newRecipe, ingredient);
		ingredient = new Ingredient("brown sugar", 1.0, "tablespoon");
		newRecipe.addIngredient(ingredient);
		db.addIngredientToDB(newRecipe, ingredient);
		ingredient = new Ingredient("Shaoxin rice wine", 3.0, "tablespoon");
		newRecipe.addIngredient(ingredient);
		db.addIngredientToDB(newRecipe, ingredient);
		ingredient = new Ingredient("light soy sauce", 1.0, "tablespoon");
		newRecipe.addIngredient(ingredient);
		db.addIngredientToDB(newRecipe, ingredient);
		ingredient = new Ingredient("dark soy sauce", 0.5, "tablespoon");
		newRecipe.addIngredient(ingredient);
		db.addIngredientToDB(newRecipe, ingredient);
		ingredient = new Ingredient("chicken stock or water", 2.0, "cups");
		newRecipe.addIngredient(ingredient);
		db.addIngredientToDB(newRecipe, ingredient);
		
		PreparationStep step;
		step = new PreparationStep("Bring a pot of water to a boil and blanch the pork for a couple minutes.");
		newRecipe.addPreparationStep(step);
		db.addPreparationStepToDB(newRecipe, step);
		step = new PreparationStep("Take the pork out of the pot and set aside.");
		newRecipe.addPreparationStep(step);
		db.addPreparationStepToDB(newRecipe, step);
		step = new PreparationStep("Over low heat, add oil and sugar to your wok.");
		newRecipe.addPreparationStep(step);
		db.addPreparationStepToDB(newRecipe, step);
		step = new PreparationStep("Melt the sugar slightly and add the pork.");
		newRecipe.addPreparationStep(step);
		db.addPreparationStepToDB(newRecipe, step);
		step = new PreparationStep("Raise the heat to medium and cook until the pork is lightly browned.");
		newRecipe.addPreparationStep(step);
		db.addPreparationStepToDB(newRecipe, step);
		step = new PreparationStep("Turn the heat back down to low and add cooking wine, light soy sauce, dark soy sauce, and chicken stock.");
		newRecipe.addPreparationStep(step);
		db.addPreparationStepToDB(newRecipe, step);
		step = new PreparationStep("Cover and simmer for about 60 minutes to 90 minutes until pork is fork tender.");
		newRecipe.addPreparationStep(step);
		db.addPreparationStepToDB(newRecipe, step);
		step = new PreparationStep("Every 5-10 minutes, stir to prevent burning and add water if it gets too dry.");
		newRecipe.addPreparationStep(step);
		db.addPreparationStepToDB(newRecipe, step);
		step = new PreparationStep("Once the pork is fork tender, if there is still a lot of visible liquid, uncover the wok, turn up the heat, and stir continuously the sauce has reduced to a glistening coating.");
		newRecipe.addPreparationStep(step);
		db.addPreparationStepToDB(newRecipe, step);
	} 
	
	/*
	/**
	 * Creates a Suan La Fen recipe.
	 * 
	 * @return  the recipe
	 */
/*
	private static Recipe createSuanLaFen() {
		Recipe recipe = new Recipe("Suan La Fen", "Sichuan Dish", 2);		
		
		recipe.addIngredient(new Ingredient("potato noodles", 1.0, "bunch"));
		recipe.addIngredient(new Ingredient("peanuts", 2.0, "tablespoon", "roasted"));
		recipe.addIngredient(new Ingredient("spring onion", 1.0, "tablespoon", "chopped"));
		recipe.addIngredient(new Ingredient("coriander", 1.0, "tablespoon", "chopped"));
		recipe.addIngredient(new Ingredient("pickled Sichuan vegetable", 2.0, "tablespoon", "chopped"));
		recipe.addIngredient(new Ingredient("garlic", 3.0, "cloves", "mashed"));
		recipe.addIngredient(new Ingredient("peanut oil", 0.5, "tablespoon"));
		recipe.addIngredient(new Ingredient("Sichuan peppercorn powder", 0.5, "teaspoon"));
		recipe.addIngredient(new Ingredient("Chinese five spicy powder", 0.5, "teaspoon"));
		recipe.addIngredient(new Ingredient("chili powder", 1.0, "teaspoon"));
		recipe.addIngredient(new Ingredient("vinegar", 1.0, "tablespoon"));
		recipe.addIngredient(new Ingredient("light soy sauce", 1.0, "tablespoon"));
		recipe.addIngredient(new Ingredient("salt", 1.0, "teaspoon"));
				
		recipe.addPreparationStep(new PreparationStep("Soak the sweet potato noodles with hot water around 30 minutes."));
		recipe.addPreparationStep(new PreparationStep("Transfer out and set aside."));
		recipe.addPreparationStep(new PreparationStep("In the serving bowls and mix all the seasonings."));
		recipe.addPreparationStep(new PreparationStep("Heat up peanuts oil in pan to stir-fry the mashed garlic until aroma."));
		recipe.addPreparationStep(new PreparationStep("Mix the garlic oil with the seasonings."));
		recipe.addPreparationStep(new PreparationStep("Add some spring onions and corianders in serving bowls."));
		recipe.addPreparationStep(new PreparationStep("Pour in boiling water or stock to tune the seasonings."));
		recipe.addPreparationStep(new PreparationStep("Add vinegar and light soy sauce."));
		recipe.addPreparationStep(new PreparationStep("Mix well and set aside."));
		recipe.addPreparationStep(new PreparationStep("Cook soaked sweet potato noodles around 3~5 minutes until you can break the noodles with your fingers."));
		recipe.addPreparationStep(new PreparationStep("Transfer the noodles out to the serving bowls and then add top with pickled vegetables, roasted peanuts and chopped spring onions and coriander. "));
				
		recipe.setPreparationTime(30);
		recipe.setCookingTime(5);
		
		return recipe;
	} 
	*/
	
	
	
	
	
	private static Recipe createGongBaoJiding(CookBook cb, CookBookDB db) throws SQLException {
		Recipe recipe = new Recipe("Gong Bao Jiding", "Si Chuan", 4);
		cb.add(recipe);
		Area area = new Area(recipe.getAreaName());
		db.addNewAreaToDB(area);
		db.addNewRecipeToDB(recipe);
		return recipe;
	}
	/**
	 * Program entry point. 
	 * 
	 * @param args  command line arguments; not used.
	 * @throws Exception 
	 */

	public static void main(String[] args) throws Exception {
		CookBook cb = new CookBook("Chinese Cuisine");
		CookBookDB db = new CookBookDB();
		
		/*
		Recipe recipe = cb.getRecipeByName("Gong Bao Jiding");
		System.out.println(recipe);
        Area area = cb.searchArea(recipe.getAreaName());

		cb.getRecipeByArea("Sichuan Dish");
		cb.deleteArea(area);
		System.out.println(recipe.getAreaName());
		*/
		
		
	}
	private static void initializeCookBook(CookBook cb, CookBookDB db) throws SQLException {
		cb.setAreaBook(db.getAreaBookFromDB());
		cb.setRecipeBook(db.getRecipeBookFromDB());
		Iterator<Recipe> iteratorRecipe = cb.getRecipeBook().values().iterator();
		while(iteratorRecipe.hasNext()) {
			Recipe recipe = iteratorRecipe.next();
			recipe.setRequiredIngredients(db.getIngredientsFromDB(recipe));
			recipe.setPreparationSteps(db.getPreparationStepsFromDB(recipe));
		}
		Iterator<Area> iteratorArea = cb.getAreaBook().values().iterator();
		while(iteratorArea.hasNext()) {
			Area area = iteratorArea.next();
			area.setAreaRecipe(db.getRecipesOfAreaFromDB(area));
		}
	}

}
