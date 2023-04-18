package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class aMedicalReportController {

    @FXML
    private Button BTN_Save;

    @FXML
    private Button BTN_Search;

    @FXML
    private Button BTN_Update;

    @FXML
    private DatePicker DTpicker;

    @FXML
    private TextArea TA_Comment;

    @FXML
    private TextArea TA_Conclusion;

    @FXML
    private TextField TF_BP;

    @FXML
    private TextField TF_GL;

    @FXML
    private TextField TF_HB;

    @FXML
    private TextField TF_Reference;

    @FXML
    private TextField TF_TestName;

    @FXML
    private TextField TF_TestResult;

    @FXML
    private TextField TF_UserName;

    @FXML
    void onActionDTpicker(ActionEvent event) {
        LocalDate getdate = DTpicker.getValue();
        String Date = getdate.toString();
        System.out.println("Selected Date: " + Date);
    }

    @FXML
    void onCLickBTN_Search(ActionEvent event) {
        if (TF_UserName.getText().isEmpty()) {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_UserName.setPromptText("User Name is Empty*");
        } else {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #00ff00 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: green;");
        }

    }

    @FXML
    void onClickBTN_Save(ActionEvent event) {

    }

    @FXML
    void onClickBTN_Update(ActionEvent event) {
        String UserName = TF_UserName.getText();
        String BloodPressure[] = TF_BP.getText().split("/");
        String Glucose = TF_GL.getText();
        String HeartBeat = TF_HB.getText();
        String BP_systolic = BloodPressure[0];
        String BP_diastolic = BloodPressure[1];
    }

    @FXML
    void onMouseEnteredBTN_Save(MouseEvent event) {

    }

    @FXML
    void onMouseEnteredBTN_Update(MouseEvent event) {

    }

    @FXML
    void onMouserEnteredBTN_Search(MouseEvent event) {

    }


    public void initialize() {
        TF_UserName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #00ff00 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: green;");
            }
            else {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: yellow;");
            }
        });
    }

}
