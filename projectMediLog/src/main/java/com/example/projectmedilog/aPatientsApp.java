package com.example.projectmedilog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class aPatientsApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(signUpApp.class.getResource("aPatients.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Patients");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}