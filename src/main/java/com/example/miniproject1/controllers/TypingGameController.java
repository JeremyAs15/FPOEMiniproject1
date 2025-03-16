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

public class TypingGameController {

    @FXML
    private Label attemptsLabel;
    @FXML
    private Button checkButton;
    @FXML
    private ImageView imageBackground;
    @FXML
    private Label levelLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private ImageView sunImage;
    @FXML
    private Label timeLabel;
    @FXML
    private TextField wordField;
    @FXML
    private Label wordLabel;
    @FXML
    private ImageView attemptsImage;

    private int time = 20;
    private int resetTime = 20;
    private int attempts = 4;
    private int level = 1;
    private int correctWords = 0;
    private Timeline timeline;

    @FXML
    public void initialize() {
        wordLabel.setStyle("-fx-alignment: center;");
        messageLabel.setStyle("-fx-alignment: center;");
        setNewWord();
        updateLabels();
        startTimer();
        wordField.setOnAction(this::checkWord);
    }

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
                } else {
                    winGame("🎉 Congratulations! You won the game!");
                    return;
                }
            }
            time = resetTime;
            wordField.clear();
            setNewWord();
            updateLabels();
        } else {
            messageLabel.setText("❌ Incorrect!");
            System.out.println("El usuario escribió mal la palabra: " + userInput);
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

    private void setNewWord() {
        wordLabel.setText(RandomWords.newWord());
    }

    private void updateLabels() {
        levelLabel.setText("Level: " + level);
        attemptsLabel.setText("Attempts: " + attempts);
        timeLabel.setText("Time: " + time);
    }

    private void updateAttemptsImage() {
        String[] sunEclipse = {
                "/com/example/miniproject1/images/sun0.png",   // 0 intentos
                "/com/example/miniproject1/images/sun25.png",  // 1 intento
                "/com/example/miniproject1/images/sun50.png",  // 2 intentos
                "/com/example/miniproject1/images/sun75.png",  // 3 intentos
                "/com/example/miniproject1/images/sun100.png"  // 4 intentos
        };
        int index = Math.max(0, 4 - attempts);
        attemptsImage.setImage(new Image(getClass().getResourceAsStream(sunEclipse[index])));
    }

    private void endGame(String message) {
        timeline.stop();
        messageLabel.setText(message);
        wordLabel.setText("Game Over");
        attemptsLabel.setText("Attempts: 0");
        levelLabel.setText("Level: " + level);
        wordField.setDisable(true);
        checkButton.setDisable(true);
    }

    private void winGame(String message) {
        messageLabel.setText(message);
        wordField.setDisable(true);
        checkButton.setDisable(true);
        timeline.stop();
        wordLabel.setText("You Won!");
        attemptsLabel.setText("Attempts: " + attempts);
        levelLabel.setText("Level: " + level);
        timeLabel.setText("Time: " + time);
    }
}
