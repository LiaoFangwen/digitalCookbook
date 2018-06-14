package digitalCookBook;

import java.util.Iterator;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class RecipeViewController {
	private Recipe recipe;
	
	
	@FXML
	private Label recipeNameLabel;
	@FXML
	private AnchorPane mainPane;
	@FXML
	private Label backLabel;
	@FXML
	private Label editLabel;
	@FXML
	private MainApp mainApp;
	@FXML
	private ScrollPane ingredientPane;
	@FXML
	private ScrollPane stepPane;
	@FXML
    public void backToMain() {
    	mainApp.getRecipeStage().close();
    }
	
	@FXML
	public void showDetails() {
		
		VBox vb1 = new VBox();
		Iterator<Ingredient> iterator1 = recipe.getRequiredIngredients().iterator();
		while(iterator1.hasNext()) {
			GridPane grid = new GridPane();
			Ingredient ingredient = iterator1.next();
			Label name = new Label(ingredient.getIngredientName());
			Label amount = new Label(Double.toString(ingredient.getQuantity()));
			Label unit = new Label(ingredient.getUnit());
			grid.add(name, 0, 0);
			grid.add(amount, 1, 0);
			grid.add(unit, 2, 0);
	        vb1.getChildren().add(grid);  
	    }
		ingredientPane.setContent(vb1);
		
		VBox vb2 = new VBox();
		Iterator<PreparationStep> iterator2 = recipe.getPreparationSteps().iterator();
		while(iterator2.hasNext()) {
			GridPane grid = new GridPane();
			PreparationStep step = iterator2.next();
			Label content = new Label(step.getContent());
			grid.add(content, 0, 0);
	        vb2.getChildren().add(grid);  
	    }
	    
		stepPane.setContent(vb2);
		
    }
    
	public void setInformation(Recipe recipe) {
		this.recipe = recipe;
		recipeNameLabel.setText(recipe.getRecipeName());
		this.recipe.setRequiredIngredients(recipe.getRequiredIngredients());
		this.recipe.setPreparationStep(recipe.getPreparationSteps());
	}
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
}
