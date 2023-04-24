package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
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
    private TextField TF_UserName;

    @FXML
    private PasswordField TF_password;

    @FXML
    private Hyperlink hyperSignup;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ToggleGroup usertype;
    private String userType = "";
    private int count = 0;

    @FXML
    void onMouseEnteredRB_Admin(MouseEvent event) {
        RB_Admin.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEnteredRB_Doctor(MouseEvent event) {
        RB_Doctor.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEnteredRB_User(MouseEvent event) {
        RB_User.setCursor(Cursor.HAND);
    }

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
    void onMouseEnteredBTN_Login(MouseEvent event) {
        BTN_login.setCursor(Cursor.HAND);
    }

    @FXML
    void onClickLogin(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        //checking if email and password are empty
        emptyFieldsCheck();

        String UserName = TF_UserName.getText();
        String Password = TF_password.getText();

        //connecting database
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();

        if (!userType.equals("")) {
            //login code for user
            if (userType.equals("User")) {
                if (!TF_UserName.getText().isEmpty() && !TF_password.getText().isEmpty()) {
                    ResultSet resultSet = statement.executeQuery("select * from signup");
                    while (resultSet.next()) {
                        if (UserName.equals(resultSet.getString("UserName")) && Password.equals(resultSet.getString("Pass"))) {
                            new user(resultSet.getString("FullName"), resultSet.getString("UserName"), resultSet.getString("Gender"), resultSet.getString("Age"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Address"), resultSet.getString("Blood_Group"), resultSet.getBlob("Image"));
                            String FullName = resultSet.getString("FullName");
                            changeScene(event, "pHome.fxml", FullName, UserName);
                            gotoSuccessDialog("Login Successfull");
                            count++;
                        }
                    }
                    if (count == 0) {
                        gotoErrorDialog("userLogin.fxml", "Wrong username or password!");
                    }
                }
            }

            //login code for doctor
            if (userType.equals("Doctor")) {
                if (!TF_UserName.getText().isEmpty() && !TF_password.getText().isEmpty()) {
                    ResultSet resultSet = statement.executeQuery("select * from doctors");
                    while (resultSet.next()) {
                        if (UserName.equals(resultSet.getString("UserName")) && Password.equals(resultSet.getString("Pass"))) {
                            //String FullName = resultSet.getString("FullName");
                            changeScenedHome(event, "dHome.fxml", UserName);
                            gotoSuccessDialog("Login Successfull");
                            count++;
                        }
                    }
                    if (count == 0) {
                        gotoErrorDialog("userLogin.fxml", "Wrong username or password!");
                    }
                }
            }

            //login code for admin
            if (userType.equals("Admin")) {
                if (!TF_UserName.getText().isEmpty() && !TF_password.getText().isEmpty()) {
                    ResultSet resultSet = statement.executeQuery("select * from admins");
                    while (resultSet.next()) {
                        if (UserName.equals(resultSet.getString("UserName")) && Password.equals(resultSet.getString("Pass"))) {
                            changeSceneaHome(event, "aHome.fxml", UserName);
                            gotoSuccessDialog("Login Successfull");
                            count++;
                        }
                    }
                    if (count == 0) {
                        gotoErrorDialog("userLogin.fxml", "Wrong username or password!");
                    }
                }
            }
        }
    }

    void gotoSuccessDialog(String message) throws IOException {
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("successDialog.fxml"));
        Parent root = loader.load();
        DialogController controller = loader.getController();
        controller.successDialog(dialogStage, message, 2);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.show();

//        AnchorPane loginPage = FXMLLoader.load(getClass().getResource(fxml));
//        anchorPane.getChildren().setAll(loginPage);
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

        AnchorPane loginPage = FXMLLoader.load(getClass().getResource(fxml));
        anchorPane.getChildren().setAll(loginPage);
    }

    void changeScene(ActionEvent event, String fxml, String FullName, String UserName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        pHomeController phomecontroller = loader.getController();
        phomecontroller.UserName.setText(UserName);
        Stage secondStage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
        secondStage.setScene(new Scene(root));
    }

    //login to dHome
    void changeScenedHome(ActionEvent event, String fxml, String Email) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        dHomeController dhomecontroller = loader.getController();
        dhomecontroller.userEmail.setText(Email);
        Stage secondStage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
        secondStage.setScene(new Scene(root));
    }

    // change scene to aHome
    void changeSceneaHome(ActionEvent event, String fxml, String Email) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        aHomeController ahomecontroller = loader.getController();
        ahomecontroller.userEmail.setText(Email);
        Stage secondStage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
        secondStage.setScene(new Scene(root));
    }


    @FXML
    void onClickSignUp(ActionEvent event) throws IOException {
        AnchorPane signUpPage = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        anchorPane.getChildren().setAll(signUpPage);
//        userLogin.mainstage.setTitle("Sign up");
    }

    void emptyFieldsCheck() {
        if (TF_UserName.getText().isEmpty()) {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_UserName.setPromptText("User Name is Empty*");
        } else {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (TF_password.getText().isEmpty()) {
            TF_password.setBackground(Background.fill(Color.TRANSPARENT));
            TF_password.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_password.setPromptText("Password Field is Empty*");
        } else {
            TF_password.setBackground(Background.fill(Color.TRANSPARENT));
            TF_password.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (!RB_Admin.isSelected() && !RB_User.isSelected() && !RB_Doctor.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please Select User Type");
            alert.setContentText("You must select a user type!");
            alert.showAndWait();
        }
    }

    public void initialize() {
        TF_UserName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color:  #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: grey;");
            }
        });
        TF_password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_password.setBackground(Background.fill(Color.TRANSPARENT));
                TF_password.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_password.setBackground(Background.fill(Color.TRANSPARENT));
                TF_password.setStyle("-fx-border-color:  #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: grey;");
            }
        });

    }
    //End of the userLoginCOntroller class
}
