package com.example.miniproject1.Models;

import java.util.Random;

/**
 * The {@code RandomWords} class is a method to obtain a random word
 * from a list of words.
 */
public class RandomWords {
    /**
     * An array with 60 words related to Java and JavaFX.
     */
    private static String[] words = {
            "Java", "JavaFX", "SceneBuilder", "FXML", "Stage", "Scene",
            "AnchorPane", "BorderPane", "VBox", "HBox", "GridPane", "StackPane",
            "Button", "Label", "TextField", "TextArea", "ComboBox", "ChoiceBox",
            "CheckBox", "RadioButton", "ToggleButton", "Slider", "ProgressBar",
            "TableView", "TableColumn", "ListView", "TreeView", "MenuBar", "MenuItem",
            "ContextMenu", "Dialog", "Alert", "EventHandler", "MouseEvent",
            "KeyEvent", "ActionEvent", "ObservableList", "Property", "Binding",
            "FXMLLoader", "Controller", "Parent", "CSS", "Stylesheets",
            "Animation", "Transition", "FadeTransition", "TranslateTransition",
            "RotateTransition", "ScaleTransition", "Timeline", "MediaPlayer",
            "Canvas", "GraphicsContext", "ImageView", "Font", "Border", "Insets",
            "Padding", "LayoutX", "LayoutY"
    };
    /**
     * @return a randomly selected word from the array
     */
    public static String newWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }
}
