package digitalCookBook;
import java.sql.*;
import java.util.*;

public class CookBookDB {
   private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   private final String DB_URL = "jdbc:mysql://localhost/";

   private final String USER = "root";
   private final String PASS = "12345678";
   
   private Connection conn = null;
   private Statement stmt = null;
   
   public CookBookDB() {
	   
	   
	   try {
		   Class.forName("com.mysql.jdbc.Driver");
		   System.out.println("Connecting to database...");
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   System.out.println("Creating database...");
		   stmt = conn.createStatement();
		   
		   String sql = "CREATE TABLE RecipeBook "
		   		+ "(id_R INTEGER not NULL AUTO_iNCREMENT, "
		   		+ "recipeName STRING, "
		   		+ "serveAmount INTEGER, "  
		   		+ "cookingTime INTEGER, "  
		   		+ "preparationTime INTEGER, " 
		   		+ "recipeDescription STRING, " 
		   		+ "areaName STRING, " 
		   		+ "id_A INTEGER, "
		   		+ "PRIMARY KEY (id_R), "
		   		+ "FOREIGN KEY (id_A) REFERENCES AreaBook(id_A))";
		   stmt.executeUpdate(sql);
		   
		   
		   
		   sql = "CREATE TABLE AreaBook "
		   		+ "(id_A INTEGER not NULL AUTO_INCREMENT, "
		   		+ "areaName STRING, "
		   		+ "PRIMARY KEY (id_A))";
		   stmt.executeUpdate(sql);
		   
		   
		   
		   sql = "CREATE TABLE IngredientBook "
		   		+ "(id_I INTEGER not NULL AUTO_INCREMENT, "
		   		+ "id_R INTEGER, "
		   		+ "ingredientName STRING, "
		   		+ "amount DOUBLE, "
		   		+ "unit STRING, "
		   		+ "description STRING, "
		   		+ "serveAmount INTEGER, "
		   		+ "PRIMARY KEY (id_I), "
		   		+ "FOREIGN KEY (Id_R) REFERENCES RecipeBook(id_R)";
		   stmt.executeUpdate(sql);
		   
		   
		   
		   sql = "CREATE TABLE PreparationStepBook "
		   		+ "(id_P INTEGER not NULL AUTO_INCREMENT, "
		   		+ "id_R INTEGER, "
		   		+ "content STRING, "
		   		+ "PRIMARY KEY (id_P), "
		   		+ "FOREIGN KEY (Id_R) REFERENCES RecipeBook(id_R)";
		   stmt.executeUpdate(sql);
	   } catch (Exception e) {
		   e.printStackTrace();
	   }	   
   }
   
   public Map<String, Recipe> getRecipeBookFromDB() throws SQLException {
	   Map<String, Recipe> map = new HashMap<String, Recipe>();
	   String sql = "SELECT * FROM RecipeBook";
	   ResultSet rs = stmt.executeQuery(sql);
	   while(rs.next()) {
		   int id = rs.getInt(1);
		   String name = rs.getString(2);
		   int serveAmount = rs.getInt(3);
		   int ct = rs.getInt(4);
		   int pt = rs.getInt(5);
		   String d = rs.getString(6);
		   String an = rs.getString(7);
		   int areaID = rs.getInt(8);
		   Recipe recipe = new Recipe(id, name, serveAmount, ct, pt, d, an, areaID);
		   recipe.setRequiredIngredients(getIngredientsFromDB(recipe));
		   recipe.setPreparationSteps(getPreparationStepsFromDB(recipe));
		   map.put(recipe.getRecipeName(), recipe);
	   }
	   return map;
   }
   
   
   public Map<String, Area> getAreaBookFromDB() throws SQLException {
	   Map<String, Area> map = new HashMap<String, Area>();
	   String sql = "SELECT * FROM AreaBook";
	   ResultSet rs = stmt.executeQuery(sql);
	   while(rs.next()) {
		   int id = rs.getInt(1);
		   String name = rs.getString(2);
		   Area area = new Area(id, name);
		   area.setAreaRecipe(getRecipesOfAreaFromDB(area));
		   map.put(area.getAreaName(), area);
	   }
	   return map;
   }
	 
    
   public void createAreaRecipeBookToDB(Area area) throws SQLException {
	   String sql = "CREATE TABLE " + area.getAreaName() + "(id_AR INTEGER NOT NULL AUTO_INCREMENT, "
	   		+ "id_R INTEGER, "
	   		+ "recipeName STRING, "
	   		+ "PRIMARY KEY (id_AR), "
	   		+ "FOREIGN KEY(id_R) REFERENCES RecipeBook(id_R))";
	   stmt.executeUpdate(sql);
   }

   
   
