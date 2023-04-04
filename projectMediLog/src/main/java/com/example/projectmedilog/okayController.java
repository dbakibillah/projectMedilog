package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class okayController {

    @FXML
    private Button BTN_login;

    @FXML
    private Label LB_okay;

    @FXML
    private AnchorPane okayPage;

    @FXML
    void gotoLogin(ActionEvent event) throws IOException {
        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        okayPage.getChildren().setAll(loginPage);
    }



    void okaySignup(String text) throws InterruptedException, IOException {

        LB_okay.setText(text);

    }

}
