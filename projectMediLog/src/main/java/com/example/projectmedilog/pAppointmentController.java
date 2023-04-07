package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class pAppointmentController {

    @FXML
    private AnchorPane AppointmentPane;

    @FXML
    private Button BTN_cancel;

    @FXML
    private Button BTN_confirm;

    @FXML
    private ChoiceBox<?> CB_doctor;

    @FXML
    private ChoiceBox<?> CB_time;

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


    @FXML
    void onClickBTN_cancel(ActionEvent event) {

    }

    @FXML
    void onClickBTN_confirm(ActionEvent event) {

    }

}