   public void addNewRecipeToDB(Recipe recipe) throws SQLException {
	   String sql = "INSERT INTO RecipeBook VALUES ("
			+ recipe.getRecipeName() + ", " 
			+ recipe.getServeAmount() + ", " 
			+ recipe.getCookingTime() + ", " 
			+ recipe.getPreparationTime() + ", "
			+ recipe.getRecipeDescription() + ", "
			+ recipe.getAreaName() + ", "
			+ recipe.getArea() + ", "
			+ recipe.getAreaID() + ")";
	   stmt.executeUpdate(sql);
	   Iterator<Ingredient> iterator1 = recipe.getRequiredIngredients().iterator();
	   while(iterator1.hasNext())
		   addIngredientToDB(recipe, iterator1.next());
	   Iterator<PreparationStep> iterator2 = recipe.getPreparationSteps().iterator();
	   while(iterator2.hasNext())
		   addPreparationStepToDB(recipe, iterator2.next());
   }
   
   public void addNewAreaToDB(Area area) throws SQLException {
	   String sql = "INSERT INTO AreaBook VALUES ("
			+ area.getAreaName() + ")";
	   stmt.executeUpdate(sql);
   }
   
   public void addRecipeToAreaToDB(Area area, Recipe recipe) throws SQLException {
	   String sql = "INSERT INTO " + area.getAreaName() + " VALUES (" 
			+ recipe.getRecipeName() + ")";
	   stmt.executeUpdate(sql);
   }
   
   public void addIngredientToDB(Recipe recipe, Ingredient ingredient) throws SQLException {
	   String sql = "INSERT INTO IngredientBook VALUES (" 
			+ ingredient.getIngredientName() + ", "
			+ recipe.getRecipeID() + ", "
			+ ingredient.getAmount() + ", " 
			+ ingredient.getUnit() + ", " 
			+ ingredient.getDescription() + ", " 
			+ ingredient.getServeAmount() + ")";
	   stmt.executeUpdate(sql);
   }
   
   public void addPreparationStepToDB(Recipe recipe, PreparationStep step) throws SQLException {
	   String sql = "INSERT INTO PreparationStpeBook VALUES (" 
			+ recipe.getRecipeID() + ","
			+ step.getContent() + ")";
	   stmt.executeUpdate(sql);
   }
   
   public List<String> getRecipesOfAreaFromDB(Area area) throws SQLException {
	   List<String> list = new ArrayList<String>();
	   String sql = "SELECT * FROM" + area.getAreaName();
	   ResultSet rs = stmt.executeQuery(sql);
	   while(rs.next()) {
		   String areaName = rs.getString(3);
		   list.add(areaName);
	   }
	   return list;
		   
   }
   
   public List<Ingredient> getIngredientsFromDB(Recipe recipe) throws SQLException {
	   String sql = "SELECT * FROM IngredientBook WHERE id_R = " + recipe.getRecipeID();
	   ResultSet rs = stmt.executeQuery(sql);
	   List<Ingredient> list = new ArrayList<Ingredient>();
	   while(rs.next()) {
		   int id = rs.getInt(1);
		   String name = rs.getString(3);
		   double amount = rs.getDouble(4);
		   String unit = rs.getString(5);
		   String description = rs.getString(6);
		   int serveAmount = rs.getInt(7);
		   Ingredient ing = new Ingredient(id, name, amount, unit, description, serveAmount);
		   list.add(ing);
	   }
	   return list;	   
   }
   
   
   public List<PreparationStep> getPreparationStepsFromDB(Recipe recipe) throws SQLException {
	   String sql = "SELECT * FROM PreparationStepBook WHERE id_R = " + recipe.getRecipeID();
	   ResultSet rs = stmt.executeQuery(sql);
	   List<PreparationStep> list = new ArrayList<PreparationStep>();
	   while(rs.next()) {
		   int id = rs.getInt(1);
		   String content = rs.getString(3);
		   PreparationStep ps = new PreparationStep(id, content);
		   list.add(ps);
	   }
	   return list;	   
   }
   
