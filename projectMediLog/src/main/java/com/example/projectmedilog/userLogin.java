package com.example.projectmedilog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class userLogin extends Application {
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(userLogin.class.getResource("userLogin.fxml"));
        //Scene scene = new Scene(fxmlLoader.load());
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        //stage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
