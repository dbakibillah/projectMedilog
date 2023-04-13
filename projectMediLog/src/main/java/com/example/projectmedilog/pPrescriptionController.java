package com.example.projectmedilog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class pPrescriptionController implements Initializable {

    @FXML
    private TableColumn<pTable, String> createdTablecolumn;

    @FXML
    private TableColumn<pTable, String> dateTablecolumn;

    @FXML
    private TableColumn<pTable, String> diseaseTablecolumn;

    @FXML
    private TableColumn<pTable, String> nameTablecolumn;
    @FXML
    private TableColumn<?, ?> emailTablecolumn;

    @FXML
    private TableColumn<pTable, String> medicineTablecolumn;


    @FXML
    private TableView<pTable> pTable;

    //    @FXML
//    private TextField txtCreatedby;
//
//    @FXML
//    private TextField txtDisease;
    @FXML
    private TableColumn<pTable, String> testTablecolumn;


//    @FXML
//    private TextField txtName;
//
//    @FXML
//    private TextField txtdate;
//    @FXML
//    private TextField txtMedicine;
//    @FXML
//    private TextField txtTest;


    ObservableList<pTable> listI = FXCollections.observableArrayList();
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    Integer index;

//    public pPrescriptionController() {
//    }

    @FXML
    void getitem(MouseEvent event) throws IOException {
        index = pTable.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }

        //getting table data
        String Name = nameTablecolumn.getCellData(index).toString();
        String Email = emailTablecolumn.getCellData(index).toString();
        String CreatedBy = createdTablecolumn.getCellData(index).toString();
        String Date = dateTablecolumn.getCellData(index).toString();
        String Disease = diseaseTablecolumn.getCellData(index).toString();
        String Test = testTablecolumn.getCellData(index).toString();
        String Medicine = medicineTablecolumn.getCellData(index).toString();
        gotoPrescriptionDialog(Name,Email, CreatedBy, Date, Disease, Test, Medicine);
}

    void gotoPrescriptionDialog(String Name,String Email, String Createdby, String Date, String Disease, String Test, String Medicine) throws IOException {
        //passing to dialog box
        Stage pdialogStage = new Stage();
        pdialogStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pPrescriptionDialog.fxml"));
        Parent root = loader.load();
        pPrescriptionDialogController controller = loader.getController();
        controller.showDialog(pdialogStage, Name,Email, Createdby, Date, Disease, Test, Medicine);
        Scene scene = new Scene(root);
        pdialogStage.setScene(scene);
        pdialogStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            //bringin data from database
            conn = database.dbconnect();
            rs = conn.createStatement().executeQuery("select * from pprescription");

            while (rs.next()) {
                listI.add(new pTable(rs.getString("name"),rs.getString("email"), rs.getString("createdby"), rs.getString("date"), rs.getString("disease"), rs.getString("test"), rs.getNString("medicine")));
            }
            nameTablecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            emailTablecolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            createdTablecolumn.setCellValueFactory(new PropertyValueFactory<>("createdby"));
            dateTablecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            diseaseTablecolumn.setCellValueFactory(new PropertyValueFactory<>("disease"));
            testTablecolumn.setCellValueFactory(new PropertyValueFactory<>("test"));
            medicineTablecolumn.setCellValueFactory(new PropertyValueFactory<>("medicine"));
            pTable.setItems(listI);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