   public void deleteRecipeFromDB(Recipe recipe) throws SQLException {
	   String sql = "DELETE FROM " + recipe.getAreaName() + " WHERE id_R = " + recipe.getRecipeID(); 
	   stmt.executeUpdate(sql);
	   sql = "DELETE FROM IngredientBook WHERE id_R = " + recipe.getRecipeID();
	   stmt.executeUpdate(sql);
	   sql = "DELETE FROM PreparationBook WHERE id_R = " + recipe.getRecipeID();
	   stmt.executeUpdate(sql);
	   sql = "DELETE FROM RecipeBook WHERE id_R = " + recipe.getRecipeID();
	   stmt.executeUpdate(sql);
   }
   
   public void deleteAreaFromDB(Area area) throws SQLException {
	   String sql = "DROP TABLE " + area.getAreaName();
	   stmt.executeUpdate(sql);	   
	   sql = "DELETE FROM AreaBook WHERE id_A = " + area.getAreaID();
	   stmt.executeUpdate(sql);
   }
   
   public void deleteIngredientFromDB(Ingredient ingredient) throws SQLException {
	   String sql = "DELETE FROM IngredientBook WHERE id_I = " + ingredient.getIngredientID();
	   stmt.executeUpdate(sql);
   }
   
   public void deletePreparationStepFromDB(PreparationStep step) throws SQLException {
	   String sql = "DELETE FROM PreparationBook WHERE id_P = " + step.getPreparationStepID();
	   stmt.executeUpdate(sql);
   }
   
   
   
   
   public void changeRecipeAreaInDB(Recipe recipe, Area area) throws SQLException {
	   String sql = "DELETE FROM " + recipe.getAreaName()
	   		+ " WHERE id_R = " + recipe.getRecipeID();
	   stmt.executeUpdate(sql);
	   sql = "UPDATE RecipeBook SET areaName = "
		    + area.getAreaName() + ", SET id_A = "
		    + area.getAreaID() + " WHERE id_R = "
		    + recipe.getRecipeID();
	   stmt.executeUpdate(sql);
	   sql = "INSERT INTO " + area.getAreaName() + " VALUES("
			+ recipe.getRecipeID() + ", "
			+ recipe.getRecipeName() + ")";
   }
   
   public void updateRecipeInDB(Recipe recipe) throws SQLException {
	   String sql = "UPDATE RecipeBook SET recipeName = "
			+ recipe.getRecipeName() + ", SET ServeAmount = " 
			+ recipe.getServeAmount() + ", SET CookingTime = " 
			+ recipe.getCookingTime() + ", SET PreparationTime = " 
			+ recipe.getPreparationTime() + ", SET recipeDescription = "
			+ recipe.getRecipeDescription() + " WHERE id_R = " + recipe.getRecipeID();
	   stmt.executeUpdate(sql);
   }
   
   public void updateIngredientInDB(Ingredient ingredient) throws SQLException {
	   String sql = "UPDATE IngredientBook SET ingredientName = " 
			+ ingredient.getIngredientName() + ", SET amount = "
			+ ingredient.getAmount() + ", SET unit = " 
			+ ingredient.getUnit() + ", SET description = " 
			+ ingredient.getDescription() + ", SET serveAmount = " 
			+ ingredient.getServeAmount() + " WHERE id_I = " + ingredient.getIngredientID();
		   stmt.executeUpdate(sql);
   }
   
   public void updatePreparationStepInDB(PreparationStep step) throws SQLException {
	   String sql = "UPDATE PreparationStpeBook SET content = " 
			+ step.getContent() + " WHERE id_P = " + step.getPreparationStepID();
		   stmt.executeUpdate(sql);
   }
}