package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;

import java.sql.*;

public class signUpController {
    @FXML
    private Button BTN_signup;

    @FXML
    private TextField TF_FirstName;

    @FXML
    private TextField TF_LastName;

    @FXML
    private TextField TF_email;

    @FXML
    private PasswordField TF_pass;

    @FXML
    private TextField TF_phone;


    @FXML
    void onBTNsignupClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String FirstName = TF_FirstName.getText();
        String LastName = TF_LastName.getText();
        String Email = TF_email.getText();
        String pass = TF_pass.getText();
        String phone = TF_phone.getText();

        Patient p =new Patient(FirstName,LastName,Email,pass,phone);

        // Statement statement = connection.createStatement();
        // statement.executeUpdate(p.toString());

        try (
                Connection connection = database.dbconnect();
                PreparedStatement pst = connection.prepareStatement("insert into signup(firstname,lastname,email,pass,phone) values(?,?,?,?,?)")
        ){
        pst.setString(1,FirstName);
        pst.setString(2,LastName);
        pst.setString(3,Email);
        pst.setString(4,pass);
        pst.setString(5,phone);
        pst.executeUpdate();
    } catch (SQLException e) {
            // print SQL exception information.
            System.out.println(e);
        }
    }
}
