package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class aDocEditorController implements Initializable {
    @FXML
    private Button BTN_cancel;
    @FXML
    private ToggleGroup gender;
    @FXML
    private Button BTN_delete;

    @FXML
    private Button BTN_save;

    @FXML
    private ChoiceBox<String> CB_degree;

    @FXML
    private ChoiceBox<String> CB_department;

    @FXML
    private RadioButton RB_female;

    @FXML
    private RadioButton RB_male;

    @FXML
    private RadioButton RB_others;

    @FXML
    private TextField TF_age;

    @FXML
    private TextField TF_UserName;

    @FXML
    private TextField TF_FullName;

    @FXML
    private TextField TF_phone;
    @FXML
    private TextField TF_password;

    @FXML
    private AnchorPane anchorPane;
    Integer id;


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
        Stage stage = (Stage) BTN_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onClickBTN_delete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String sql = "DELETE from doctors WHERE `id` = " + id;
        Connection connection = database.dbconnect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            gotoSuccessDialog("Doctor deleted successfully");
            //close the window
            Stage stage = (Stage) BTN_delete.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickBTN_save(ActionEvent event) throws SQLException, ClassNotFoundException, IOException, InterruptedException {

        if (TF_UserName.getText().isEmpty() || TF_FullName.getText().isEmpty() || TF_password.getText().isEmpty() || TF_age.getText().isEmpty() || TF_phone.getText().isEmpty()) {
            emptyFieldsCheck();
        } else {
            String UserName = TF_UserName.getText();
            String FullName = TF_FullName.getText();
            String Password = TF_password.getText();
            String Age = TF_age.getText();
            String Phone = TF_phone.getText();
            String Degree = CB_degree.getValue().toString();
            String Department = CB_department.getValue().toString();

            // add to database
            Connection connection = database.dbconnect();
            Statement statement = connection.createStatement();
            try (
                    PreparedStatement pst = connection.prepareStatement("insert into doctors(UserName, FullName, pass, Gender,  Age, Phone, Degree, Department) values(?, ?, ?, ?, ?, ?, ?, ?)")
            ) {

                pst.setString(1, UserName);
                pst.setString(2, FullName);
                pst.setString(3, Password);
                pst.setString(4, Gender);
                pst.setString(5, Age);
                pst.setString(6, Phone);
                pst.setString(7, Degree);
                pst.setString(8, Department);
                pst.executeUpdate();

                gotoSuccessDialog("Doctor added successfully");
                //close the window
                Stage stage = (Stage) BTN_save.getScene().getWindow();
                stage.close();

            } catch (SQLException e) {
                System.out.println(e);
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
    }

    @FXML
    void onMouseEnteredBTN_Cancel(MouseEvent event) {
        BTN_cancel.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEnteredBTN_Delete(MouseEvent event) {
        BTN_delete.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEnteredBTN_Save(MouseEvent event) {
        BTN_save.setCursor(Cursor.HAND);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // doctor degree add

        CB_degree.getItems().addAll("MBBS", "BHMS", "MS", "PhD");
        CB_degree.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return (s == null) ? "Nothing selected" : s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }
        });

        // doctor department add
        CB_department.getItems().addAll("Cardiology", "Dermatology", "ENT", "Gastroenterology", "General Medicine", "General Surgery", "Gynecology", "Neurology", "Ophthalmology", "Orthopedics", "Pediatrics", "Psychiatry", "Urology");
        CB_department.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return (s == null) ? "Nothing selected" : s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }
        });

        TF_FullName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_FullName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_FullName.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_FullName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_FullName.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_UserName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_password.setBackground(Background.fill(Color.TRANSPARENT));
                TF_password.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_password.setBackground(Background.fill(Color.TRANSPARENT));
                TF_password.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_age.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_age.setBackground(Background.fill(Color.TRANSPARENT));
                TF_age.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_age.setBackground(Background.fill(Color.TRANSPARENT));
                TF_age.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_phone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
                TF_phone.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
                TF_phone.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
    }

    void emptyFieldsCheck() {
        if (TF_FullName.getText().isEmpty()) {
            TF_FullName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_FullName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_FullName.setPromptText("Name is Empty*");
        } else {
            TF_FullName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_FullName.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }
        if (TF_UserName.getText().isEmpty()) {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_UserName.setPromptText("Email is Empty*");
        } else {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }
        if (TF_password.getText().isEmpty()) {
            TF_password.setBackground(Background.fill(Color.TRANSPARENT));
            TF_password.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_password.setPromptText("Password is Empty*");
        } else {
            TF_password.setBackground(Background.fill(Color.TRANSPARENT));
            TF_password.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }
        if (TF_age.getText().isEmpty()) {
            TF_age.setBackground(Background.fill(Color.TRANSPARENT));
            TF_age.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_age.setPromptText("Age is Empty*");
        } else {
            TF_age.setBackground(Background.fill(Color.TRANSPARENT));
            TF_age.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }
        if (TF_phone.getText().isEmpty()) {
            TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
            TF_phone.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_phone.setPromptText("Phone is Empty*");
        } else {
            TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
            TF_phone.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }
        if (CB_degree.getValue() == null) {
            CB_degree.setBackground(Background.fill(Color.TRANSPARENT));
            CB_degree.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
        } else {
            CB_degree.setBackground(Background.fill(Color.TRANSPARENT));
            CB_degree.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 00;");
        }
        if (CB_department.getValue() == null) {
            CB_department.setBackground(Background.fill(Color.TRANSPARENT));
            CB_department.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
        } else {
            CB_department.setBackground(Background.fill(Color.TRANSPARENT));
            CB_department.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 00;");
        }
        if (!RB_male.isSelected() && !RB_female.isSelected() && !RB_others.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Gender is Empty");
            alert.setContentText("Please Select Gender");
            alert.showAndWait();
        }
    }

}
