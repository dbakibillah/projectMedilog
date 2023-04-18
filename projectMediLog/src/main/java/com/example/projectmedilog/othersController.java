package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class othersController {
    @FXML
    private AnchorPane AnchorPane_Others;

    @FXML
    private Pane Pane_ADHD;

    @FXML
    private Pane Pane_Ambulance;

    @FXML
    private Pane Pane_BMI;

    @FXML
    private Pane Pane_Blogs;

    @FXML
    private Pane Pane_Blood;

    @FXML
    private Pane Pane_Diet;

    @FXML
    private Pane Pane_IQ;

    @FXML
    private Pane Pane_Podcasts;

    @FXML
    private Pane Pane_SOS;

    @FXML
    void OnCliekedPane_Diet(MouseEvent event) {

    }

    @FXML
    void onClickedPane_ADHD(MouseEvent event) throws IOException {
        AnchorPane webPage = FXMLLoader.load(getClass().getResource("webView.fxml"));
        AnchorPane_Others.getChildren().setAll(webPage);
//        webViewController wpage = new webViewController("https://www.clinical-partners.co.uk/for-adults/adult-adhd-add/test-for-adhd");
    }

    @FXML
    void onClickedPane_Ambulance(MouseEvent event) {

    }

    @FXML
    void onClickedPane_BMI(MouseEvent event) {

    }

    @FXML
    void onClickedPane_Blogs(MouseEvent event) {

    }

    @FXML
    void onClickedPane_Blood(MouseEvent event) {

    }

    @FXML
    void onClickedPane_IQ(MouseEvent event) {

    }

    @FXML
    void onClickedPane_Podcasts(MouseEvent event) {

    }

    @FXML
    void onClickedPane_SOS(MouseEvent event) {

    }

    @FXML
    void onMouseEnteredPane_ADHD(MouseEvent event) {
        Pane_ADHD.setCursor(Cursor.HAND);
        Pane_ADHD.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEnteredPane_Ambulance(MouseEvent event) {
        Pane_Ambulance.setCursor(Cursor.HAND);
        Pane_Ambulance.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEnteredPane_BMI(MouseEvent event) {
        Pane_BMI.setCursor(Cursor.HAND);
        Pane_BMI.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEnteredPane_Blogs(MouseEvent event) {
        Pane_Blogs.setCursor(Cursor.HAND);
        Pane_Blogs.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEnteredPane_Blood(MouseEvent event) {
        Pane_Blood.setCursor(Cursor.HAND);
        Pane_Blood.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEnteredPane_Diet(MouseEvent event) {
        Pane_Diet.setCursor(Cursor.HAND);
        Pane_Diet.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEnteredPane_IQ(MouseEvent event) {
        Pane_IQ.setCursor(Cursor.HAND);
        Pane_IQ.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEnteredPane_Podcasts(MouseEvent event) {
        Pane_Podcasts.setCursor(Cursor.HAND);
        Pane_Podcasts.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEnteredPane_SOS(MouseEvent event) {
        Pane_SOS.setCursor(Cursor.HAND);
        Pane_SOS.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseExitedPane_ADHD(MouseEvent event) {
        Pane_ADHD.setCursor(Cursor.DEFAULT);
        Pane_ADHD.setBackground(Background.fill(Color.web("FFFFFF")));
    }

    @FXML
    void onMouseExitedPane_Ambulance(MouseEvent event) {
        Pane_Ambulance.setCursor(Cursor.DEFAULT);
        Pane_Ambulance.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExitedPane_BMI(MouseEvent event) {
        Pane_BMI.setCursor(Cursor.DEFAULT);
        Pane_BMI.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExitedPane_Blogs(MouseEvent event) {
        Pane_Blogs.setCursor(Cursor.DEFAULT);
        Pane_Blogs.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExitedPane_Blood(MouseEvent event) {
        Pane_Blood.setCursor(Cursor.DEFAULT);
        Pane_Blood.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExitedPane_Diet(MouseEvent event) {
        Pane_Diet.setCursor(Cursor.DEFAULT);
        Pane_Diet.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExitedPane_IQ(MouseEvent event) {
        Pane_IQ.setCursor(Cursor.DEFAULT);
        Pane_IQ.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExitedPane_Podcasts(MouseEvent event) {
        Pane_Podcasts.setCursor(Cursor.DEFAULT);
        Pane_Podcasts.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExitedPane_SOS(MouseEvent event) {
        Pane_SOS.setCursor(Cursor.DEFAULT);
        Pane_SOS.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

}
