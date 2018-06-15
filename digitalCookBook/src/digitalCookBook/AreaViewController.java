package digitalCookBook;

import java.io.IOException;
import java.util.Iterator;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AreaViewController {
	@FXML
	private Label backLabel;
	@FXML
	private MainApp mainApp;
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
	@FXML
    public void backToMain() {
    	mainApp.showCookBookView();
    }
	/*
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
	/*
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
	*/
}
