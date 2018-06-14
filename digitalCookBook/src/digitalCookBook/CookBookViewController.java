package digitalCookBook;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CookBookViewController {
	@FXML
	private Label addLabel;
	@FXML
	private Button searchBtn;
	@FXML
	private TextField searchField;
	@FXML
	private Label mainTitle;
	@FXML
	private Label subTitle;
	@FXML
	private AnchorPane mainPane;
	@FXML
	private Label searchLabel;
	@FXML
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    public void showRecipe() {
    	System.out.println("fuck you");
    
    }
    @FXML
    public void showSearchResult() throws IOException {
    	mainApp.showSearchView();
    }
    
}

