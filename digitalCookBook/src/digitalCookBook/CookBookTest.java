package digitalCookBook;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class CookBookTest {
	
	@Test
	public void testAddRecipe() throws ClassNotFoundException, SQLException {
		CookBook cb1 = new CookBook("cb1");
		Recipe recipe1 = new Recipe("Suan La jiba", "Sichuan Dish", 2);		
		recipe1.addIngredient(new Ingredient("potato noodles", 1.0, "bunch"));
		recipe1.addIngredient(new Ingredient("peanuts", 2.0, "tablespoon", "roasted"));
		recipe1.addIngredient(new Ingredient("spring onion", 1.0, "tablespoon", "chopped"));
		recipe1.addIngredient(new Ingredient("coriander", 1.0, "tablespoon", "chopped"));
		recipe1.addIngredient(new Ingredient("pickled Sichuan vegetable", 2.0, "tablespoon", "chopped"));
		recipe1.addIngredient(new Ingredient("garlic", 3.0, "cloves", "mashed"));
		recipe1.addIngredient(new Ingredient("peanut oil", 0.5, "tablespoon"));
		recipe1.addIngredient(new Ingredient("Sichuan peppercorn powder", 0.5, "teaspoon"));
		recipe1.addIngredient(new Ingredient("Chinese five spicy powder", 0.5, "teaspoon"));
		recipe1.addIngredient(new Ingredient("chili powder", 1.0, "teaspoon"));
		recipe1.addIngredient(new Ingredient("vinegar", 1.0, "tablespoon"));
		recipe1.addIngredient(new Ingredient("light soy sauce", 1.0, "tablespoon"));
		recipe1.addIngredient(new Ingredient("salt", 1.0, "teaspoon"));	
		recipe1.addPreparationStep(new PreparationStep("Soak the sweet potato noodles with hot water around 30 minutes."));
		recipe1.addPreparationStep(new PreparationStep("Transfer out and set aside."));
		recipe1.addPreparationStep(new PreparationStep("In the serving bowls and mix all the seasonings."));
		recipe1.addPreparationStep(new PreparationStep("Heat up peanuts oil in pan to stir-fry the mashed garlic until aroma."));
		recipe1.addPreparationStep(new PreparationStep("Mix the garlic oil with the seasonings."));
		recipe1.addPreparationStep(new PreparationStep("Add some spring onions and corianders in serving bowls."));
		recipe1.addPreparationStep(new PreparationStep("Pour in boiling water or stock to tune the seasonings."));
		recipe1.addPreparationStep(new PreparationStep("Add vinegar and light soy sauce."));
		recipe1.addPreparationStep(new PreparationStep("Mix well and set aside."));
		recipe1.addPreparationStep(new PreparationStep("Cook soaked sweet potato noodles around 3~5 minutes until you can break the noodles with your fingers."));
		recipe1.addPreparationStep(new PreparationStep("Transfer the noodles out to the serving bowls and then add top with pickled vegetables, roasted peanuts and chopped spring onions and coriander. "));	
		recipe1.setPreparationTime(30);
		recipe1.setCookingTime(5);

		CookBook.addRecipe(recipe1);
		
		long i = recipe1.getIdRecipe();
		CookBook cb2 = new CookBook("cb2");
		Recipe recipe2 = CookBook.getRecipeByID(i);
		
		int j = recipe1.getPreparationSteps().size();
		for(int k = 0; k<j; k++)
			assertEquals(recipe1.getPreparationSteps().get(k).getContent(), recipe2.getPreparationSteps().get(k).getContent());
	}

	@Test
	public void testSearchRecipeByKeyword() throws ClassNotFoundException, SQLException {
		CookBook cb = new CookBook("cb");
		Recipe recipe1 = new Recipe("Chinesische Teeeier", "China", 2);	
		Recipe recipe2 = new Recipe("Chinesischer Gurkensalat", "China", 2);
		CookBook.addRecipe(recipe1);
		CookBook.addRecipe(recipe2);
		List<Recipe> list = CookBook.searchRecipeByKeyword("Chinesische");
		assertEquals("Chinesischer Gurkensalat", list.get(0).getRecipeName());
		assertEquals("Chinesische Teeeier", list.get(1).getRecipeName());
		
		
	}

	@Test
	public void testGetRecipeByID() throws ClassNotFoundException, SQLException {
		CookBook cb = new CookBook("cb");
		Recipe recipe = new Recipe("Haehnchen Kung Pao", "China", 2);
		CookBook.addRecipe(recipe);
		Recipe recipe1 = CookBook.getRecipeByID(recipe.getIdRecipe());
		assertEquals(recipe1, recipe);
		
	}

	@Test
	public void testGetRecipeByArea() throws ClassNotFoundException, SQLException {
		CookBook cb = new CookBook("cb");
		Recipe recipe1 = new Recipe("Grosser Teller Haehnchen", "Xinjiang", 2);
		Recipe recipe2 = new Recipe("Kebab", "Xinjiang", 2);
		CookBook.addRecipe(recipe1);
		CookBook.addRecipe(recipe2);
		CookBook.getRecipeByArea("Xinjiang");
	}
	
	@Test
	public void testDeleteRecipe() throws ClassNotFoundException, SQLException {
		CookBook cb = new CookBook("cb");
		Recipe recipe1 = new Recipe("Grosser Teller Haehnchen", "Xinjiang", 2);
		Recipe recipe2 = new Recipe("Kebab", "Xinjiang", 2);
		Recipe recipe3 = new Recipe("Doener", "Xinjiang", 2);
		CookBook.addRecipe(recipe1);
		CookBook.addRecipe(recipe2);
		CookBook.addRecipe(recipe3);
		CookBook.getRecipeByArea("Xinjiang");
		CookBook.deleteRecipe(recipe3);
		CookBook.getRecipeByArea("Xinjiang");
	}

	@Test
	public void testDeleteArea() throws ClassNotFoundException, SQLException {
		CookBook cb = new CookBook("cb");
		Recipe recipe1 = new Recipe("Grosser Teller Haehnchen", "Xinjiang", 2);
		Recipe recipe2 = new Recipe("Kebab", "Xinjiang", 2);
		Recipe recipe3 = new Recipe("Doener", "Xinjiang", 2);
		CookBook.addRecipe(recipe1);
		CookBook.addRecipe(recipe2);
		CookBook.addRecipe(recipe3);
		CookBook.deleteArea("Xinjiang");
		assertEquals("Unknown", recipe1.getAreaName());
		assertEquals("Unknown", recipe2.getAreaName());
		assertEquals("Unknown", recipe3.getAreaName());
	}


	@Test
	public void testEditRecipe() throws ClassNotFoundException, SQLException {
		CookBook cb = new CookBook("cb");
		Recipe recipe1 = new Recipe("Gebratener Reis", "Jiangsu", 2);
		recipe1.addIngredient(new Ingredient("Gekochtes Reis", 200, "g"));
		recipe1.addIngredient(new Ingredient("Fruehlingszwiebeln", 2, "Stuecke"));
		recipe1.addIngredient(new Ingredient("Karotte", 0.5, "Stuecke"));
		recipe1.addIngredient(new Ingredient("Salz", 5, "g"));
		recipe1.addPreparationStep(new PreparationStep("Fruehlingszwiebeln schneiden."));
		recipe1.addPreparationStep(new PreparationStep("Karotte wuerfeln."));
		recipe1.addPreparationStep(new PreparationStep("Reis anbraten."));
		CookBook.addRecipe(recipe1);
		long i = recipe1.getIdRecipe();
		Recipe recipe2 = new Recipe("Gebratener Reis", "Jiangsu", 2);
		recipe2.addIngredient(new Ingredient("Gekochtes Reis", 200, "g"));
		recipe2.addIngredient(new Ingredient("Chinesischer Kochwein", 30, "ml"));
		recipe2.addIngredient(new Ingredient("Fruehlingszwiebeln", 2, "Stuecke"));
		recipe2.addIngredient(new Ingredient("Karotte", 0.5, "Stuecke"));
		recipe2.addPreparationStep(new PreparationStep("Fruehlingszwiebeln schneiden."));
		recipe2.addPreparationStep(new PreparationStep("Mit Salz und Pfeffer wurzen."));
		recipe2.addPreparationStep(new PreparationStep("Reis anbraten."));
		recipe2.addPreparationStep(new PreparationStep("Eier darin verruehren."));
		recipe2.addPreparationStep(new PreparationStep("Mit Fruehlingszwiebeln bestreuen."));
		CookBook.editRecipe(recipe1, recipe2);
		Recipe recipe3 = CookBook.getRecipeByID(i);
		assertEquals("Jiangsu", recipe3.getAreaName());
		assertEquals("Eier darin verruehren.", recipe3.getPreparationSteps().get(3).getContent());
		
	}


}
