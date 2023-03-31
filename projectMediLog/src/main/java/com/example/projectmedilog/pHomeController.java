package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class pHomeController {
    @FXML
    private Button BTN_prescription;

    @FXML
    Pane homePane;
    @FXML
    Pane HomePane2;

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
    void onClickSettings(ActionEvent event) {

    }
    @FXML
    void onClickLogout(ActionEvent event) {

    }
}
