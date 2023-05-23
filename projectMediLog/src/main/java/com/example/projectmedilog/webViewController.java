package com.example.projectmedilog;

import javafx.fxml.FXML;
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

}
