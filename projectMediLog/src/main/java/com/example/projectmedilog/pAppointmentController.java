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

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.sql.*;
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
    private TextField TF_age;

    @FXML
    private TextField TF_injury_or_condition;

    @FXML
    private TextField TF_phone;

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
    void onClickBTN_confirm(ActionEvent event) throws SQLException, ClassNotFoundException {

        String Name = TF_name.getText();
        String Gender = this.Gender;
        String Date = TF_date.getText();
        String Time = CB_time.getValue().toString();
        String Phone = TF_phone.getText();
        String Doctor = CB_doctor.getValue().toString();
        String InjuryOrCondition = TF_injury_or_condition.getText();
        String Age = TF_age.getText();

        //add to database

        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();

        try (
                PreparedStatement pst = connection.prepareStatement("insert into appointment(Name, Email, Gender, Date, Time, Phone, Injury_or_Condition, Doctor, Age) values(?, ?, ?, ?, ?, ?, ?, ?, ?)")
        ) {
            pst.setString(1, Name);
            pst.setString(2, user.getEmail());
            pst.setString(3, Gender);
            pst.setString(4, Date);
            pst.setString(5, Time);
            pst.setString(6, Phone);
            pst.setString(7, InjuryOrCondition);
            pst.setString(8, Doctor);
            pst.setString(9, Age);
            pst.executeUpdate();

            System.out.println("Appointment added");

        } catch (SQLException e) {
            System.out.println(e);
        }
        //add to table

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

        CB_doctor.getItems().addAll("Dr. A", "Dr. B", "Dr. C", "Dr. D", "Dr. E");
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
