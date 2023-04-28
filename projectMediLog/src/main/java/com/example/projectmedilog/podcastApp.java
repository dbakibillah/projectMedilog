package com.example.projectmedilog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class podcastApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("podcast.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Podcast");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            podcastController pc = fxmlLoader.getController();
            pc.stopMedia();
            stage.close();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
