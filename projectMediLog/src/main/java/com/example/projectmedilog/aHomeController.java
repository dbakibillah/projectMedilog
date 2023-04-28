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

public class aHomeController implements Initializable {

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
    Label UserName;

    @FXML
    private Pane homePane;
    @FXML
    Circle ImageCIrcle = new Circle();
    public static String aUserName;



    @FXML
    void onClickChat(ActionEvent event) throws IOException {
        AnchorPane ChatPane = FXMLLoader.load(getClass().getResource("chat.fxml"));
        anchorpaneHome.getChildren().setAll(ChatPane);
    }
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

    @FXML
    void onClickSettings(ActionEvent event) throws IOException {
        Pane SettingPane = FXMLLoader.load(getClass().getResource("aSettings.fxml"));
        anchorpaneHome.getChildren().setAll(SettingPane);
    }

    @FXML
    void onCLickMedicalRecords(ActionEvent event) {
        AnchorPane MedicalRecord = null;
        try {
            MedicalRecord = FXMLLoader.load(getClass().getResource("aMedicalReport.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpaneHome.getChildren().setAll(MedicalRecord);
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
    public void setImageCIrcle() {
        try {
            //check if image is not null then not display default image
            if (admin.getImage() != null) {
                InputStream inputStream = admin.getImage().getBinaryStream();
                Image image = new Image(new ByteArrayInputStream(inputStream.readAllBytes()));
                ImageCIrcle.setFill(new ImagePattern(image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {

        aUserName = admin.getUserName();
        UserName.setText(aUserName);
        System.out.println(aUserName);
        Pane DashboardPane = null;
        try {
            DashboardPane = FXMLLoader.load(getClass().getResource("aDashboard.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpaneHome.getChildren().setAll(DashboardPane);
        setImageCIrcle();
    }
}
