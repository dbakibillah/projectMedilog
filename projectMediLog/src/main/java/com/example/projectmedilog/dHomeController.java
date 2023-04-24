package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dHomeController implements Initializable {


    @FXML
    Label userEmail;
    @FXML
    private Button Appointment_btn;
    @FXML
    private Button BTN_Prescription;

    @FXML
    private Button BTN_Logout;

    @FXML
    private Button Dashboard_btn;

    @FXML
    private Pane Pane_Appointments;

    @FXML
    private Pane Pane_Operation;

    @FXML
    private Pane Pane_Tasks;

    @FXML
    private Pane homePane;

    @FXML
    private AnchorPane anchorpaneHome;
    @FXML
    private AnchorPane dHomeAnchor;


    //public String userLabel = "";
    //code for login

    @FXML
    void onClickedBTN_Prescription(ActionEvent event) throws IOException {
        Pane prescription = FXMLLoader.load(getClass().getResource("aPrescription.fxml"));
        anchorpaneHome.getChildren().setAll(prescription);
    }

    public void onDashboard_btnClick(ActionEvent event) throws IOException {
        Pane DashboardPane = FXMLLoader.load(getClass().getResource("dDashboard.fxml"));
        anchorpaneHome.getChildren().setAll(DashboardPane);
    }

    public void onClickAppointment_btn(ActionEvent event) throws IOException {
        AnchorPane nextPage = FXMLLoader.load(getClass().getResource("aAppointments.fxml"));
        anchorpaneHome.getChildren().setAll(nextPage);
    }

    @FXML
    void onCLickMedicalRecords(ActionEvent event) throws IOException {
//        AnchorPane nextPage = FXMLLoader.load(getClass().getResource("mRecords.fxml"));
//        anchorpaneHome.getChildren().setAll(nextPage);
    }

    @FXML
    void onClickBTN_Others(ActionEvent event) throws IOException {
        AnchorPane nextPage = FXMLLoader.load(getClass().getResource("others.fxml"));
        anchorpaneHome.getChildren().setAll(nextPage);
    }

    public void onClickSettings(ActionEvent event) throws IOException {
        Pane SettingPane = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        anchorpaneHome.getChildren().setAll(SettingPane);
    }

    @FXML
    void onClickLogout(ActionEvent event) throws IOException {
        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("userLogin.fxml"));

        dHomeAnchor.getChildren().setAll(loginPage);
    }

    public void onMouseClicked_App(MouseEvent event) {

    }

    public void onMouseClicked_Operation(MouseEvent event) {

    }

    public void onMouseClicked_Tasks(MouseEvent event) {

    }

    public void onMouseEntered_App(MouseEvent event) {

    }

    public void onMouseEntered_Operation(MouseEvent event) {

    }

    @FXML
    void onMouseEntered_Tasks(MouseEvent event) {

    }

    @FXML
    void onMouseExited_App(MouseEvent event) {

    }

    @FXML
    void onMouseExited_Operation(MouseEvent event) {

    }

    @FXML
    void onMouseExited_Tasks(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        Pane DashboardPane = null;
        try {
            DashboardPane = FXMLLoader.load(getClass().getResource("dDashboard.fxml"));
            // set label text
            //  userEmail.setText(LoginController.userLabel);
            dDashboardController pDashboardController = new dDashboardController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpaneHome.getChildren().setAll(DashboardPane);


        //  setImage CIrcle();


    }
}
