package com.example.miniproject1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The class Main is the entry point for the Fast Typing Game application.
 * @author Jeremy
 * @version 1.0
 */
public class Main extends Application {
    /**
     * Starts the JavaFX application and loads the FXML layout from TypingGameView.fxml, and
     * configures the principal stage with a title and the loaded scene, and then displays the stage.
     * @param stage the principal stage for the program, into which the program scene is set.
     * @throws IOException if the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("TypingGameView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Fast Typing Game!");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments (not used).
     */
    public static void main(String[] args) {
        launch();
    }
}
