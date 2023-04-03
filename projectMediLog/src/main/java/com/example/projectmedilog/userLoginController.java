package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class userLoginController {

    @FXML
    private Button BTN_login;

    @FXML
    private RadioButton RB_Admin;

    @FXML
    private RadioButton RB_Doctor;

    @FXML
    private RadioButton RB_User;

    @FXML
    private TextField TF_email;

    @FXML
    private PasswordField TF_password;

    @FXML
    private Hyperlink hyperSignup;

    @FXML
    private AnchorPane page;

    @FXML
    private ToggleGroup usertype;
    private String userType;
    private int count = 0;

    @FXML
    void userType(ActionEvent event) {
        if (RB_User.isSelected())
            this.userType = "User";
        else if (RB_Admin.isSelected())
            this.userType = "Admin";
        else if (RB_Doctor.isSelected())
            this.userType = "Doctor";
    }

    @FXML
    void onClickLogin(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String Email = TF_email.getText();
        String Password = TF_password.getText();

        //connecting database
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();

        //login code for user
        if (userType.equals("User")) {
            ResultSet resultSet = statement.executeQuery("select * from signup");
            while (resultSet.next()) {
                if (Email.equals(resultSet.getString("Email")) && Password.equals(resultSet.getString("Pass"))) {
                    AnchorPane homePage = FXMLLoader.load(getClass().getResource("pHome.fxml"));
                    userLogin.mainstage.setTitle("Home");
                    page.getChildren().setAll(homePage);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("wrong email or password");
            }
        }

        //login code for doctor

        //login code for admin
    }

    @FXML
    void onClickSignUp(ActionEvent event) throws IOException {
        AnchorPane signUpPage = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        page.getChildren().setAll(signUpPage);
        userLogin.mainstage.setTitle("Sign up");
    }


}
