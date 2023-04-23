package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class pHomeController implements Initializable {
    @FXML
    private Button BTN_prescription;
    @FXML
    Label UserName;
    @FXML
    Pane homePane;
    @FXML
    Pane anchorpaneHome;

    @FXML
    Circle ImageCIrcle = new Circle();

    @FXML
    private AnchorPane pHomeAnchor;
    public static String pUserName = "";

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
    void onCLickMedicalRecords(ActionEvent event) throws IOException {
        AnchorPane pMedicalRecordsAnchorPane = FXMLLoader.load(getClass().getResource("pMedicalRecords.fxml"));
        anchorpaneHome.getChildren().setAll(pMedicalRecordsAnchorPane);
    }

    @FXML
    void onClickSettings(ActionEvent event) throws IOException {
        Pane SettingPane = FXMLLoader.load(getClass().getResource("Settings.fxml"));

        //SettingsController settingsController = new SettingsController();
        //settingsController.getUserData(userEmail.getText());
        anchorpaneHome.getChildren().setAll(SettingPane);
    }

    public void setImageCIrcle() {
        try {
            //check if image is not null then not display default image
            if (user.getImage() != null) {
                InputStream inputStream = user.getImage().getBinaryStream();
                Image image = new Image(new ByteArrayInputStream(inputStream.readAllBytes()));
                ImageCIrcle.setFill(new ImagePattern(image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void onClickBTN_Others(ActionEvent event) {
        AnchorPane nextPage = null;
        try {
            nextPage = FXMLLoader.load(getClass().getResource("others.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpaneHome.getChildren().setAll(nextPage);
    }

    @FXML
    void onClickLogout(ActionEvent event) throws IOException {
        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        pHomeAnchor.getChildren().setAll(loginPage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        pUserName = user.getUserName();
        Pane DashboardPane = null;
        try {
            DashboardPane = FXMLLoader.load(getClass().getResource("pDashboard.fxml"));

            //set userLabel in Dashboard
            pDashboardController pDashboardController = new pDashboardController();
//           pDashboardController.userLabel.setText(userLabel);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpaneHome.getChildren().setAll(DashboardPane);
        setImageCIrcle();
    }
}


