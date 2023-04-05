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
import java.sql.*;

public class signUpController {
    @FXML
    private Button BTN_signup;
    @FXML
    private RadioButton RB_female;
    @FXML
    private RadioButton RB_male;
    @FXML
    private RadioButton RB_others;
    @FXML
    private TextField TF_FirstName;
    @FXML
    private TextField TF_LastName;
    @FXML
    private TextField TF_age;
    @FXML
    private TextField TF_email;
    @FXML
    private PasswordField TF_pass;
    @FXML
    private TextField TF_phone;
    @FXML
    private ToggleGroup gender;
    @FXML
    private Hyperlink login;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    void gotoLogin(ActionEvent event) throws IOException {
        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        anchorPane.getChildren().setAll(loginPage);
    }

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
    void onBTNsignupClicked(ActionEvent event) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        String FirstName = TF_FirstName.getText();
        String LastName = TF_LastName.getText();
        String Gender = this.Gender;
        String Age = TF_age.getText();
        String Phone = TF_phone.getText();
        String Email = TF_email.getText();
        String Pass = TF_pass.getText();

        //Checking already signed up or not
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from signup");

        while (resultSet.next()) {
            if (resultSet.getString("Email").equals(Email)) {
                count++;
                //this.changeScene(event, "okay.fxml", "Signup Successful...");
                gotoErrorDialog("Signup.fxml", "Already Signed Up!");
                break;
            }
        }

        //Writing data to mysql: "projectmedilog -> signup"
        if (count == 0) {
            try (
                    PreparedStatement pst = connection.prepareStatement("insert into signup(FirstName, LastName, Gender, Age, Phone, Email, Pass) values(?, ?, ?, ?, ?, ?, ?)")
            ) {
                pst.setString(1, FirstName);
                pst.setString(2, LastName);
                pst.setString(3, Gender);
                pst.setString(4, Age);
                pst.setString(5, Phone);
                pst.setString(6, Email);
                pst.setString(7, Pass);
                pst.executeUpdate();

                // go to okay.fxml
                gotoSuccessDialog("userLogin.fxml", "Signup Successfull...");
                //this.changeScene(event, "okay.fxml", "Signup Successful...");

            } catch (SQLException e) {
                System.out.println(e);
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

//    void changeScene(ActionEvent event, String fxml, String text) throws IOException, InterruptedException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
//        Parent root = loader.load();
//
//        okayController okay = loader.getController();
//        okay.okaySignup(text);
//        Stage secondStage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
//        secondStage.setScene(new Scene(root));
//    }


}
