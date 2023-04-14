package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

import java.sql.SQLException;

public class SettingsController {

    @FXML
    private Button BTN_ChooseFile;

    @FXML
    private Button BTN_Save;

    @FXML
    private Button BTN_SaveChange;

    @FXML
    private Button BTN_Upload;

    @FXML
    private ChoiceBox<?> CB_bloodgrp;

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
    void onClickBTN_Save(ActionEvent event) {

    }

    @FXML
    void onClickBTN_SaveChange(ActionEvent event) {

    }

}
