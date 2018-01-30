/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author n0149245
 */
public class JavaFXApplication1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
                      
//        Button b1 = new Button("First Button");        
//        Button b2 = new Button("Second Button");
//        Button b3 = new Button("Third Button");
//        Button b4 = new Button("Fourth");
//        
//        HBox hbox = new HBox();
//        hbox.setSpacing(10);
//        b1.setPrefSize(80, 20);
//        b2.setPrefSize(80, 20);
//        
//        GridPane grid = new GridPane();        
//        GridPane.setConstraints(b1, 1, 1);
//        GridPane.setConstraints(b2, 2, 1);
//        GridPane.setConstraints(b3, 1, 2);
//        GridPane.setConstraints(b4, 2, 2);
//        
//        BorderPane borderPane = new BorderPane();
//        borderPane.setTop(new Button("Top"));
//        borderPane.setBottom(new Button("Bottom"));
//        borderPane.setLeft(new Button("Left"));
//        borderPane.setRight(new Button("Right"));
//        borderPane.setCenter(new Button("Center"));
//        GridPane.setConstraints(borderPane, 1, 3, 2, 1);
//        
////        grid.getChildren().addAll(b1, b2, b3, b4, borderPane);       
//        TextField name = new TextField();
//        name.setPromptText("Enter name: ");
//        
//        ComboBox combo = new ComboBox();
//        combo.getItems().addAll("Highest", "High", "Normal", "Low");
//        combo.setValue("Normal");
//        
//        grid.getChildren().addAll(b1, b2, b3, b4, combo); // name / borderPane
//        Scene scene = new Scene(grid, 300, 400);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Do-It!!");
//        primaryStage.setAlwaysOnTop(true);
//        primaryStage.setResizable(false);
//        primaryStage.show();

        GridPane grid = new GridPane();
        grid.setMinWidth(600);
        grid.setMinHeight(400);
        grid.setVgap(5);
        grid.setHgap(20);
        grid.setGridLinesVisible(false);
        
        Label tableArea = new Label("This is where tasks table will come.");
        tableArea.setMinWidth(600);
        
        GridPane.setConstraints(tableArea, 1, 1, 3, 1);
        TextField taskName = new TextField();
        taskName.setPromptText("Enter task name");
        GridPane.setConstraints(taskName, 2, 2);
        
        TableView table = new TableView();
        table.setMinHeight(300);
        table.setMinWidth(550);
        
        TextField another = new TextField();
        GridPane.setConstraints(another, 2, 3);
        another.setText("Default Text");
        
        ComboBox priority = new ComboBox();
        priority.getItems().addAll("High", "Low", "Medium");
        priority.setPromptText("Enter Priority");
        GridPane.setConstraints(priority, 1, 2);
        
        Button addButton = new Button("Add");
        addButton.setMinWidth(100);
        GridPane.setConstraints(addButton, 3, 2);
        
        Button cancelButton = new Button("Cancel");
        cancelButton.setMinWidth(100);
        GridPane.setConstraints(cancelButton, 3, 3);
        
        HBox progressArea = new HBox();
        progressArea.getChildren().addAll(new Label("Progress"), new Spinner<>(0, 100, 0), new CheckBox("Completed"));
        progressArea.setAlignment(Pos.CENTER_RIGHT);
        progressArea.setSpacing(10);
        GridPane.setConstraints(progressArea, 1, 3, 2, 1);
        grid.getChildren().addAll(table, taskName, priority, addButton, cancelButton, progressArea);
        Scene scene = new Scene(grid,600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Do It!");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
