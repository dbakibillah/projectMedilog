package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class pAppointmentController implements Initializable {

    @FXML
    private AnchorPane AppointmentPane;

    @FXML
    private Button BTN_cancel;

    @FXML
    private Button BTN_confirm;

    @FXML
    private ChoiceBox<String> CB_doctor;

    @FXML
    private ChoiceBox<String> CB_time;

    @FXML
    private RadioButton RB_female;

    @FXML
    private RadioButton RB_male;

    @FXML
    private RadioButton RB_others;

    @FXML
    private TableColumn<?, ?> TC_date;

    @FXML
    private TableColumn<?, ?> TC_doctor;

    @FXML
    private TableColumn<?, ?> TC_email;

    @FXML
    private TableColumn<?, ?> TC_gender;

    @FXML
    private TableColumn<?, ?> TC_injury_or_condition;

    @FXML
    private TableColumn<?, ?> TC_mobile;

    @FXML
    private TableColumn<?, ?> TC_name;

    @FXML
    private TableColumn<?, ?> TC_time;

    @FXML
    private TextField TF_date;


    @FXML
    private TextField TF_injury_or_condition;

    @FXML
    private TextField TF_mobile;

    @FXML
    private TextField TF_name;


    private String Gender;
    private int count = 0;

    @FXML
    void selectGender(ActionEvent event) {
        //Collecting gender information
        if (RB_male.isSelected())
            this.Gender = "Male";
        else if (RB_female.isSelected())
            this.Gender = "Female";
        else if (RB_others.isSelected())
            this.Gender = "Others";
    }
    @FXML
    void onClickBTN_cancel(ActionEvent event) {

    }

    @FXML
    void onClickBTN_confirm(ActionEvent event) {

        String FirstName = TF_name.getText();
       // String Email = TF_email.getText();
        String Gender = this.Gender;
        String Date = TF_date.getText();
        String Time = CB_time.getValue().toString();
        String Mobile = TF_mobile.getText();
        String Doctor = CB_doctor.getValue().toString();
        String InjuryOrCondition = TF_injury_or_condition.getText();
    }
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

        CB_doctor.getItems().addAll();
        CB_doctor.setConverter(new StringConverter<String>() {
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
