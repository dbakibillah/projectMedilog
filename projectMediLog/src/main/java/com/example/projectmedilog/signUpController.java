package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
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
    private TextField TF_FullName;
    @FXML
    private TextField TF_UserName;
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
    void onBTNsignupClicked(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        //checking if all fields are filled or not
        if (TF_FullName.getText().isEmpty() || TF_UserName.getText().isEmpty() || TF_age.getText().isEmpty() || TF_phone.getText().isEmpty() || TF_email.getText().isEmpty() || TF_pass.getText().isEmpty()) {
            emptyFieldsCheck();
        } else {
            String FullName = TF_FullName.getText();
            String UserName = TF_UserName.getText();
            String Gender = this.Gender;
            String Age = TF_age.getText();
            String Phone = TF_phone.getText();
            String Email = TF_email.getText();
            String Pass = TF_pass.getText();

            //Checking already signed up or not
            Connection connection = database.dbconnect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                //UserName check
                if (resultSet.getString("UserName").equals(UserName)) {
                    TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                    TF_UserName.clear();
                    TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
                    TF_UserName.setPromptText("UserName Already Taken!");
                    count++;
                    break;
                }
                if (resultSet.getString("Email").equals(Email)) {
                    count++;
                    gotoErrorDialog("Signup.fxml", "Already Signed Up!");
                    break;
                }
            }
            if (count == 0) {
                createAccount(FullName, UserName, Gender, Age, Phone, Email, Pass);
            }

        }
    }

    void createAccount(String FullName, String UserName, String Gender, String Age, String Phone, String Email, String Pass) throws SQLException, ClassNotFoundException {
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        //Writing data to mysql: "projectmedilog -> users"
        if (count == 0) {
            try (
                    PreparedStatement pst = connection.prepareStatement("insert into users(FullName, UserName, Gender, Age, Phone, Email, Pass) values(?, ?, ?, ?, ?, ?, ?)")
            ) {
                pst.setString(1, FullName);
                pst.setString(2, UserName);
                pst.setString(3, Gender);
                pst.setString(4, Age);
                pst.setString(5, Phone);
                pst.setString(6, Email);
                pst.setString(7, Pass);
                pst.executeUpdate();
                pst.close();
                gotoSuccessDialog("userLogin.fxml", "Signup Successfull...");
                //this.changeScene(event, "okay.fxml", "Signup Successful...");


            } catch (SQLException e) {
                System.out.println(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Writing into medical_records table: "projectmedilog -> medical_records"
            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into medical_records(UserName) values(?)")) {
                preparedStatement.setString(1, UserName);
                preparedStatement.executeUpdate();
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
        controller.successDialog(dialogStage, message, 2);
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
        controller.errorDialog(dialogStage, message, 3);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.show();

        //  AnchorPane loginPage = FXMLLoader.load(getClass().getResource(fxml));
        //  anchorPane.getChildren().setAll(loginPage);

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

    public void initialize() {
        TF_FullName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_FullName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_FullName.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_FullName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_FullName.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");
                TF_FullName.setPromptText("Full Name Required!");
            }
        });
        TF_UserName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");
                TF_UserName.setPromptText("UserName Required!");
            }
        });
        TF_age.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_age.setBackground(Background.fill(Color.TRANSPARENT));
                TF_age.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_age.setBackground(Background.fill(Color.TRANSPARENT));
                TF_age.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");
                TF_age.setPromptText("Age Required!");
            }
        });
        TF_phone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
                TF_phone.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
                TF_phone.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");
                TF_phone.setPromptText("Phone Required!");
            }
        });
        TF_email.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_email.setBackground(Background.fill(Color.TRANSPARENT));
                TF_email.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_email.setBackground(Background.fill(Color.TRANSPARENT));
                TF_email.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");
                TF_email.setPromptText("Email Required!");
            }
        });
        TF_pass.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_pass.setBackground(Background.fill(Color.TRANSPARENT));
                TF_pass.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_pass.setBackground(Background.fill(Color.TRANSPARENT));
                TF_pass.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");
                TF_pass.setPromptText("Password Required!");
            }
        });
    }

    void emptyFieldsCheck() {
        if (TF_FullName.getText().isEmpty()) {
            TF_FullName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_FullName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_FullName.setPromptText("The Field is Empty");
        } else {
            TF_FullName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_FullName.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (TF_UserName.getText().isEmpty()) {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_UserName.setPromptText("The Field is Empty");
        } else {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (TF_age.getText().isEmpty()) {
            TF_age.setBackground(Background.fill(Color.TRANSPARENT));
            TF_age.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_age.setPromptText("The Field is Empty");
        } else {
            TF_age.setBackground(Background.fill(Color.TRANSPARENT));
            TF_age.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (TF_phone.getText().isEmpty()) {
            TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
            TF_phone.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_phone.setPromptText("The Field is Empty");
        } else {
            TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
            TF_phone.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (TF_email.getText().isEmpty()) {
            TF_email.setBackground(Background.fill(Color.TRANSPARENT));
            TF_email.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_email.setPromptText("The Field is Empty");
        } else {
            TF_email.setBackground(Background.fill(Color.TRANSPARENT));
            TF_email.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (TF_pass.getText().isEmpty()) {
            TF_pass.setBackground(Background.fill(Color.TRANSPARENT));
            TF_pass.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_pass.setPromptText("The Field is Empty");
        } else {
            TF_pass.setBackground(Background.fill(Color.TRANSPARENT));
            TF_pass.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
    }
}
