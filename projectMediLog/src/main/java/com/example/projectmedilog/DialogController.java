package com.example.projectmedilog;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class DialogController {

    @FXML
    private Label successLabel;
    @FXML
    private Label errorLabel;

    void successDialog(Stage dialogStage, String message, int time) throws IOException {
        successLabel.setText(message);
        FXMLLoader fxmlLoader = new FXMLLoader(DialogController.class.getResource("successDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialogStage.setTitle("Successfull");
        dialogStage.setScene(scene);
        dialogStage.show();
        // create a new Timeline object
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), event -> {
            // close the dialog box
            dialogStage.close();
        }));
        // start the timeline
        timeline.play();

    }

    void errorDialog(Stage dialogStage, String message, int time) throws IOException {
        errorLabel.setText(message);
        FXMLLoader fxmlLoader = new FXMLLoader(DialogController.class.getResource("errorDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialogStage.setTitle("Error!");
        dialogStage.setScene(scene);
        dialogStage.show();

        // create a new Timeline object
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), event -> {
            // close the dialog box
            dialogStage.close();
        }));
        // start the timeline
        timeline.play();
    }
}
