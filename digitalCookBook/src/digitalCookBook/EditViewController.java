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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class EditViewController {
	private int countStep = 0;
	private VBox svb = new VBox();
	private GridPane stepPane = new GridPane();
	private int row = 0;
	private int column = 0;
	private Recipe recipe;
	private List<TextField> stepList = new ArrayList<TextField>();
	
	@FXML
	private AnchorPane mainPane;
	@FXML
	private TextField recipeName;
	@FXML
	private Button saveRecipe;
	@FXML
	private Button cancel;
	@FXML
	private Button addOneMoreStep;
	@FXML
	private TextField cookingTime;
	@FXML
	private Button deleteRecipe;
	@FXML
	private ScrollPane stepScroll;
	@FXML
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
	
    public EditViewController() {
    	
    }
    
    public void addNewStep() {
    	Label stepNo = new Label(Integer.toString(row+1));
    	TextField emptyStep = new TextField();
    	Button delete = new Button("delete");
    	
    	delete.setOnMouseClicked((new EventHandler<MouseEvent>() {    
            @Override  
            public void handle(MouseEvent event) { 
            	stepPane.getChildren().remove(delete);
            	stepPane.getChildren().remove(delete);
            	
            }  
        }));  
        
    	stepPane.add(stepNo, column, row);
    	stepPane.add(emptyStep, column+1, row);
    	stepPane.add(delete, column+2, row);
    	row++;
    	/*
    	GridPane grid = new GridPane();
    	Label stepNo = new Label(Integer.toString(++countStep));
    	TextField emptyStep = new TextField();
    	stepList.add(emptyStep);
    	Button delete = new Button("delete");
    	delete.setOnMouseClicked((new EventHandler<MouseEvent>() {    
            @Override  
            public void handle(MouseEvent event) { 
            	svb.getChildren().remove(delete.getParent());
            }  
        }));  
    	grid.add(stepNo, 0, 0);
    	grid.add(emptyStep, 1, 0);
    	grid.add(delete, 2, 0);
		return grid;
		*/
    }
    public void showFirst() {
    	addNewStep();
    	stepScroll.setContent(stepPane);
    	/*
    	svb.getChildren().add(addNewStep());
    	stepScroll.setContent(svb);  	
    	*/
    }
    @FXML 
    public void addOneMoreStep() {
    	addNewStep();
    	//svb.getChildren().add(addNewStep());
    }
    @FXML
    public void getStep() throws ClassNotFoundException, SQLException {
    	Iterator<TextField> stepIterator = stepList.iterator();
    	while(stepIterator.hasNext()) {
    		TextField field = stepIterator.next();
    		String content = field.getText();
    		recipe.addPreparationStep(new PreparationStep(content));
    	}
    	System.out.println(recipe.toString());
    	
    }
}
    
