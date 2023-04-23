package com.example.projectmedilog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class webViewApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("webView.fxml"));
        AnchorPane webPage = fxmlLoader.load();
        stage.setTitle("webView");
        stage.setScene(new Scene(webPage));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
