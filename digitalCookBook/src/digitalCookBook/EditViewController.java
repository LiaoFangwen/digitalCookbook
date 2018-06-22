package digitalCookBook;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EditViewController {
	private Recipe recipe = new Recipe();
	private GridPane gridi = new GridPane();
	private GridPane grids = new GridPane();
	private int row1 = 0;
	private int row2 = 0;
	private List<Ingredient> ingList = new ArrayList<Ingredient>();
	private List<PreparationStep> stepList = new ArrayList<PreparationStep>();
	private List<Ingredient> ingS = new ArrayList<Ingredient>();
	private List<PreparationStep> stepS = new ArrayList<PreparationStep>();
	
	@FXML
	private AnchorPane mainPane;
	@FXML
	private TextField recipeName;
	@FXML
	private TextField areaName;
	@FXML
	private TextField serveAmount;
	@FXML
	private Button saveRecipe;
	@FXML
	private Button cancel;
	@FXML
	private TextField preparationTime;
	@FXML
	private TextField cookingTime;
	@FXML
	private Button deleteRecipe;
	@FXML
	private ScrollPane ingScroll;
	@FXML
	private ScrollPane stepScroll;
	@FXML
	private Label backLabel;
	@FXML
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
	@FXML
    public void backToMain() {
    	mainApp.getEditStage().close();
    }
	
    public EditViewController() {
    	
    }
    public void addNewIng(int focus) {
    	gridi.getChildren().clear();
    	Iterator<Ingredient> iterator1 = ingList.iterator();
    	row1 = 0;
		while(iterator1.hasNext()) {	
			Ingredient ingredient = iterator1.next();
			TextField materialName = new TextField(ingredient.getIngredientName());
			TextField amount = new TextField((Double.toString(ingredient.getQuantity())));
			TextField unit = new TextField(ingredient.getUnit());
			Button deleteE = new Button("Delete");
			Button addS = new Button("Add");
			gridi.add(materialName, 0, row1);
			gridi.add(amount, 1, row1);
			gridi.add(unit, 2, row1);
			gridi.add(deleteE, 3, row1);
			gridi.add(addS, 4, row1);
			int index = gridi.getRowIndex(deleteE);
			deleteE.setOnMouseClicked((new EventHandler<MouseEvent>() {    
		            @Override  
		            public void handle(MouseEvent event) { 	            	
		            	for(int i = 0; i<row1; i++) {
		        			TextField t1 = (TextField)gridi.getChildren().get(i*5);
		        			TextField t2 = (TextField)gridi.getChildren().get(i*5+1);
		        			TextField t3 = (TextField)gridi.getChildren().get(i*5+2);
		        			ingList.set(i, new Ingredient(t1.getText(), Double.parseDouble(t2.getText()), t3.getText()));
		        		}
		            	ingList.remove(index);
		            	addNewIng(index-1);
		            }	            	
		        }));
    	
		
			addS.setOnMouseClicked((new EventHandler<MouseEvent>() {    
	            @Override  
	            public void handle(MouseEvent event) {
	            	for(int i = 0; i<row1; i++) {
	        			TextField t1 = (TextField)gridi.getChildren().get(i*5);
	        			TextField t2 = (TextField)gridi.getChildren().get(i*5+1);
	        			TextField t3 = (TextField)gridi.getChildren().get(i*5+2);
	        			ingList.set(i, new Ingredient(t1.getText(), Double.parseDouble(t2.getText()), t3.getText()));
	        		}
	            	ingList.add(index+1, new Ingredient("", 0, ""));
	            	addNewIng(index+1);
	            }	            	
	        }));  
			row1++;	
		}
		gridi.getChildren().get(focus*5).requestFocus();
    	/*
    	TextField material = new TextField();
    	TextField amount = new TextField();
    	TextField unit = new TextField();
    	Button delete = new Button("delete");
    	delete.setOnMouseClicked((new EventHandler<MouseEvent>() {    
            @Override  
            public void handle(MouseEvent event) { 
            	gridi.getChildren().remove(material);
            	gridi.getChildren().remove(amount);
            	gridi.getChildren().remove(unit);
            	gridi.getChildren().remove(delete);
            	row1--;            	            
            }		
        })); 
    	gridi.add(material, 0, row1);
    	gridi.add(amount, 1, row1);
    	gridi.add(unit, 2, row1);
    	gridi.add(delete, 3, row1);
    	row1++;
    	*/
    }
    
    public void addNewStep(int focus) {
    	grids.getChildren().clear();
		row2 = 0;
		Iterator<PreparationStep> iterator2 = stepList.iterator();
		while(iterator2.hasNext()) {			
			PreparationStep step = iterator2.next();
			Text stepNoE = new Text(Integer.toString(row2+1));
			TextField contentE = new TextField(step.getContent());
			Button deleteE = new Button("Delete");
			Button addS = new Button("Add");
			grids.add(stepNoE, 0, row2);
			grids.add(contentE, 1, row2);
			grids.add(deleteE, 2, row2);
			grids.add(addS, 3, row2);
			int index = grids.getRowIndex(deleteE);
			deleteE.setOnMouseClicked((new EventHandler<MouseEvent>() {    
	            @Override  
	            public void handle(MouseEvent event) { 	            	
	            	for(int i = 0; i<row2; i++) {
	        			TextField t = (TextField)grids.getChildren().get(i*4+1);
	        			stepList.set(i, new PreparationStep(t.getText()));
	        		}
	            	stepList.remove(index);
	            	addNewStep(index-1);
	            }	            	
	        }));
			addS.setOnMouseClicked((new EventHandler<MouseEvent>() {    
	            @Override  
	            public void handle(MouseEvent event) {
	            	for(int i = 0; i<row2; i++) {
	        			TextField t = (TextField)grids.getChildren().get(i*4+1);
	        			stepList.set(i, new PreparationStep(t.getText()));
	        		}
	            	stepList.add(index+1, new PreparationStep(""));
	            	addNewStep(index+1);	
	            }	            	
	        }));  
		row2++;
		}
		grids.getChildren().get(focus*4+1).requestFocus();
    	/*
    	Label stepNo = new Label("Step " + Integer.toString(row2+1) + ": ");
    	
    	vbs.getChildren().add(stepNo);
    	
    	TextField emptyStep = new TextField();
    	Button delete = new Button("delete");
    	
    	delete.setOnMouseClicked((new EventHandler<MouseEvent>() {    
            @Override  
            public void handle(MouseEvent event) { 
            	grids.getChildren().remove(emptyStep);
            	grids.getChildren().remove(delete);
            	vbs.getChildren().remove(row2-1);
            	row2--;            	            
            }		
        }));  
        
    	
    	grids.add(emptyStep, 0, row2);
    	grids.add(delete, 1, row2);
    	row2++;
    	*/
    }
    public void showFirst() {
    	recipeName.requestFocus();
    	ingList.add(new Ingredient("", 0, ""));
    	addNewIng(0);
    	ingScroll.setContent(gridi);
    	stepList.add(new PreparationStep(""));
    	addNewStep(0);
    	stepScroll.setContent(grids);
    }
    @FXML
	public void save() throws NumberFormatException, ClassNotFoundException, SQLException {
		for(int i = 0; i<row1*5; i = i+5) {
			TextField t1 = (TextField)gridi.getChildren().get(i);
			TextField t2 = (TextField)gridi.getChildren().get(i+1);
			TextField t3 = (TextField)gridi.getChildren().get(i+2);
			ingS.add(new Ingredient(t1.getText(), Double.parseDouble(t2.getText()), t3.getText()));
		}
		for(int i = 0; i<row2*4; i = i+4) {
			TextField t = (TextField)grids.getChildren().get(i+1);
			stepS.add(new PreparationStep(t.getText()));
		}
		recipe.setRecipeName(recipeName.getText());
		recipe.setAreaName(areaName.getText());
		recipe.setCookingTime(Long.parseLong(cookingTime.getText()));
		recipe.setPreparationTime(Long.parseLong(preparationTime.getText()));
		recipe.setServeAmount(Integer.parseInt(serveAmount.getText()));
		recipe.setPreparationStep(stepS);
		recipe.setRequiredIngredients(ingS);
		CookBook.addRecipe(recipe);
	}
    @FXML
    public void cancel() {
    	ingList.clear();
    	stepList.clear();
    	showFirst();
    }
}
    
