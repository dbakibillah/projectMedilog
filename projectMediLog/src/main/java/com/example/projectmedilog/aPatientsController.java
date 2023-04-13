package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class aPatientsController {

    @FXML
    private Button BTN_AddPatient;

    @FXML
    void onCLickedBTN_AddDoc(ActionEvent event) {

    }

    @FXML
    void onMouseEnteredBTN_AddPatient(MouseEvent event) {
        BTN_AddPatient.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseExitedBTN_AddPatient(MouseEvent event) {

    }

}
