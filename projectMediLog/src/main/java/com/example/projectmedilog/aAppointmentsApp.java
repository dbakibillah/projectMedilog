package com.example.projectmedilog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class aAppointmentsApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //load the fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(aAppointmentsApp.class.getResource("aAppointments.fxml"));
        //create a scene with the fxml file
        Scene scene = new Scene(fxmlLoader.load());
        //set the title of the stage
        stage.setTitle("Appointments");
//set the scene to the stage
        stage.setScene(scene);
        stage.show();
//        FXMLLoader fxmlLoader = new FXMLLoader(signUpApp.class.getResource("aAppointments.fxml"));
//
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Appointments");
//        stage.setScene(scene);
//        stage.show();
    }
}