package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class webViewController implements Initializable {
    @FXML
    private AnchorPane AnchorPane_webView;
    @FXML
    private WebView web_webView;
    private WebEngine webEngine;

    private String wpage;

//    public webViewController(String page) {
//        this.wpage = page;
//        System.out.println(page);
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = web_webView.getEngine();
//        loadPage(wpage);
    }

    void loadPage(String page){
//        webEngine.load(page);
    }
}
