package digitalCookBook;

import java.io.IOException;
import java.sql.SQLException;

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
    private Stage searchStage;
    private Stage recipeStage;
	private Stage AreaStage;
    static Recipe testRecipe1;
    static Recipe testRecipe2;
    static CookBook testcb;
    
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
	        Scene scene = new Scene(cbView);
	        primaryStage.setScene(scene);
	        primaryStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	 public void showSearchView() throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchGUI.fxml"));
	    	searchStage = new Stage();
	    	AnchorPane searchView = loader.load();
	    	searchStage.setTitle("Search Rusults");
	    	
	    	//searchGUI.initModality(Modality.WINDOW_MODAL);
	    	//searchGUI.initOwner(primaryStage);
	    	SearchViewController controller = loader.getController();
	        controller.setMainApp(this);	
	        controller.showRecipe();
	        controller.setCb(testcb);
	        controller.setSearchString("Gong Bao Jiba");
	    	Scene scene = new Scene(searchView);
	    	searchStage.setScene(scene);
	    	searchStage.showAndWait();
	    	
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
	 public Stage getSearchStage() {
			return searchStage;
		}
	 public Stage getRecipeStage() {
			return recipeStage;
		}
	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
	     testRecipe1 = createSuanLaFen();
	     testRecipe2 = createGongBaoJiding();
	     testcb = new CookBook("cookbook");
	     testcb.addRecipe(testRecipe1);
	     testcb.addRecipe(testRecipe2);
		 launch(args);

	    }
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
	}


