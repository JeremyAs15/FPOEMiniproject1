package com.example.miniproject1.controllers;

import com.example.miniproject1.Models.RandomWords;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * The lass manages the logic and user interface for the fast typing game.
 * It handles the game timer, word validation, level and attempt management, and updates the UI components
 * such as labels and images accordingly.
 */
public class TypingGameController {
    /** Label displaying the remaining number of attempts. */
    @FXML
    private Label attemptsLabel;
    /** Button used to check the word entered by the user. */
    @FXML
    private Button checkButton;
    /** ImageView for the background image. */
    @FXML
    private ImageView imageBackground;
    /** Label displaying the current level. */
    @FXML
    private Label levelLabel;
    /** Label for showing game messages (e.g., correct or incorrect input). */
    @FXML
    private Label messageLabel;
    /** ImageView displaying a sun image as part of the UI. */
    @FXML
    private ImageView sunImage;
    /** Label displaying the remaining time. */
    @FXML
    private Label timeLabel;
    /** TextField for the user to enter words. */
    @FXML
    private TextField wordField;
    /** Label showing the current word to type. */
    @FXML
    private Label wordLabel;
    /** ImageView used to display the image representing remaining attempts. */
    @FXML
    private ImageView attemptsImage;
    /** Current time left for the round. */
    private int time = 20;
    /** Base time used to reset the timer, which decreases as the game progresses. */
    private int resetTime = 20;
    /** Number of attempts (lives) the user has remaining. */
    private int attempts = 4;
    /** Current game level. */
    private int level = 1;
    /** Count of correctly typed words. */
    private int correctWords = 0;
    /** Timeline used to manage the countdown timer. */
    private Timeline timeline;
    /**
     * Initializes the controller.
     * This method sets the initial UI styles, generates the first word, updates the labels,
     * starts the game time, and sets the action handler for the word input field.
     */
    @FXML
    public void initialize() {
        wordLabel.setStyle("-fx-alignment: center;");
        messageLabel.setStyle("-fx-alignment: center;");
        setNewWord();
        updateLabels();
        startTimer();
        wordField.setOnAction(this::checkWord);
    }
    /**
     * Starts the game timer.
     * A is created that decrements the time every second. When time reaches zero,
     * the game is ended with a "time's up" message.
     */
    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            time--;
            timeLabel.setText("Time: " + time);
            if (time <= 0) {
                endGame("🛑 Game Over! Time's up.");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Checks the word entered by the user.
     * If the user's input matches the current word (ignoring case), the game increments the number of
     * correct words and the level. Every 5 correct words, the base time is reduced by 2 seconds until
     * a minimum threshold is reached. If the input is incorrect, an attempt is deducted immediately.
     * The method updates the UI accordingly. If no attempts remain, the game ends.*
     * @param event when the user submits the word.
     */
    @FXML
    void checkWord(ActionEvent event) {
        if (attempts == 0) {
            return;
        }
        String userInput = wordField.getText().trim();
        String currentWord = wordLabel.getText();

        if (userInput.equalsIgnoreCase(currentWord)) {
            correctWords++;
            level++;
            messageLabel.setText("✅ Correct!");
            if (correctWords % 5 == 0) {
                if (resetTime > 2) {
                    resetTime -= 2;
                }
            }
            time = resetTime;
            wordField.clear();
            setNewWord();
            updateLabels();
        } else {
            messageLabel.setText("❌ Incorrect!");
            System.out.println("The user type wrong the next word: " + userInput);
            attempts--;
            updateAttemptsImage();
            updateLabels();
            if (attempts == 0) {
                endGame("🛑 Game Over! No more attempts.");
                return;
            }
            wordField.clear();
            setNewWord();
        }
    }

    /**
     * Generates a new random word and updates the word label.
     */
    private void setNewWord() {
        wordLabel.setText(RandomWords.newWord());
    }

    /**
     * Updates the level, attempts, and time labels on the UI.
     */
    private void updateLabels() {
        levelLabel.setText("Level: " + level);
        attemptsLabel.setText("Attempts: " + attempts);
        timeLabel.setText("Time: " + time);
    }
    /**
     * Updates the image representing the remaining attempts.
     * The image is selected from a predefined array of images based on the number of attempts left.
     */
    private void updateAttemptsImage() {
        String[] sunEclipse = {
                "/com/example/miniproject1/images/sun0.png",
                "/com/example/miniproject1/images/sun25.png",
                "/com/example/miniproject1/images/sun50.png",
                "/com/example/miniproject1/images/sun75.png",
                "/com/example/miniproject1/images/sun100.png"
        };
        int index = Math.max(0, 4 - attempts);
        attemptsImage.setImage(new Image(getClass().getResourceAsStream(sunEclipse[index])));
    }
    /**
     * Ends the game.
     * This method stops the timer, displays a game over message, updates the UI, and disables further input.
     * @param message the message to be displayed when the game ends.
     */
    private void endGame(String message) {
        timeline.stop();
        messageLabel.setText(message);
        wordLabel.setText("Game Over");
        attemptsLabel.setText("Attempts: 0");
        levelLabel.setText("Level: " + level);
        wordField.setDisable(true);
        checkButton.setDisable(true);
    }
}
