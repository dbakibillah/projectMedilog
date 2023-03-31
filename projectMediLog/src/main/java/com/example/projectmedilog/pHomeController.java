package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;


public class pHomeController {
    @FXML
    private Button BTN_prescription;

    @FXML
    Pane homePane;
    @FXML
    Pane HomePane2;
    @FXML
    private AnchorPane pHomeAnchor;

    public void onClickAppointment_btn(ActionEvent event) throws IOException {
        Pane AppointtmentPane = FXMLLoader.load(getClass().getResource("pAppointment.fxml"));
        HomePane2.getChildren().setAll(AppointtmentPane);
    }

    public void onDashboard_btnClick(ActionEvent event) throws IOException {
        Pane DashboardPane = FXMLLoader.load(getClass().getResource("pDashboard.fxml"));
        HomePane2.getChildren().setAll(DashboardPane);
    }

    @FXML
    void onClickPrescription(ActionEvent event) {

    }

    @FXML
    void onCLickMedicalRecords(ActionEvent event) {

    }

    @FXML
    void onClickSettings(ActionEvent event) throws IOException {
        Pane SettingPane = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        HomePane2.getChildren().setAll(SettingPane);
    }

    @FXML
    private Pane Pane_BP;

    @FXML
    void onMouseEntered_BP(MouseEvent event) {
        Pane_BP.setCursor(Cursor.HAND);
        Pane_BP.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseExited_BP(MouseEvent event) {
        Pane_BP.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onClickedBP(MouseEvent event) {
        System.out.println("BP Clicked");
    }

    @FXML
    private Pane Pane_HB;

    @FXML
    void onMouseEntered_HB(MouseEvent event) {
        Pane_HB.setCursor(Cursor.HAND);
        Pane_HB.setBackground(Background.fill(Color.web("95BDFF")));

    }


    @FXML
    void onMouseExited_HB(MouseEvent event) {
        Pane_HB.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseClicked_HB(MouseEvent event) {

    }
    @FXML
    private Pane Pnae_GL;
    @FXML
    void onMouseEntered_GL(MouseEvent event) {
        Pnae_GL.setCursor(Cursor.HAND);
        Pnae_GL.setBackground(Background.fill(Color.web("95BDFF")));
    }
    @FXML
    void onMouseExited_GL(MouseEvent event) {
        Pnae_GL.setBackground(Background.fill(Color.web("#FFFFFF")));
    }
    @FXML
    void onMouseClicked_GL(MouseEvent event) {

    }

    @FXML
    void onClickLogout(ActionEvent event) throws IOException {
        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        pHomeAnchor.getChildren().setAll(loginPage);
    }
}


