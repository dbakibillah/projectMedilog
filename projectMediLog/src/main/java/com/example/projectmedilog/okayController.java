package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class okayController {

    @FXML
    public Label LB_okay;
    @FXML
    private AnchorPane okayPage;

    void okaySignup(String text) throws InterruptedException, IOException {

        LB_okay.setText(text);
        System.out.println("from okayController");

        Thread thread = Thread.currentThread();
        System.out.println("current thread: "+thread);
        Thread.sleep(5000);

        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        okayPage.getChildren().setAll(loginPage);


    }

}
