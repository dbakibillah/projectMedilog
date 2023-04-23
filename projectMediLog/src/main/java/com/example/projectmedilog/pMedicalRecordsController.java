package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class pMedicalRecordsController {

    @FXML
    private Button BTN_Search;

    @FXML
    private Label LB_Date;

    @FXML
    private Label LB_Reference;

    @FXML
    private Label LB_TestName;

    @FXML
    private Label LB_TestResult;

    @FXML
    private Label LB_UserName;

    @FXML
    private TableView<String> List_Date;

    @FXML
    private TextArea TA_Comment;

    @FXML
    private TextArea TA_Conclusion;

    @FXML
    private TextField TF_UserName;
    @FXML
    private TableColumn<MedicalRecordsTable, Integer> TC_Id;
    @FXML
    private TableColumn<MedicalRecordsTable, String> TC_Date;
    Integer index;


    @FXML
    void getItem(MouseEvent event) {
        index = List_Date.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }
        Integer Id = Integer.valueOf(TC_Id.getCellData(index));
        String Date = TC_Date.getCellData(index);

    }

    @FXML
    void onClickedBTN_Search(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (TF_UserName.getText().isEmpty()) {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_UserName.setPromptText("User Name is Empty*");
        } else {
            String UserName = TF_UserName.getText();

            //database connection
            Connection connection = database.dbconnect();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM `medical_records` WHERE `UserName` = '" + UserName + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                //import into listview
                Integer Id = resultSet.getInt("Id");
                String Date = resultSet.getString("Date");
                String Reference = resultSet.getString("Reference");
                String TestName = resultSet.getString("TestName");
                String TestResult = resultSet.getString("TestResult");
                String Comment = resultSet.getString("Comment");
                String Conclusion = resultSet.getString("Conclusion");

                List_Date.getItems().add(String.valueOf((new MedicalRecordsTable(Id, Date, Reference, TestName, TestResult, Comment, Conclusion))));
                TC_Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
                TC_Date.setCellValueFactory(new PropertyValueFactory<>("Date"));


            }
        }
    }

    @FXML
    void onMouseEnteredBTN_Search(MouseEvent event) {
        BTN_Search.setCursor(Cursor.HAND);
    }

    public void initialize() {
        TF_UserName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
    }
}
