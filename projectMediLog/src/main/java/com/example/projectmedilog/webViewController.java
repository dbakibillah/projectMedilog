package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class webViewController {

    @FXML
    private AnchorPane AnchorPane_webView;

    @FXML
    private WebView web_view;
    private WebEngine webEngine;

    public void initialize(){
        webEngine = web_view.getEngine();
        webEngine.load("https://www.facebook.com/");

    }

}
