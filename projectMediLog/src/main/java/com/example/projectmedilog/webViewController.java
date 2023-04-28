package com.example.projectmedilog;

import javafx.fxml.FXML;
<<<<<<< HEAD
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class webViewController {

    @FXML
    public WebView web_view;
    public WebEngine webEngine;
    private String url;

    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public void initialize(String url) {
        webEngine = web_view.getEngine();

        web_view.getEngine().load(url);
        System.out.println(url);

    }

//    public void loadPage() {
//        webEngine.load(getUrl());
//
//    }


=======
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


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
>>>>>>> 2ccb2987851008e8d60db12cc4bfeb0fea5dff6f
}
