package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class aAppointmentsEditorController implements Initializable {

    @FXML
    private Button BTN_cancel;

    @FXML
    private Button BTN_delete;

    @FXML
    private Button BTN_save;

    @FXML
    private ChoiceBox<String> CB_time;

    @FXML
    private RadioButton RB_female;

    @FXML
    private RadioButton RB_male;

    @FXML
    private RadioButton RB_others;

    @FXML
    private TextField TF_date;

    @FXML
    private TextField TF_doctor;

    @FXML
    private TextField TF_email;

    @FXML
    private TextField TF_injury_or_condition;

    @FXML
    private TextField TF_mobile;

    @FXML
    private TextField TF_name;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CB_time.getItems().addAll("09:00 - 11:00", "11:00 - 13:00", "17:00 - 19:00", "19:00 - 21:00", "21:00 - 23:00");
        CB_time.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return (s == null) ? "Nothing selected" : s;
            }
            @Override
            public String fromString(String s) {
                return null;
            }
        });
    }

}
