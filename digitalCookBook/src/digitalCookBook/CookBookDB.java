package digitalCookBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CookBookDB {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String jdbc = "jdbc:mysql://localhost:3306/DigitalCookBook?serverTimezone=UTC";
		Connection con = DriverManager.getConnection(jdbc, "root", "12345678");
		return con;
	}

	public static ResultSet getRecipeData() throws ClassNotFoundException, SQLException {
		String sql = "select* from recipe";
		Statement statement = getConnection().createStatement();
		ResultSet set = statement.executeQuery(sql);
		return set;
	}

	public static ResultSet getIngredientData() throws ClassNotFoundException, SQLException {
		String sql = "select* from ingredient";
		Statement statement = getConnection().createStatement();
		ResultSet set = statement.executeQuery(sql);
		return set;
	}

	public static ResultSet getAreaData() throws ClassNotFoundException, SQLException {
		String sql = "select* from area";
		Statement statement = getConnection().createStatement();
		ResultSet set = statement.executeQuery(sql);
		return set;
	}

	public static ResultSet getPreparationData() throws ClassNotFoundException, SQLException {
		String sql = "select * from preparation_step";
		Statement statement = getConnection().createStatement();
		ResultSet set = statement.executeQuery(sql);
		return set;
	}

	public static List<Ingredient> ingredientList() throws ClassNotFoundException, SQLException {
		List<Ingredient> ingredientList = new ArrayList<Ingredient>();
		ResultSet set = getIngredientData();
		while (set.next()) {
			Ingredient ingredient = new Ingredient();
			ingredient.setRecipeID((long)set.getObject(1));
			ingredient.setIngredientName(set.getString(2));
			ingredient.setQuantity((double) set.getObject(3));
			ingredient.setUnit(set.getString(4));
			if (set.getString(5) != null) {
				ingredient.setDescription(set.getString(5));
			}
			ingredientList.add(ingredient);
		}

		return ingredientList;
	}

	public static List<PreparationStep> preparationList() throws ClassNotFoundException, SQLException {
		List<PreparationStep> preparationList = new ArrayList<PreparationStep>();
		ResultSet set = getPreparationData();
		while (set.next()) {
			PreparationStep step = new PreparationStep();
			step.setRecipeID((long) set.getObject("recipe_id"));
			step.setContent(set.getString("step"));
			preparationList.add(step);
		}
		return preparationList;
	}

	public static Map<Long, Recipe> recipelist() throws ClassNotFoundException, SQLException {
		Map<Long, Recipe> recipelist = new HashMap<Long, Recipe>();
		ResultSet set = getRecipeData();
		while (set.next()) {
			Recipe recipe = new Recipe();
			ResultSet setArea = getAreaData();
			String areaName = null;
			while (setArea.next()) {
				if ((set.getObject("area_id"))!= null) {
					if ((int) set.getObject("area_id") == (int) setArea.getObject("area_id")) {
						areaName = setArea.getString("name");
						break;
					}
				}
			}

			List<Ingredient> ingredientRecipe = new ArrayList<Ingredient>();
			List<Ingredient> ingredientAll = ingredientList();
			Iterator<Ingredient> iteratorforIngre = ingredientAll.iterator();
			while (iteratorforIngre.hasNext()) {
				Ingredient ingredient = iteratorforIngre.next();
				if (ingredient.getRecipeID() == (long) set.getObject("recipe_id")) {
					ingredientRecipe.add(ingredient);
				}
			}

			recipe.setRequiredIngredients(ingredientRecipe);

			List<PreparationStep> preparationStep = new ArrayList<PreparationStep>();
			List<PreparationStep> stepAll = preparationList();
			Iterator<PreparationStep> iteratorforStep = stepAll.iterator();
			while (iteratorforStep.hasNext()) {
				PreparationStep step = iteratorforStep.next();
				if (step.getRecipeID() == (long) set.getObject("recipe_id")) {
					preparationStep.add(step);
				}
			}

			recipe.setPreparationStep(preparationStep);

			recipe.setIdRecipe((long) set.getObject("recipe_id"));
			recipe.setRecipeName(set.getString("name"));
			recipe.setServeAmount((int) set.getObject("serveamount"));
			recipe.setCookingTime((long) set.getObject("cookingTime"));
			recipe.setPreparationTime((long) set.getObject("preparationTime"));
			recipe.setAreaName(areaName);
			recipe.setRequiredIngredients(ingredientRecipe);
			recipe.setPreparationStep(preparationStep);

			recipelist.put(recipe.getIdRecipe(), recipe);
		}

		return recipelist;

	}

	public static Map<String, Area> areaList() throws ClassNotFoundException, SQLException {
		Map<String, Area> area = new HashMap<String, Area>();
		ResultSet set = getAreaData();
		while (set.next()) {
			Area area_instance = new Area();
			String areaName = set.getString("name");
			int id = (int) set.getObject("area_id");
			area_instance.setAreaName(areaName);
			area_instance.setIdArea(id);
			area.put(areaName, area_instance);
		}
		return area;
	}

	public static void addRecipeDB(Recipe recipe) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		String sql = "insert into recipe(name,serveamount,preparationTime,cookingTime,area_id) values (?,?,?,?,?)";
		PreparedStatement recipeStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		recipeStatement.setString(1, recipe.getRecipeName());
		recipeStatement.setInt(2, recipe.getServeAmount());
		recipeStatement.setLong(3, recipe.getPreparationTime());
		recipeStatement.setLong(4, recipe.getCookingTime());
		recipeStatement.setInt(5, areaList().get(recipe.getAreaName()).getIdArea());
		recipeStatement.executeUpdate();
		ResultSet set = recipeStatement.getGeneratedKeys();
		if (set.next()) {
			recipe.setIdRecipe(set.getInt(1));
		}

	}

	public static void deleteRecipeDB(Recipe recipe) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		String sql = "delete from recipe where recipe_id = ?";
		PreparedStatement recipeStatement = con.prepareStatement(sql);
		recipeStatement.setLong(1, recipe.getIdRecipe());
		recipeStatement.executeUpdate();
	}

	public static void addNewAreaDB(Area area) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		String sql = "insert into area(name) values (?)";
		PreparedStatement areaStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		areaStatement.setString(1, area.getAreaName());
		areaStatement.executeUpdate();
		ResultSet set = areaStatement.getGeneratedKeys();
		if (set.next()) {
			area.setIdArea(set.getInt(1));
		}
	}

	public static void deleteAreaDB(Area area) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		String sql = "delete from area where name = ?";
		PreparedStatement areaStatement = con.prepareStatement(sql);
		areaStatement.setString(1, area.getAreaName());
		areaStatement.executeUpdate();
	}

	public static void addIngredientDB(Ingredient ingredient) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		String sql = "insert into ingredient(recipe_id,name,quantity,unit,description) values (?,?,?,?,?)";
		PreparedStatement ingredientStatement = con.prepareStatement(sql);
		ingredientStatement.setLong(1, ingredient.getRecipeID());
		ingredientStatement.setString(2, ingredient.getIngredientName());
		ingredientStatement.setDouble(3, ingredient.getQuantity());
		ingredientStatement.setString(4, ingredient.getUnit());
		ingredientStatement.setString(5, ingredient.getDescription());
		ingredientStatement.executeUpdate();
	}

	public static void deleteIngredientDB(Ingredient ingredient) throws SQLException, ClassNotFoundException {
		Connection con = getConnection();
		String sql = "delete from ingredient where recipe_id= ? and name = ?";
		PreparedStatement ingredientStatement = con.prepareStatement(sql);
		ingredientStatement.setLong(1, ingredient.getRecipeID());
		ingredientStatement.setString(2, ingredient.getIngredientName());
		ingredientStatement.executeUpdate();
	}

	public static void addPreparationDB(PreparationStep step) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		String sql = "insert into preparation_step(recipe_id,step) values (?,?)";
		PreparedStatement preparationStatement = con.prepareStatement(sql);
		preparationStatement.setLong(1, step.getRecipeID());
		preparationStatement.setString(2, step.getContent());
		preparationStatement.executeUpdate();
	}

	public static void deletePreparationDB(PreparationStep step) throws SQLException, ClassNotFoundException {
		Connection con = getConnection();
		String sql = "delete from preparation_step where recipe_id= ? and step = ?";
		PreparedStatement preparationStatement = con.prepareStatement(sql);
		preparationStatement.setLong(1, step.getRecipeID());
		preparationStatement.setString(2, step.getContent());
		preparationStatement.executeUpdate();
	}

	public static void updateCookingTime(Recipe recipe, long time) throws SQLException, ClassNotFoundException {
		Connection con = getConnection();
		String sql = "update recipe set cookingTime=? where recipe_id=?";
		PreparedStatement timeStatement = con.prepareStatement(sql);
		timeStatement.setLong(1, time);
		timeStatement.setLong(2, recipe.getIdRecipe());
		timeStatement.executeUpdate();
	}
	
	public static void updatePreparationTime(Recipe recipe, long time) throws SQLException, ClassNotFoundException {
		Connection con = getConnection();
		String sql = "update recipe set preparationTime=? where recipe_id=?";
		PreparedStatement timeStatement = con.prepareStatement(sql);
		timeStatement.setLong(1, time);
		timeStatement.setLong(2, recipe.getIdRecipe());
		timeStatement.executeUpdate();
	}
	
	/**
	 * use the 'update sql'
	 * @param recipeNew
	 * @param ingredientOld
	 * @param preparationOid
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	
	public static void updateRecipe(Recipe recipeNew,List<Ingredient> ingredientOld, List<PreparationStep> preparationOld) throws SQLException, ClassNotFoundException{
		Connection con = getConnection();
		String sql = "update recipe set name = ?, area_id = ?, where recipe_id = ?";
		PreparedStatement recipeStatement = con.prepareStatement(sql);
		recipeStatement.setString(1,recipeNew.getRecipeName());
		recipeStatement.setInt(2,recipeNew.getAreaID());
		recipeStatement.setLong(3, recipeNew.getIdRecipe());
		updateCookingTime(recipeNew,recipeNew.getCookingTime());
		updatePreparationTime(recipeNew,recipeNew.getPreparationTime());
		updateIngredientList(ingredientOld,recipeNew.getRequiredIngredients());
		updatePreparationStepList(preparationOld,recipeNew.getPreparationSteps());
		
	}
	
	public static void updateIngredient(Ingredient ingredientOld,Ingredient ingredientNew)throws SQLException,ClassNotFoundException{
		Connection con = getConnection();
		String sql = "update ingredient set name = ?, quantity = ?, unit = ?, description = ?, where recipe_id = ? and name = ?";
		PreparedStatement ingredientStatement = con.prepareStatement(sql);
		ingredientStatement.setString(1, ingredientNew.getIngredientName());
		ingredientStatement.setDouble(2,ingredientNew.getQuantity());
		ingredientStatement.setString(3, ingredientNew.getUnit());
		ingredientStatement.setString(4, ingredientNew.getDescription());
		ingredientStatement.setLong(5, ingredientOld.getRecipeID());
		ingredientStatement.setString(6, ingredientOld.getIngredientName());
	}
	
	public static void updateIngredientList(List<Ingredient> ingredientListOld,List<Ingredient>ingredientListNew) throws ClassNotFoundException, SQLException {
		Iterator<Ingredient> listOld = ingredientListOld.iterator();
		Iterator<Ingredient> listNew = ingredientListNew.iterator();
		while(listOld.hasNext()) {
			Ingredient ingredientOld = listOld.next();
			Ingredient ingredientNew = listNew.next();
			updateIngredient(ingredientOld,ingredientNew);
		}
	}
	
	public static void updatePreparationStep(PreparationStep stepOld, PreparationStep stepNew) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		String sql = "update preparation_step set step = ? where recipe_id = ? and step = ? ";
		PreparedStatement stepStatement = con.prepareStatement(sql);
		stepStatement.setString(1,stepNew.getContent());
		stepStatement.setLong(2, stepOld.getRecipeID());
		stepStatement.setString(3,stepOld.getContent());
	}
	
	public static void updatePreparationStepList(List<PreparationStep> stepOld, List<PreparationStep> stepNew) throws ClassNotFoundException, SQLException {
		Iterator<PreparationStep> listOld = stepOld.iterator();
		Iterator<PreparationStep> listNew = stepNew.iterator();
		while(listOld.hasNext()) {
			PreparationStep step_Old = listOld.next();
			PreparationStep step_New = listNew.next();
			updatePreparationStep(step_Old,step_New);
		}
	}
	
	
	/*
	public static void updateRecipe(Recipe recipeNew) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		String sql = "delete from recipe where recipe_id = ?";
		PreparedStatement recipeStatement = con.prepareStatement(sql);
		recipeStatement.setLong(1, recipeNew.getIdRecipe());
		
	}
	*/
	
	
	

}
