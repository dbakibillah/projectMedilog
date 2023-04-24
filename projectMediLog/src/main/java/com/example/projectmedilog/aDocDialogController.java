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


public class aDocDialogController {
    @FXML
    private Button BTN_Close;

    @FXML
    private Label Label_Age;

    @FXML
    private Label Label_Mobile;

    @FXML
    private Label Label_Name;


    @FXML
    private Label Label_Degree;

    @FXML
    private Label Label_Department;

    @FXML
    private Label Label_UserName;

    @FXML
    private Label Label_Gender;

    Stage stage;
    void showDialog(Stage dialogStage, String Name, String UserName, String Gender, String Age, String Mobile, String Degree,  String Department) throws IOException {
        this.stage = dialogStage;
        Label_Name.setText(Name);
        Label_UserName.setText(UserName);
        Label_Gender.setText(Gender);
        Label_Age.setText(Age);
        Label_Mobile.setText(Mobile);
        Label_Degree.setText(Degree);
        Label_Department.setText(Department);




        FXMLLoader fxmlLoader = new FXMLLoader(DialogController.class.getResource("aDocDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialogStage.setTitle("Doctors");
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
