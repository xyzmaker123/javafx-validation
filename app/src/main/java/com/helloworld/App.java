/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.helloworld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX Hello World");
        TextField field = new TextField();
        field.setText("How are you?");

        StackPane root = new StackPane();
        root.getChildren().add(field);
        stage.setScene(new Scene(root, 300, 250));
        stage.show();
    }
}
