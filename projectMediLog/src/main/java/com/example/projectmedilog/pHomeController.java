package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class pHomeController implements Initializable {
    @FXML
    private Button BTN_prescription;

    @FXML
    Label userEmail;
    @FXML
    Pane homePane;
    @FXML
    Pane anchorpaneHome;
    @FXML
    private AnchorPane pHomeAnchor;

    public String userLabel = "";

    public void onClickAppointment_btn(ActionEvent event) throws IOException {
        Pane AppointtmentPane = FXMLLoader.load(getClass().getResource("pAppointment.fxml"));
        anchorpaneHome.getChildren().setAll(AppointtmentPane);
    }

    public void onDashboard_btnClick(ActionEvent event) throws IOException {
        Pane DashboardPane = FXMLLoader.load(getClass().getResource("pDashboard.fxml"));
        anchorpaneHome.getChildren().setAll(DashboardPane);
    }

    @FXML
    void onClickPrescription(ActionEvent event) throws IOException {
        AnchorPane pPrescriptionAnchorPane = FXMLLoader.load(getClass().getResource("pPrescription.fxml"));
        anchorpaneHome.getChildren().setAll(pPrescriptionAnchorPane);
    }

    @FXML
    void onCLickMedicalRecords(ActionEvent event) {

    }

    @FXML
    void onClickSettings(ActionEvent event) throws IOException {
        Pane SettingPane = FXMLLoader.load(getClass().getResource("Settings.fxml"));

        //SettingsController settingsController = new SettingsController();
        //settingsController.getUserData(userEmail.getText());
        anchorpaneHome.getChildren().setAll(SettingPane);
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

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        Pane DashboardPane = null;
        try {
            DashboardPane = FXMLLoader.load(getClass().getResource("pDashboard.fxml"));

            //set userLabel in Dashboard
            pDashboardController pDashboardController = new pDashboardController();
//            pDashboardController.userLabel.setText(userLabel);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpaneHome.getChildren().setAll(DashboardPane);
    }
}


