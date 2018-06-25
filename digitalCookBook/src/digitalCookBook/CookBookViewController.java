package digitalCookBook;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class CookBookViewController {
	public static String searchString;
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
    public void showSearchResult() throws IOException, ClassNotFoundException, SQLException {
    	searchString = searchField.getText();
    	
    	mainApp.showSearchView();
    }
    @FXML
    public void showArea() throws IOException {
    	mainApp.showAreaView();
    }
    @FXML
	public void showEdit() throws IOException {
		mainApp.showEditView();
	}
    @FXML
    public void setEnterAction() {
    	searchField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    try {
						showSearchResult();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        });
    }
    
}

