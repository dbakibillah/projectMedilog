package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class aHomeController {

    @FXML
    private Button Appointment_btn;

    @FXML
    private Button BTN_Doctors;

    @FXML
    private Button BTN_Logout;

    @FXML
    private Button BTN_Patients;

    @FXML
    private Button Dashboard_btn;

    @FXML
    AnchorPane anchorpaneHome;
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Pane homePane;

    @FXML
    void onClickAppointment_btn(ActionEvent event) throws IOException {
        AnchorPane nextPage = FXMLLoader.load(getClass().getResource("aAppointments.fxml"));
        anchorpaneHome.getChildren().setAll(nextPage);
    }

    @FXML
    void onClickDoctors_btn(ActionEvent event) throws IOException {
        AnchorPane nextPage = FXMLLoader.load(getClass().getResource("aDoc.fxml"));
        anchorpaneHome.getChildren().setAll(nextPage);
    }

    @FXML
    void onClickLogout(ActionEvent event) throws IOException {
        AnchorPane nextPage = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        mainAnchorPane.getChildren().setAll(nextPage);
    }

    @FXML
    void onClickPatient_btn(ActionEvent event) throws IOException {
        AnchorPane nextPage = FXMLLoader.load(getClass().getResource("aPatients.fxml"));
        anchorpaneHome.getChildren().setAll(nextPage);
    }

    @FXML
    void onDashboard_btnClick(ActionEvent event) throws IOException {
        AnchorPane nextPage = FXMLLoader.load(getClass().getResource("aDashboard.fxml"));
        anchorpaneHome.getChildren().setAll(nextPage);
    }

}
