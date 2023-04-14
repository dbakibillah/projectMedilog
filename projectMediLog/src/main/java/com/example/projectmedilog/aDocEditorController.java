package com.example.projectmedilog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
    private TextField TF_date;

    @FXML
    private TextField TF_email;

    @FXML
    private TextField TF_name;

    @FXML
    private TextField TF_phone;
    @FXML
    private TextField TF_password;

    @FXML
    private AnchorPane anchorPane;



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
    void onClickBTN_delete(ActionEvent event) {

    }

    @FXML
    void onClickBTN_save(ActionEvent event) throws SQLException, ClassNotFoundException, IOException, InterruptedException {


        if (TF_name.getText().isEmpty() || TF_email.getText().isEmpty() || TF_password.getText().isEmpty()|| this.Gender == null || TF_age.getText().isEmpty() || TF_phone.getText().isEmpty() || CB_degree.getValue().isEmpty() || CB_department.getValue().isEmpty() || TF_date.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }
        String Name = TF_name.getText();
        String Password = TF_password.getText();
        String Email = TF_email.getText();
        String Gender = this.Gender;
        String Age = TF_age.getText();
        String Phone = TF_phone.getText();
        String Degree = CB_degree.getValue().toString();
        String Department = CB_department.getValue().toString();
        String Date = TF_date.getText();

        // add to database
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        try (
                PreparedStatement pst = connection.prepareStatement("insert into doctors(Name, Email, pass, Gender,  Age, Phone, Degree, Department,Date) values(?, ?, ?, ?, ?, ?, ?, ?, ?)")
        ) {

            pst.setString(1, Name);
            pst.setString(2, Email);
            pst.setString(3, Password);
            pst.setString(4, Gender);
            pst.setString(5, Age);
            pst.setString(6, Phone);
            pst.setString(7, Degree);
            pst.setString(8, Department);
            pst.setString(9, Date);
            pst.executeUpdate();

           System.out.println("Appointment added");
// clear all fields
            TF_name.clear();
            TF_email.clear();
            TF_password.clear();
            this.Gender = null;
            TF_age.clear();
            TF_phone.clear();
            CB_degree.setValue(null);
            CB_department.setValue(null);
            TF_date.clear();
            gotoSuccessDialog("Doctor added successfully");

        } catch (SQLException e) {
            System.out.println(e);
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      // doctor degree add

        CB_degree.getItems().addAll("MBBS", "BHMS", "MS",  "PhD");
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


    }
}
