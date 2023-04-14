package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class aPrescriptionDialogController {

    @FXML
    private Button BTN_Add;

    @FXML
    private Button BTN_Clear;
    @FXML
    TextField TF_Email;

    @FXML
    TextField TF_Name;
    @FXML
    private TextField TF_Createdby;

    @FXML
    private TextField TF_Date;

    @FXML
    private TextField TF_Disease;



    @FXML
    private TextField TF_Medicine;



    @FXML
    private TextField TF_Test;

    @FXML
    private Button BTN_Delete;
    Stage stage;
    void showDialog(Stage dialogStage, String Name,String Email, String Createdby, String Date, String Disease, String Test, String Medicine) throws IOException {
        this.stage = dialogStage;
        TF_Name.setText(Name);
        TF_Email.setText(Email);
        TF_Createdby.setText(Createdby);
        TF_Date.setText(Date);
        TF_Disease.setText(Disease);
        TF_Test.setText(Test);
        TF_Medicine.setText(Medicine);
        FXMLLoader fxmlLoader = new FXMLLoader(DialogController.class.getResource("aPrescriptionDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialogStage.setTitle("Prescription");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    void onClickBTN_Add() throws IOException, SQLException, ClassNotFoundException {
        // Get value from text fields
        String Name = TF_Name.getText();
        String Email = TF_Email.getText();
        String CreatedBy = TF_Createdby.getText();
        String Date = TF_Date.getText();
        String Disease = TF_Disease.getText();
        String Test = TF_Test.getText();
        String Medicine = TF_Medicine.getText();

        Connection connection = database.dbconnect();


        PreparedStatement pst = connection.prepareStatement("insert into pprescription(Name, Email,CreatedBy,Date,Disease,Test,Medicine)values(?, ?, ?, ?, ?, ?, ?)");
        {
            pst.setString(1,Name);
            pst.setString(2,Email);
            pst.setString(3,CreatedBy);
            pst.setString(4,Date);
            pst.setString(5,Disease);
            pst.setString(6,Test);
            pst.setString(7,Medicine);
            pst.executeUpdate();
            this.stage.close();
            

        }

    }

    @FXML
    void onClickBTN_Clear(ActionEvent event) {

        TF_Name.setText("");
        TF_Email.setText("");
        TF_Medicine.setText("");
        TF_Date.setText("");
        TF_Createdby.setText("");
        TF_Test.setText("");
        TF_Disease.setText("");
    }

    @FXML
    void onClickBTN_Delete(ActionEvent event) throws SQLException, ClassNotFoundException {
    String sql = "DELETE from pprescription WHERE `email` = '" + TF_Email.getText() + "'";
        Connection connection = database.dbconnect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void showDialog(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(DialogController.class.getResource("aPrescriptionDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Prescription");
        stage.setScene(scene);
        stage.show();
    }
}
