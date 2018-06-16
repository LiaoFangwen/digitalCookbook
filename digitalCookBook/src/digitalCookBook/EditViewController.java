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

public class EditViewController {
	private Recipe recipe = new Recipe();
	private GridPane gridi = new GridPane();
	private GridPane grids = new GridPane();
	private FlowPane ingFlow = new FlowPane();
	private FlowPane stepFlow = new FlowPane();
	private VBox vbi = new VBox();
	private VBox vbs = new VBox();
	private int row1 = 0;
	private int row2 = 0;
	private List<Ingredient> ingList = new ArrayList<Ingredient>();
	private List<PreparationStep> stepList = new ArrayList<PreparationStep>();
	
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
	private Button addOneMoreIng;
	@FXML
	private Button addOneMoreStep;
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
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
	
    public EditViewController() {
    	
    }
    @FXML 
    public void addNewIng() {
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
    }
    @FXML
    public void addNewStep() {
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
    }
    public void showFirst() {
    	addNewIng();
    	ingScroll.setContent(gridi);
    	stepFlow.getChildren().add(vbs);
    	vbs.setSpacing(8);
    	stepFlow.getChildren().add(grids);
    	addNewStep();
    	stepScroll.setContent(stepFlow);
    	/*
    	svb.getChildren().add(addNewStep());
    	stepScroll.setContent(svb);  	
    	*/
    }
    @FXML
	public void save() throws NumberFormatException, ClassNotFoundException, SQLException {
		for(int i = 0; i<row1*4; i = i+4) {
			TextField t1 = (TextField)gridi.getChildren().get(i);
			TextField t2 = (TextField)gridi.getChildren().get(i+1);
			TextField t3 = (TextField)gridi.getChildren().get(i+2);
			ingList.add(new Ingredient(t1.getText(), Double.parseDouble(t2.getText()), t3.getText()));
			System.out.println(t1.getText());
		}
		for(int i = 0; i<row2*2; i = i+2) {
			TextField t = (TextField)grids.getChildren().get(i);
			stepList.add(new PreparationStep(t.getText()));
		}
		
		recipe.setRecipeName(recipeName.getText());
		recipe.setAreaName(areaName.getText());
		recipe.setCookingTime(Long.parseLong(cookingTime.getText()));
		recipe.setPreparationTime(Long.parseLong(preparationTime.getText()));
		recipe.setServeAmount(Integer.parseInt(serveAmount.getText()));
		recipe.setPreparationStep(stepList);
		recipe.setRequiredIngredients(ingList);
		CookBook.addRecipe(recipe);
	}
}
    
