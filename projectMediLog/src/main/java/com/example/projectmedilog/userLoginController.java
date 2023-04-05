package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private AnchorPane anchorPane;

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
                    String userName = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
                    //this.changeScene(event, "pHome.fxml", userName, Email);
                    gotoSuccessDialog("pHome.fxml", "Login Successfull");
                    count++;
                }
            }
            if (count == 0) {
                gotoErrorDialog("userLogin.fxml", "Wrong username or password!");
            }
        }

        //login code for doctor
        if (userType.equals("Doctor")) {

            ResultSet resultSet = statement.executeQuery("select * from doctors");
            while (resultSet.next()) {
                if (Email.equals(resultSet.getString("Email")) && Password.equals(resultSet.getString("Pass"))) {
                    String userName = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
                    //this.changeScene(event, "pHome.fxml", userName, Email);
                    gotoSuccessDialog("dHome.fxml", "Login Successfull");
                    count++;
                }
            }
            if (count == 0) {
                //System.out.println("wrong email or password");
                gotoErrorDialog("userLogin.fxml", "Wrong username or password!");
            }
        }

        //login code for admin
        if (userType.equals("Admin")) {
            ResultSet resultSet = statement.executeQuery("select * from admins");
            while (resultSet.next()) {
                if (Email.equals(resultSet.getString("Email")) && Password.equals(resultSet.getString("Pass"))) {
                    String userName = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
                    //this.changeScene(event, "pHome.fxml", userName, Email);
                    gotoSuccessDialog("aHome.fxml", "Login Successfull");
                    count++;
                }
            }
            if (count == 0) {
                //System.out.println("wrong email or password");
                gotoErrorDialog("userLogin.fxml", "Wrong username or password!");
            }
        }
    }

    void gotoSuccessDialog(String fxml, String message) throws IOException {
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("successDialog.fxml"));
        Parent root = loader.load();
        DialogController controller = loader.getController();
        controller.successDialog(dialogStage, message);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.show();

        AnchorPane loginPage = FXMLLoader.load(getClass().getResource(fxml));
        anchorPane.getChildren().setAll(loginPage);
    }

    void gotoErrorDialog(String fxml, String message) throws IOException {
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("errorDialog.fxml"));
        Parent root = loader.load();
        DialogController controller = loader.getController();
        controller.errorDialog(dialogStage, message);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.show();

        AnchorPane loginPage = FXMLLoader.load(getClass().getResource(fxml));
        anchorPane.getChildren().setAll(loginPage);
    }

//    void changeScene(ActionEvent event, String fxml, String userName, String Email) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
//        Parent root = loader.load();
//
//        pHomeController phomecontroller = loader.getController();
//        phomecontroller.userLabel.setText(userName);
//        phomecontroller.userEmail.setText(Email);
//        Stage secondStage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
//        secondStage.setScene(new Scene(root));
//    }

    @FXML
    void onClickSignUp(ActionEvent event) throws IOException {
        AnchorPane signUpPage = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        anchorPane.getChildren().setAll(signUpPage);
        userLogin.mainstage.setTitle("Sign up");
    }


}
