package com.example.projectmedilog;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    private ListView<String> List_Date;

    //checking demo with arraylist
    ArrayList<String> dateList = new ArrayList<>();
    String dateFromList;

    //Local Variables
    Integer Id;
    String pDate;
    String Reference;
    String TestName;
    String TestResult;
    String Comment;
    String Conclusion;
    ResultSet resultSet;

//    @FXML
//    void getItem(MouseEvent event) {
//        index = List_Date.getSelectionModel().getSelectedIndex();
//
//        if (index <= -1) {
//            return;
//        }
////        Integer Id = Integer.valueOf(TC_Id.getCellData(index));
////        String Date = TC_Date.getCellData(index);
//
//    }

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
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                //import into listview
                this.Id = resultSet.getInt("Id");
                pDate = resultSet.getString("Date");
                Reference = resultSet.getString("Reference");
                TestName = resultSet.getString("TestName");
                TestResult = resultSet.getString("TestResult");
                Comment = resultSet.getString("Comment");
                Conclusion = resultSet.getString("Conclusion");

                dateList.add(this.pDate);
            }
            List_Date.getItems().addAll(dateList);
            List_Date.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                    dateFromList = List_Date.getSelectionModel().getSelectedItem();
                    LB_Date.setText(dateFromList);

                    try {
                        showdata(UserName,dateFromList);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            connection.close();
        }
    }

    void showdata(String UserName, String dateFromList) throws SQLException, ClassNotFoundException {
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM `medical_records` WHERE `UserName` = '" + UserName + "' AND `Date` = '" + dateFromList + "'";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            LB_UserName.setText(rs.getString("UserName"));
            LB_TestName.setText(rs.getString("TestName"));
            LB_TestResult.setText(rs.getString("TestResult"));
            LB_Reference.setText(rs.getString("Reference"));
            TA_Comment.setText(rs.getString("Comment"));
            TA_Conclusion.setText(rs.getString("Conclusion"));
        }
        connection.close();
    }

    @FXML
    void onMouseEnteredBTN_Search(MouseEvent event) {
        BTN_Search.setCursor(Cursor.HAND);
    }

    public void initialize() {
//        try {
//            //bringin data from database
//            conn = database.dbconnect();
//            rs = conn.createStatement().executeQuery("select * from medical_records");
//
//            while (rs.next()) {
//                listI.add(new MedicalRecordsTable(rs.getInt("Id"), rs.getString("Date"), rs.getString("")));
//            }
//            TC_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
//            TC_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
//
//
//
//            List_Date.setItems(listI);
//
//        } catch (SQLException | ClassNotFoundException e) {
//
//
//        }


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

