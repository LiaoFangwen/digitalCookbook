package digitalCookBook;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class IngredientTest {
	private Ingredient ingredient;
	@Test
	void testIngredientStringDoubleStringString() {
		ingredient = new Ingredient("grosse Kartoffeln", 2, "Stueck", "");
		assertEquals("grosse Kartoffeln", ingredient.getIngredientName());
		assertEquals(2, ingredient.getQuantity());
		assertEquals("Stueck", ingredient.getUnit());
		assertEquals("", ingredient.getDescription());
	}

	@Test
	void testIngredientStringDoubleString() {
		ingredient = new Ingredient("Zucker",1, "TL");
		assertEquals("Zucker", ingredient.getIngredientName());
		assertEquals(1, ingredient.getQuantity());
		assertEquals("TL", ingredient.getUnit());
		assertNull(ingredient.getDescription());
	}
	@Test
	void testSetTotalAmount() {
		ingredient = new Ingredient("water", 1, "L");
		ingredient.setServeAmount(4);
		assertEquals(0.35, ingredient.getTotalAmount());
		ingredient = new Ingredient("Knoblauch", 3, "Zehen");
		ingredient.setServeAmount(4);
		assertEquals(6, ingredient.getTotalAmount());
	}

}
