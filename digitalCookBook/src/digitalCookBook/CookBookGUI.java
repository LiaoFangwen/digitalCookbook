package digitalCookBook;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CookBookGUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Text mainTitle = new Text("My Cook Book"); 
		mainTitle.setFont(Font.font(50));
		mainTitle.setFill(Color.ORANGE);
		mainTitle.setX(200.0);
		mainTitle.setY(200.0);
		Text subTitle = new Text("learn how to cook");
		subTitle.setFont(Font.font(20));
		subTitle.setFill(Color.RED);
		subTitle.setX(300);
		subTitle.setY(230);
		TextField searchField = new TextField("input word");
		searchField.setScaleX(1);
		searchField.setLayoutY(10);
		Button addNew = new Button("add a new recipe");
		addNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				search(primaryStage);
				
		}
		});
 
		Group root = new Group();
		ObservableList list = root.getChildren(); 
		list.add(mainTitle);
		list.add(subTitle);
		list.add(addNew);
		list.add(searchField);
		Scene scene = new Scene(root,800, 600);
		scene.setFill(Color.FLORALWHITE);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void search(Stage primaryStage) {
		
		Group searchRoot = new Group();
		Button backbtn = new Button("Back");
		backbtn.setLayoutX(10);
		backbtn.setLayoutY(10);
		TextField textField = new TextField();
		textField.setLayoutX(10);
		textField.setLayoutY(50);
		textField.setPrefWidth(600);
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {    
            @Override  
            public void handle(KeyEvent event) {  
                if(event.getCode().equals(KeyCode.ENTER ))
            	System.out.println(textField.getText());  
            }  
        });  
        
        VBox resultBox = new VBox();
        resultBox.setLayoutX(100);
        resultBox.setLayoutY(200);
        resultBox.setMinHeight(200);
        for(int i = 0; i<100; i++)
        	resultBox.getChildren().add(new TextField("fuck you"));
        
        ScrollBar resultBar = new ScrollBar();
        resultBar.setLayoutX(500);
        resultBar.setLayoutY(100);
        resultBar.setMin(0);
        resultBar.setOrientation(Orientation.VERTICAL);
        resultBar.setPrefHeight(180);
        resultBar.setMax(360);
        resultBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    resultBox.setLayoutY(-new_val.doubleValue());
            }
        });
        
        searchRoot.getChildren().add(resultBar);
        searchRoot.getChildren().add(resultBox);
        searchRoot.getChildren().add(backbtn);
		searchRoot.getChildren().add(textField);
		Scene scene = new Scene(searchRoot, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}


