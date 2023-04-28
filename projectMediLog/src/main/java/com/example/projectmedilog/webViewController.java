package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.web.WebHistory;

public class webViewController implements Initializable{

    @FXML
    private AnchorPane AnchorPane_webView;

    @FXML
    private WebView web_view;
    private WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = web_view.getEngine();
        webEngine.load("https://www.google.com/");
    }
}
