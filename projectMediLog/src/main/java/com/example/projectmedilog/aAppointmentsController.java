package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class aAppointmentsController implements Initializable {

    @FXML
    private Button BTN_NewAppointment;

    @FXML
    private TableColumn<?, ?> TC_date;

    @FXML
    private TableColumn<?, ?> TC_doctor;

    @FXML
    private TableColumn<?, ?> TC_email;

    @FXML
    private TableColumn<?, ?> TC_gender;

    @FXML
    private TableColumn<?, ?> TC_injury;

    @FXML
    private TableColumn<?, ?> TC_mobile;

    @FXML
    private TableColumn<?, ?> TC_name;

    @FXML
    private TableColumn<?, ?> TC_time;



    @FXML
    void onCLickedBTN_NewAppointment(ActionEvent event) {

    }

    @FXML
    void onMouseEnteredBTN_NewAppointment(MouseEvent event) {

    }

    @FXML
    void onMouseExitedBTN_NewAppointment(MouseEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resource) {


    }


}
