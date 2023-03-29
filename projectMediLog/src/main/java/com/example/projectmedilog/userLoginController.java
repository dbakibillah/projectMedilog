package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class userLoginController {

    @FXML
    private Hyperlink hyperSignup;
    @FXML
    BorderPane page;

    @FXML
    void onClickSignUp(ActionEvent event) throws IOException {
        BorderPane signUpPage = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        page.getChildren().setAll(signUpPage);
    }

}