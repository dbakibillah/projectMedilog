package com.example.projectmedilog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class aPatientsController implements Initializable {

    @FXML
    private TableView<aPatientsTable> PatientTable;

    @FXML
    private TableColumn<aPatientsTable, String> TableColumn_Age;

    @FXML
    private TableColumn<aPatientsTable, String> TableColumn_BloodGroup;

    @FXML
    private TableColumn<aPatientsTable, String> TableColumn_Email;

    @FXML
    private TableColumn<aPatientsTable, String> TableColumn_Gender;

    @FXML
    private TableColumn<aPatientsTable, String> TableColumn_Name;

    @FXML
    private TableColumn<aPatientsTable, String> TableColumn_Phone;
    ObservableList<aPatientsTable> listI = FXCollections.observableArrayList();

    Connection conn;
    ResultSet rs;



    public  void setPatientTableData(){
        try {
            //bringin data from database
            conn = database.dbconnect();
            rs = conn.createStatement().executeQuery("select * from users");

            while (rs.next()) {
                listI.add(new aPatientsTable(rs.getString("FullName"), rs.getString("Email"), rs.getString("Phone"), rs.getString("Gender"), rs.getString("Age"), rs.getString("blood_group")));
            }
            TableColumn_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            TableColumn_Phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            TableColumn_Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            TableColumn_Age.setCellValueFactory(new PropertyValueFactory<>("age"));
            TableColumn_BloodGroup.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));


            TableColumn_Name.setStyle("-fx-alignment: CENTER;");
            TableColumn_Email.setStyle("-fx-alignment: CENTER;");
            TableColumn_Phone.setStyle("-fx-alignment: CENTER;");
            TableColumn_Gender.setStyle("-fx-alignment: CENTER;");
            TableColumn_Age.setStyle("-fx-alignment: CENTER;");
            TableColumn_BloodGroup.setStyle("-fx-alignment: CENTER;");
            PatientTable.refresh();

            PatientTable.setItems(listI);

        } catch (SQLException | ClassNotFoundException e) {


        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setPatientTableData();
    }
}
