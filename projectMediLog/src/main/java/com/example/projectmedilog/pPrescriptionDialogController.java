package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class pPrescriptionDialogController {

    @FXML
    private Button BTN_Close;

    @FXML
    private Label Label_Createdby;

    @FXML
    private Label Label_Date;

    @FXML
    private Label Label_Disease;

    @FXML
    private Label Label_Medicine;

    @FXML
    private Label Label_Name;

    @FXML
    private Label Label_Test;

    void showDialog(Stage dialogStage, String message){
//        successLabel.setText(message);
//        FXMLLoader fxmlLoader = new FXMLLoader(DialogController.class.getResource("successDialog.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        dialogStage.setTitle("Successfull");
//        dialogStage.setScene(scene);
//        dialogStage.show();
    }
    @FXML
    void onMouseCLickedBTN_Close(MouseEvent event) {

    }

    @FXML
    void onMouseEnteredBTN_Close(MouseEvent event) {

    }

}
