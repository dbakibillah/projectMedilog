package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DialogController {

    @FXML
    private Label successLabel;

    void successDialog(Stage dialogStage, String message) throws IOException {
        successLabel.setText(message);
        FXMLLoader fxmlLoader = new FXMLLoader(DialogController.class.getResource("successDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialogStage.setTitle("Successfull");
        dialogStage.setScene(scene);
        dialogStage.show();
    }
}
