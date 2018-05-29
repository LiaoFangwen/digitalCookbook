package digitalCookBook;
public class CookBookTest {
	private static Recipe createGongBaoJiding() {
		Recipe recipe = new Recipe("Gong Bao Jiding", "Sichuan Dish", 4);
		return recipe;
	}
	public static void main(String args[]) {
		CookBook cb1 = new CookBook("CookBook1");
		cb1.displayCookBookName();
		cb1.createNewArea("SiChuan");
		cb1.createNewArea("Shanghai");
		System.out.println(cb1.areaExists("SiChuan"));
		cb1.printAreas();
		System.out.println(cb1.searchArea("Shanghai").getAreaName());
	}
}
