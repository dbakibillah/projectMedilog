package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingsController implements  Initializable {

    @FXML
    private Button BTN_ChooseFile;

    @FXML
    private Button BTN_Save;

    @FXML
    private Button BTN_SaveChange;

    @FXML
    private Button BTN_Upload;

    @FXML
    private ChoiceBox<String> CB_bloodgrp;


    @FXML
    public Circle ImageCIrcle;

    @FXML
    private TextField TF_address;

    @FXML
    private TextField TF_age;

    @FXML
    PasswordField TF_currentpass;

    @FXML
    private TextField TF_email;

    @FXML
    private TextField TF_firstname;

    @FXML
    private TextField TF_lastname;

    @FXML
    private TextField TF_mobile;

    @FXML
    private PasswordField TF_newpass;

    @FXML
    private TextField TF_username;

    @FXML
    void onCLickBTN_ChooseFile(ActionEvent event) throws SQLException {
        ImageUpload imageUpload = new ImageUpload();
        imageUpload.selectImage();
        //imageUpload.displayImage();
    }

    @FXML
    void onCLickBTN_Upload(ActionEvent event) {

    }

    @FXML
    void onClickBTN_Save(ActionEvent event)throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        String UserName = TF_username.getText();
        String CurrentPass = TF_currentpass.getText();
        String NewPass = TF_newpass.getText();
    }

    @FXML
    void onClickBTN_SaveChange(ActionEvent event) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        String FirstName = TF_firstname.getText();
        String LastName = TF_lastname.getText();
        String Email = TF_email.getText();
        String Age = TF_age.getText();
        String Phone = TF_mobile.getText();

        String Address = TF_address.getText();
        String BloodGroup = CB_bloodgrp.getValue().toString();

    }

    void getData(){}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CB_bloodgrp.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        CB_bloodgrp.setConverter(new StringConverter<String>() {
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