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
                messageLabel.setText("🛑 Game Over! Time's up.");
                disableGame();
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

            if (correctWords % 5 == 0 && time > 2) {
                time -= 2;
            }

            time = 20;
        } else {
            messageLabel.setText("❌ Incorrect!");
            attempts--;
            updateAttemptsImage();

            if (attempts == 0) {
                messageLabel.setText("🛑 Game Over! No more attempts.");
                disableGame();
                return;
            }
        }

        wordField.clear();
        setNewWord();
        updateLabels();
    }

    private void setNewWord() {
        wordLabel.setText(RandomWords.newWord());
        wordLabel.setStyle("-fx-alignment: center;");
    }

    private void updateLabels() {
        levelLabel.setText("Level: " + level);
        attemptsLabel.setText("Attempts: " + attempts);
        timeLabel.setText("Time: " + time);
    }

    private void updateAttemptsImage() {
        switch (attempts){
            case 0:
                attemptsImage.setImage(new Image(getClass().getResourceAsStream("/com/example/miniproject1/images/sun0.png")));
                break;
            case 1:
                attemptsImage.setImage(new Image(getClass().getResourceAsStream("/com/example/miniproject1/images/sun25.png")));
                break;
            case 2:
                attemptsImage.setImage(new Image(getClass().getResourceAsStream("/com/example/miniproject1/images/sun50.png")));
                break;
            case 3:
                attemptsImage.setImage(new Image(getClass().getResourceAsStream("/com/example/miniproject1/images/sun100.png")));
                break;
            case 4:
                attemptsImage.setImage(new Image(getClass().getResourceAsStream("/com/example/miniproject1/images/sun0.png")));
                break;
        }
        String[] imagePaths = {"/images/sun0.png", "/images/sun25.png", "/images/sun50.png", "/images/sun75.png", "/images/sun100.png"};
        int index = Math.max(0, 4 - attempts);
        attemptsImage.setImage(new Image(getClass().getResourceAsStream(imagePaths[index])));
    }

    private void disableGame() {
        wordField.setDisable(true);
        checkButton.setDisable(true);
        timeline.stop();
        wordLabel.setText("Game Over");
        attemptsLabel.setText("Attempts: 0");
        levelLabel.setText("Level: " + level);
    }
}
