package digitalCookBook;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;
    private Scene searchScene;
    private Stage recipeStage;
	private Scene areaScene;
	private Stage editStage;
 
    public Stage getEditStage() {
		return editStage;
	}
	public void setEditStage(Stage editStage) {
		this.editStage = editStage;
	}
	public Scene getAreaScene() {
		return areaScene;
	}
	public void setAreaScene(Scene areaScene) {
		this.areaScene = areaScene;
	}
	private static CookBook testcb;
    private static CookBookDB testdb;
    
	
	//private BorderPane rootLayout;
	@Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("My Cook Book");
        
        //initRootLayout();

        showCookBookView();
       


    }
	public void showCookBookView() {
	    try {
	        
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("CookBookGUI.fxml"));
	        AnchorPane cbView = (AnchorPane) loader.load();	       
	        CookBookViewController controller = loader.getController();
	        controller.setMainApp(this);
	        controller.setEnterAction();
	        Scene scene = new Scene(cbView);
	        primaryStage.setScene(scene);
	        primaryStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	 public void showSearchView() throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchGUI.fxml"));
	    	
	    	AnchorPane searchView = loader.load();
	    	searchScene = new Scene(searchView);
	    	searchScene.getStylesheets().add(getClass().getResource("SearchStyle.css").toExternalForm());
	    	//searchStage.setTitle("Search Rusults");
	    	
	    	//searchGUI.initModality(Modality.WINDOW_MODAL);
	    	//searchGUI.initOwner(primaryStage);
	    	SearchViewController controller = loader.getController();
	        controller.setMainApp(this);	
	        controller.setCb(testcb);
	        controller.setSearchField1(CookBookViewController.searchString);
	        controller.setSearchString(CookBookViewController.searchString);
	        controller.showRecipe();
	        
	        
	    	//Scene scene = new Scene(searchView);
	    	primaryStage.setScene(searchScene);
	    	primaryStage.show();
	    	
	    }
	 public void showRecipeView(Recipe recipe) throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("RecipeGUI.fxml"));
		 recipeStage = new Stage();
		 AnchorPane recipeView = loader.load();
		 recipeStage.setTitle("Recipe");
		 RecipeViewController controller = loader.getController();
		 controller.setMainApp(this);
		 controller.setInformation(recipe);
		 controller.showDetails();
		 Scene scene = new Scene(recipeView);
		 recipeStage.setScene(scene);
		 recipeStage.showAndWait();
	 }
	 
	 public void showAreaView() throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("AreaGUI.fxml"));
		 AnchorPane areaView = loader.load();
		 areaScene = new Scene(areaView);
		 AreaViewController controller = loader.getController();
		 controller.setMainApp(this);
		 primaryStage.setScene(areaScene);
		 primaryStage.show();
	 }
	 public void showEditView() throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("EditGUI.fxml"));
		 editStage = new Stage();
		 AnchorPane editView = loader.load();
		 editStage.setTitle("edit your recipe");
		 EditViewController controller = loader.getController();
		 controller.setMainApp(this);
		 controller.showFirst();
		 Scene scene = new Scene(editView);
		 editStage.setScene(scene);
		 editStage.show();
		 
	 }
	 
	 public Scene getSearchStage() {
			return searchScene;
		}
	 public Stage getRecipeStage() {
			return recipeStage;
		}
	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
	     
		 testcb = new CookBook("cookbook");
		 /*
	     testcb.addRecipe(createSuanLaFen());
	     testcb.addRecipe(createGongBaoJiding());
	     testcb.addRecipe(createHongShaoRou());
		*/
	     
	     
		 launch(args);
		 

	    }
	 
	 
	 
	 
	 
	 
	 
	 public static CookBook getTestcb() {
			return testcb;
		}
		public static void setTestcb(CookBook testcb) {
			MainApp.testcb = testcb;
		}
		public static CookBookDB getTestdb() {
			return testdb;
		}
		public static void setTestdb(CookBookDB testdb) {
			MainApp.testdb = testdb;
		}
		/*
	 private static Recipe createSuanLaFen() throws ClassNotFoundException, SQLException {
			Recipe recipe = new Recipe("Suan La jiba", "Sichuan Dish", 2);		
			
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
	 private static Recipe createGongBaoJiding() throws ClassNotFoundException, SQLException {
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
	 private static Recipe createHongShaoRou() throws ClassNotFoundException, SQLException {
			Recipe recipe = new Recipe("Hong Shao Rou", "Hunan Dish", 4);		
			
			recipe.addIngredient(new Ingredient("pork belly", 0.5, "kg", "cut into 2cm pieces"));
			recipe.addIngredient(new Ingredient("cooking oil", 2.0, "tablespoon"));
			recipe.addIngredient(new Ingredient("brown sugar", 1.0, "tablespoon"));
			recipe.addIngredient(new Ingredient("Shaoxin rice wine", 3.0, "tablespoon"));
			recipe.addIngredient(new Ingredient("light soy sauce", 1.0, "tablespoon"));
			recipe.addIngredient(new Ingredient("dark soy sauce", 0.5, "tablespoon"));
			recipe.addIngredient(new Ingredient("chicken stock or water", 2.0, "cups"));
			
			recipe.addPreparationStep(new PreparationStep("Bring a pot of water to a boil and blanch the pork for a couple minutes."));
			recipe.addPreparationStep(new PreparationStep("Take the pork out of the pot and set aside."));
			recipe.addPreparationStep(new PreparationStep("Over low heat, add oil and sugar to your wok."));
			recipe.addPreparationStep(new PreparationStep("Melt the sugar slightly and add the pork."));
			recipe.addPreparationStep(new PreparationStep("Raise the heat to medium and cook until the pork is lightly browned."));
			recipe.addPreparationStep(new PreparationStep("Turn the heat back down to low and add cooking wine, light soy sauce, dark soy sauce, and chicken stock."));
			recipe.addPreparationStep(new PreparationStep("Cover and simmer for about 60 minutes to 90 minutes until pork is fork tender."));
			recipe.addPreparationStep(new PreparationStep("Every 5-10 minutes, stir to prevent burning and add water if it gets too dry."));
			recipe.addPreparationStep(new PreparationStep("Once the pork is fork tender, if there is still a lot of visible liquid, uncover the wok, turn up the heat, and stir continuously the sauce has reduced to a glistening coating."));
			
			recipe.setPreparationTime(5);
			recipe.setCookingTime(100);
			
			return recipe;
		} 
		*/
	}


