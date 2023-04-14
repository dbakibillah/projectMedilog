package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class aDocController {

    @FXML
    private Button BTN_AddDoc;

    @FXML
    void onCLickedBTN_AddDoc(ActionEvent event) {

    }

    @FXML
    void onMouseEnteredBTN_AddDoc(MouseEvent event) {
        BTN_AddDoc.setCursor(Cursor.HAND);
        BTN_AddDoc.setBackground(Background.fill(Color.WHITE));
    }

    @FXML
    void onMouseExitedBTN_AddDoc(MouseEvent event) {
        BTN_AddDoc.setBackground(Background.fill(Color.web("#0080FF")));
    }

}
