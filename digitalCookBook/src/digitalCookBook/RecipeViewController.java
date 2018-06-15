package digitalCookBook;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RecipeViewController {
	private Recipe recipe;
	private GridPane grid1 = new GridPane();
	private GridPane gridE1 = new GridPane();
	private GridPane grid2 = new GridPane();
	private GridPane gridE2 = new GridPane();
	@FXML
	private TextField ctf;
	@FXML
	private TextField ptf;
	@FXML
	private Text ct;
	@FXML
	private Text pt;
	@FXML
	private Button saveBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private Text dscp;
	@FXML
	private Text recipeName;
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
		ctf.setText(ct.getText());
		ptf.setText(pt.getText());
		grid1.getColumnConstraints().add(new ColumnConstraints(200));
	    grid1.getColumnConstraints().add(new ColumnConstraints(200));
	    gridE1.getColumnConstraints().add(new ColumnConstraints(200));
	    gridE1.getColumnConstraints().add(new ColumnConstraints(200));
	    gridE1.setHgap(10);
		int row1 = 0;
		Iterator<Ingredient> iterator1 = recipe.getRequiredIngredients().iterator();
		while(iterator1.hasNext()) {			
			Ingredient ingredient = iterator1.next();
			Text name = new Text(ingredient.getIngredientName());
			TextField nameE = new TextField(name.getText());
			Text amount = new Text(Double.toString(ingredient.getQuantity()));
			TextField amountE = new TextField(amount.getText());
			Text unit = new Text(ingredient.getUnit());
			TextField unitE = new TextField(unit.getText());
			grid1.add(name, 0, row1);
			gridE1.add(nameE, 0, row1);
			grid1.add(amount, 1, row1);
			gridE1.add(amountE, 1, row1);
			grid1.add(unit, 2, row1);
			gridE1.add(unitE, 2, row1);
			row1++;
		}
		ingredientPane.setContent(grid1);

		grid2.getColumnConstraints().add(new ColumnConstraints(50));
		gridE2.getColumnConstraints().add(new ColumnConstraints(50));
		gridE2.getColumnConstraints().add(new ColumnConstraints(400));
		gridE2.getColumnConstraints().add(new ColumnConstraints(100));
		gridE2.setHgap(10);
		int row2 = 0;
		Iterator<PreparationStep> iterator2 = recipe.getPreparationSteps().iterator();
		while(iterator2.hasNext()) {			
			PreparationStep step = iterator2.next();
			Text stepNo = new Text("Step " + Integer.toString(row2+1) + ": ");
			TextField stepNoE = new TextField(Integer.toString(row2+1));
			Text content = new Text(step.getContent());
			TextField contentE = new TextField(content.getText());
			Button deleteE = new Button("Delete");
			grid2.add(stepNo, 0, row2);
			gridE2.add(stepNoE, 0, row2);
			grid2.add(content, 1, row2);
			gridE2.add(contentE, 1, row2);
			gridE2.add(deleteE, 2, row2);
			deleteE.setOnMouseClicked((new EventHandler<MouseEvent>() {    
	            @Override  
	            public void handle(MouseEvent event) { 
	            	gridE2.getChildren().remove(stepNoE);
	            	gridE2.getChildren().remove(contentE);
	            	gridE2.getChildren().remove(deleteE);
	            	}	            	
	        }));  
			row2++;
		}
		stepPane.setContent(grid2);
	}
		
	@FXML
	public void showEdit() throws IOException {
		mainApp.showEditView();
	}
	@FXML
	public void editRecipe() {
		
		ingredientPane.setContent(gridE1);
		stepPane.setContent(gridE2);
		grid1.getChildren().clear();
		grid2.getChildren().clear();
		ctf.setVisible(true);
		ptf.setVisible(true);
		saveBtn.setVisible(true);
		cancelBtn.setVisible(true);
		
	}
	@FXML
	public void cancel() {
		/*
		ingredientPane.setContent(grid1);
		stepPane.setContent(grid2);
		*/
		showDetails();
		saveBtn.setVisible(false);
		cancelBtn.setVisible(false);
		ctf.setVisible(false);
		ptf.setVisible(false);
	}
    
	public void setInformation(Recipe recipe) {
		this.recipe = recipe;
		recipeName.setText(recipe.getRecipeName());
		ct.setText(Long.toString(recipe.getCookingTime()));
		pt.setText(Long.toString(recipe.getPreparationTime()));
		this.recipe.setRequiredIngredients(recipe.getRequiredIngredients());
		this.recipe.setPreparationStep(recipe.getPreparationSteps());
		saveBtn.setVisible(false);
		cancelBtn.setVisible(false);
		ctf.setVisible(false);
		ptf.setVisible(false);
	}
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
}
