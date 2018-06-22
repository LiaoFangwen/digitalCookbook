package digitalCookBook;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
	private int row1 = 0;
	private int row2 = 0;
	private int rowE1 = 0;
	private int rowE2 = 0;
	private List<Ingredient> ingList = new ArrayList<Ingredient>();
	private List<PreparationStep> stepList = new ArrayList<PreparationStep>();
	private List<Ingredient> ingE = new ArrayList<Ingredient>();
	private List<PreparationStep> stepE = new ArrayList<PreparationStep>();
	@FXML
	private TextField ctf;
	@FXML
	private TextField ptf;
	@FXML
	private TextField rnf;
	@FXML
	private Text ar;
	@FXML
	private TextField arf;
	@FXML
	private Text sa;
	@FXML
	private TextField saf;
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
	private Text rn;
	@FXML
	private TextField csf;
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
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
	public void setInformation(Recipe recipe) {
		this.recipe = recipe;
		rn.setText(recipe.getRecipeName());
		ct.setText(Long.toString(recipe.getCookingTime()));
		pt.setText(Long.toString(recipe.getPreparationTime()));
		ar.setText(recipe.getAreaName());
		sa.setText(Integer.toString(recipe.getServeAmount()));
		this.recipe.setRequiredIngredients(recipe.getRequiredIngredients());
		this.recipe.setPreparationStep(recipe.getPreparationSteps());
		Iterator<Ingredient> iteratorI = recipe.getRequiredIngredients().iterator();
		while(iteratorI.hasNext()) {
			ingE.add(iteratorI.next());
		}
		Iterator<PreparationStep> iteratorS = recipe.getPreparationSteps().iterator();
		while(iteratorS.hasNext()) {
			stepE.add(iteratorS.next());
		}
		saveBtn.setVisible(false);
		cancelBtn.setVisible(false);
		ctf.setVisible(false);
		ptf.setVisible(false);
		rnf.setVisible(false);
		arf.setVisible(false);
		saf.setVisible(false);
		
	}
	public void showDetails() {
		grid1.getColumnConstraints().add(new ColumnConstraints(200));
	    grid1.getColumnConstraints().add(new ColumnConstraints(100));
	    Iterator<Ingredient> iterator1 = recipe.getRequiredIngredients().iterator();
		while(iterator1.hasNext()) {			
			Ingredient ingredient = iterator1.next();
			Text name = new Text(ingredient.getIngredientName());
			Text amount = new Text(Double.toString(ingredient.getQuantity()));
			Text unit = new Text(ingredient.getUnit());
			grid1.add(name, 0, row1);
			grid1.add(amount, 1, row1);
			grid1.add(unit, 2, row1);
			row1++;		
		}
		ingredientPane.setContent(grid1);
		grid2.getColumnConstraints().add(new ColumnConstraints(50));
		Iterator<PreparationStep> iterator2 = recipe.getPreparationSteps().iterator();
		while(iterator2.hasNext()) {			
			PreparationStep step = iterator2.next();
			Text stepNo = new Text("Step " + Integer.toString(row2+1) + ": ");
			Text content = new Text(step.getContent());
			grid2.add(stepNo, 0, row2);
			grid2.add(content, 1, row2);
			row2++;			
		}
		stepPane.setContent(grid2);
	}
	@FXML
	public void changeServeAmount() {
		int newServe = Integer.parseInt(csf.getText().trim());
		int row = 0;
		Iterator<Ingredient> iteratorN = recipe.getRequiredIngredients().iterator();
		while(iteratorN.hasNext() && row<row1) {
			Ingredient ing = iteratorN.next();
			ing.setServeAmount(newServe);
			ing.setTotalAmount();
			Text t = (Text) grid1.getChildren().get(3*row+1);
			t.setText(Double.toString(ing.getTotalAmount()));
			row++;
		}
	}
	@FXML
	public void editRecipe() {
		ctf.setText(ct.getText());
		ptf.setText(pt.getText());
		rnf.setText(rn.getText());
		arf.setText(ar.getText());
		saf.setText(sa.getText());
		ctf.setVisible(true);
		ptf.setVisible(true);
		rnf.setVisible(true);
		saveBtn.setVisible(true);
		cancelBtn.setVisible(true);
		arf.setVisible(true);
		saf.setVisible(true);
		rnf.requestFocus();
		gridE1.getColumnConstraints().add(new ColumnConstraints(200));
		gridE1.getColumnConstraints().add(new ColumnConstraints(100));
		gridE1.getColumnConstraints().add(new ColumnConstraints(100));
		gridE1.getColumnConstraints().add(new ColumnConstraints(100));
		gridE2.getColumnConstraints().add(new ColumnConstraints(50));
		gridE2.getColumnConstraints().add(new ColumnConstraints(400));
		gridE2.getColumnConstraints().add(new ColumnConstraints(50));
		gridE1.getChildren().clear();
		gridE2.getChildren().clear();
		
		editIng(0);
		editStep(0);
		ingredientPane.setContent(gridE1);
		stepPane.setContent(gridE2);	
	}
	public void editIng(int focus) {
		gridE1.getChildren().clear();
		rowE1 = 0;
		Iterator<Ingredient> iterator1 = ingE.iterator();
		while(iterator1.hasNext()) {			
			Ingredient ingredient = iterator1.next();
			TextField nameE = new TextField(ingredient.getIngredientName());
			TextField amountE = new TextField((Double.toString(ingredient.getQuantity())));
			TextField unitE = new TextField(ingredient.getUnit());
			Button deleteE = new Button("Delete");
			Button addS = new Button("Add");
			gridE1.add(nameE, 0, rowE1);
			gridE1.add(amountE, 1, rowE1);
			gridE1.add(unitE, 2, rowE1);
			gridE1.add(deleteE, 3, rowE1);
			gridE1.add(addS, 4, rowE1);
			int index = gridE1.getRowIndex(deleteE);
			deleteE.setOnMouseClicked((new EventHandler<MouseEvent>() {    
	            @Override  
	            public void handle(MouseEvent event) { 	            	
	            	for(int i = 0; i<rowE1; i++) {
	        			TextField t1 = (TextField)gridE1.getChildren().get(i*5);
	        			TextField t2 = (TextField)gridE1.getChildren().get(i*5+1);
	        			TextField t3 = (TextField)gridE1.getChildren().get(i*5+2);
	        			ingE.set(i, new Ingredient(t1.getText(), Double.parseDouble(t2.getText()), t3.getText()));
	        		}
	            	ingE.remove(index);
	            	editIng(index);
	            }	            	
	        }));
			addS.setOnMouseClicked((new EventHandler<MouseEvent>() {    
	            @Override  
	            public void handle(MouseEvent event) {
	            	for(int i = 0; i<rowE1; i++) {
	        			TextField t1 = (TextField)gridE1.getChildren().get(i*5);
	        			TextField t2 = (TextField)gridE1.getChildren().get(i*5+1);
	        			TextField t3 = (TextField)gridE1.getChildren().get(i*5+2);
	        			ingE.set(i, new Ingredient(t1.getText(), Double.parseDouble(t2.getText()), t3.getText()));
	        		}
	            	ingE.add(index+1, new Ingredient("", 0, ""));
	            	editIng(index+1);	
	            }	            	
	        }));  
			
			rowE1++;	
		}
		gridE1.getChildren().get(focus*5).requestFocus();
	}
	public void editStep(int focus) {
		gridE2.getChildren().clear();
		rowE2 = 0;
		Iterator<PreparationStep> iterator2 = stepE.iterator();
		while(iterator2.hasNext()) {			
			PreparationStep step = iterator2.next();
			Text stepNoE = new Text(Integer.toString(rowE2+1));
			TextField contentE = new TextField(step.getContent());
			Button deleteE = new Button("Delete");
			Button addS = new Button("Add");
			gridE2.add(stepNoE, 0, rowE2);
			gridE2.add(contentE, 1, rowE2);
			gridE2.add(deleteE, 2, rowE2);
			gridE2.add(addS, 3, rowE2);
			int index = gridE2.getRowIndex(deleteE);
			deleteE.setOnMouseClicked((new EventHandler<MouseEvent>() {    
	            @Override  
	            public void handle(MouseEvent event) { 	            	
	            	for(int i = 0; i<rowE2; i++) {
	        			TextField t = (TextField)gridE2.getChildren().get(i*4+1);
	        			stepE.set(i, new PreparationStep(t.getText()));
	        		}
	            	stepE.remove(index);
	            	editStep(index);
	            }	            	
	        }));
			addS.setOnMouseClicked((new EventHandler<MouseEvent>() {    
	            @Override  
	            public void handle(MouseEvent event) {
	            	for(int i = 0; i<rowE2; i++) {
	        			TextField t = (TextField)gridE2.getChildren().get(i*4+1);
	        			stepE.set(i, new PreparationStep(t.getText()));
	        		}
	            	stepE.add(index+1, new PreparationStep(""));
	            	editStep(index+1);	
	            }	            	
	        }));  
		rowE2++;
		}
		gridE2.getChildren().get(focus*4+1).requestFocus();
	}
	@FXML
	public void cancel() {
		grid1.getChildren().clear();
		grid2.getChildren().clear();
		row1 = 0;
		row2 = 0;
		showDetails();
		saveBtn.setVisible(false);
		cancelBtn.setVisible(false);
		ctf.setVisible(false);
		ptf.setVisible(false);
		rnf.setVisible(false);
		arf.setVisible(false);
		saf.setVisible(false);
	}
	@FXML
	public void save() throws NumberFormatException, ClassNotFoundException, SQLException {
		for(int i = 0; i<rowE1*5; i = i+5) {
			TextField t1 = (TextField)gridE1.getChildren().get(i);
			TextField t2 = (TextField)gridE1.getChildren().get(i+1);
			TextField t3 = (TextField)gridE1.getChildren().get(i+2);
			ingList.add(new Ingredient(t1.getText(), Double.parseDouble(t2.getText()), t3.getText()));
		}
		for(int i = 0; i<rowE2*4; i = i+4) {
			TextField t = (TextField)gridE2.getChildren().get(i+1);
			stepList.add(new PreparationStep(t.getText()));
		}
		
		Recipe newRecipe = new Recipe();
		newRecipe.setRecipeName(rnf.getText());
		newRecipe.setAreaName(arf.getText());
		newRecipe.setCookingTime(Long.parseLong(ctf.getText()));
		newRecipe.setPreparationTime(Long.parseLong(ptf.getText()));
		newRecipe.setServeAmount(Integer.parseInt(saf.getText()));
		newRecipe.setPreparationStep(stepList);
		newRecipe.setRequiredIngredients(ingList);
		CookBook.editRecipe(recipe, newRecipe);	
		long i = recipe.getIdRecipe();
		recipe = CookBook.getRecipeByID(i);
		cancel();
	}
}
