package com.example.projectmedilog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class userLogin extends Application {
    static Stage mainstage;

    public void start(Stage stage) throws IOException {
        mainstage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("MediLog");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
