package com.example.projectmedilog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import javafx.scene.input.MouseEvent;

public class pPrescriptionController implements Initializable {

    @FXML
    private TableColumn<pTable,String> createdTablecolumn;

    @FXML
    private TableColumn<pTable,String > dateTablecolumn;

    @FXML
    private TableColumn<pTable,String> diseaseTablecolumn;

    @FXML
    private TableColumn<pTable,String> nameTablecolumn;

    @FXML
    private TableView<pTable> pTable;

    @FXML
    private TextField txtCreatedby;

    @FXML
    private TextField txtDisease;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtdate;



    ObservableList <pTable> listI = FXCollections.observableArrayList();
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    Integer index;
    @FXML
    void getitem(MouseEvent event) {
        index = pTable.getSelectionModel().getSelectedIndex();

        if(index<= -1){
            return;
        }
        txtName.setText(nameTablecolumn.getCellData(index).toString());
        txtCreatedby.setText(createdTablecolumn.getCellData(index).toString());
        txtdate.setText(dateTablecolumn.getCellData(index).toString());
        txtDisease.setText(diseaseTablecolumn.getCellData(index).toString());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

        try {
            conn = pPreDb.getconnectionz();
            rs =  conn.createStatement().executeQuery("select * from pprescription");

            while (rs.next()){
                listI.add(new pTable(rs.getString("name"), rs.getString("createdby"), rs.getString("date"), rs.getString("disease") ));
            }
            nameTablecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            createdTablecolumn.setCellValueFactory(new PropertyValueFactory<>("createdby"));
            dateTablecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            diseaseTablecolumn.setCellValueFactory(new PropertyValueFactory<>("disease"));

            pTable.setItems(listI);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
