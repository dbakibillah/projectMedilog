package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class aAppointmentsController {

    @FXML
    private Button BTN_NewAppointment;

    @FXML
    void onCLickedBTN_NewAppointment(ActionEvent event) {

    }

    @FXML
    void onMouseEnteredBTN_NewAppointment(MouseEvent event) {
        BTN_NewAppointment.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseExitedBTN_NewAppointment(MouseEvent event) {

    }

}
