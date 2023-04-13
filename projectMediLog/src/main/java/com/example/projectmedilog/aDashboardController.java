package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class aDashboardController {

    @FXML
    private Pane Pane_Appointments;

    @FXML
    private Pane Pane_Doctors;

    @FXML
    private Pane Pane_Ppatients;

    @FXML
    void onMouseClicked_App(MouseEvent event) {

    }

    @FXML
    void onMouseClicked_Doctors(MouseEvent event) {

    }

    @FXML
    void onMouseClicked_Patients(MouseEvent event) {

    }

    @FXML
    void onMouseEntered_App(MouseEvent event) {
        Pane_Appointments.setCursor(Cursor.HAND);
        Pane_Appointments.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEntered_Doctors(MouseEvent event) {
        Pane_Doctors.setCursor(Cursor.HAND);
        Pane_Doctors.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEntered_Patients(MouseEvent event) {
        Pane_Ppatients.setCursor(Cursor.HAND);
        Pane_Ppatients.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseExited_App(MouseEvent event) {
        Pane_Appointments.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExited_Doctors(MouseEvent event) {
        Pane_Doctors.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExited_Patients(MouseEvent event) {
        Pane_Ppatients.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

}
