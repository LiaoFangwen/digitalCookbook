package digitalCookBook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchViewController {
	private String searchString;
	private CookBook cb;
	public CookBook getCb() {
		return cb;
	}
	public void setCb(CookBook cb) {
		this.cb = cb;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public List<Recipe> getResultList() {
		List<Recipe> resultList = cb.searchRecipeByKeyword(searchString);
		return resultList;
	}
	
	public SearchViewController() {
		
		
	}
	@FXML
	private ScrollPane resultPane;
	@FXML
	private Label backLabel;
	@FXML
	private TextField searchField1;
	@FXML
	private AnchorPane mainPane;
	@FXML
	private Label searchArea;
	@FXML
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
	@FXML
    public void backToMain() {
    	mainApp.showCookBookView();
    }
	@FXML
	public void showEdit() throws IOException {
		mainApp.showEditView();
	}
	@FXML
	public void showTheRecipe(Recipe recipe) throws IOException {
		mainApp.showRecipeView(recipe);
	}
	@FXML
	public void newSearch() throws IOException {
		setSearchString(searchField1.getText());
		showRecipe();
	}
	@FXML
    public void showArea() throws IOException {
    	mainApp.showAreaView();
    }
	@FXML
	public void showRecipe() {
		VBox vb = new VBox();
		Iterator<Recipe> iterator = getResultList().iterator();
		while(iterator.hasNext()) {
			
			GridPane grid = new GridPane();
			/*
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			*/
			Recipe recipe = iterator.next();
			Label name = new Label(recipe.getRecipeName());
			Label d = new Label("Description");
			
			grid.add(name, 0, 0);
			grid.add(d, 0, 1);
			
			grid.setOnMouseClicked((new EventHandler<MouseEvent>() {    
	            @Override  
	            public void handle(MouseEvent event) {  
	            	try {
						showTheRecipe(recipe);
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }  
	        }));  
			vb.getChildren().add(grid);
			
			
		}
		resultPane.setContent(vb);
		
	}
	public void setSearchField1(String s) {
		searchField1.setText(s);
	}
}
