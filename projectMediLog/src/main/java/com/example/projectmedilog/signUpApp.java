package com.example.projectmedilog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class signUpApp extends Application{
    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< HEAD
        FXMLLoader fxmlLoader = new FXMLLoader(signUpApp.class.getResource("SignUp.fxml"));
=======

        FXMLLoader fxmlLoader = new FXMLLoader(signUpApp.class.getResource("SignUp.fxml"));

>>>>>>> 3b170d7bd4a026736f62271dfda84be5a1d628da
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Signup");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
