package digitalCookBook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecipeTest {
	private Recipe recipe = new Recipe();

	@Test
	public void testRecipeStringStringInt() {
		recipe = new Recipe("Sauer-scharfe Kartoffelstreifen", "Sichuan", 2);
		assertEquals("Sauer-scharfe Kartoffelstreifen", recipe.getRecipeName());
		assertEquals("Sichuan", recipe.getAreaName());
		assertEquals(2, recipe.getServeAmount());

	}
	@Test
	void testSetIngredientAmount() {
		recipe.addIngredient(new Ingredient("Ingwer", 1, "EL"));
		recipe.setServeAmount(4);
		recipe.setIngredientAmount();
		double i = recipe.getRequiredIngredients().get(0).getTotalAmount();
		assertEquals(2, i);
	}

	@Test
	void testToString() {
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
		String ing = "potato noodles, peanuts, spring onion, coriander, pickled Sichuan vegetable, garlic...";
		assertEquals(ing, recipe.toString());
	}
	
}
