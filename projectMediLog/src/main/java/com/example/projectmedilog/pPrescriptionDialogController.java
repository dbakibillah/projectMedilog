package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
    @FXML
    private Label Label_UserName;
    Stage stage;

    void showDialog(Stage dialogStage, String Name, String UserName, String Createdby, String Date, String Disease, String Test, String Medicine) throws IOException {
        this.stage = dialogStage;
        Label_Name.setText(Name);
        Label_UserName.setText(UserName);
        Label_Createdby.setText(Createdby);
        Label_Date.setText(Date);
        Label_Disease.setText(Disease);
        Label_Test.setText(Test);
        Label_Medicine.setText(Medicine);
        FXMLLoader fxmlLoader = new FXMLLoader(DialogController.class.getResource("pPrescriptionDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialogStage.setTitle("Prescription");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    void onMouseCLickedBTN_Close(MouseEvent event) {
        this.stage.close();
    }

    @FXML
    void onMouseEnteredBTN_Close(MouseEvent event) {
        BTN_Close.setCursor(Cursor.HAND);
    }

}
