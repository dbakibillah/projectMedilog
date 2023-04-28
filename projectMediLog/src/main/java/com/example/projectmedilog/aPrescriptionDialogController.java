package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class aPrescriptionDialogController {

    @FXML
    private Button BTN_Add;

    @FXML
    private Button BTN_Clear;
    @FXML

    private TextField TF_Test;


    @FXML
    TextField TF_UserName;


    @FXML
    private TextField TF_Name;
    @FXML
    private TextField TF_Createdby;

    @FXML
    private DatePicker TF_Date;

    @FXML
    private TextField TF_Disease;



    @FXML
    private TextField TF_Medicine;



    @FXML
    private Button BTN_Delete;
    Stage stage;
    void showDialog(Stage dialogStage, String Name, String UserName, String Createdby, String Date, String Disease, String Test, String Medicine) throws IOException {
        this.stage = dialogStage;
        TF_Name.setText(Name);
        TF_UserName.setText(UserName);
        TF_Createdby.setText(Createdby);
        TF_Date.setValue(LocalDate.parse(Date));
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

        if (TF_Name.getText().isEmpty() || TF_UserName.getText().isEmpty() || TF_Createdby.getText().isEmpty() ||  TF_Disease.getText().isEmpty() || TF_Test.getText().isEmpty() || TF_Medicine.getText().isEmpty()) {
            emptyFieldsCheck();
        } else {
        // Get value from text fields
        String Name = TF_Name.getText();
        String UserName = TF_UserName.getText();
        String CreatedBy = TF_Createdby.getText();
        LocalDate getDate = TF_Date.getValue();
        String Date = getDate.toString();
        String Disease = TF_Disease.getText();
        String Test = TF_Test.getText();
        String Medicine = TF_Medicine.getText();

        Connection connection = database.dbconnect();


        PreparedStatement pst = connection.prepareStatement("insert into pprescription(Name, UserName,CreatedBy,Date,Disease,Test,Medicine)values(?, ?, ?, ?, ?, ?, ?)");
        {
            pst.setString(1,Name);
            pst.setString(2, UserName);
            pst.setString(3,CreatedBy);
            pst.setString(4,Date);
            pst.setString(5,Disease);
            pst.setString(6,Test);
            pst.setString(7,Medicine);
            pst.executeUpdate();
            this.stage.close();


        }

    }
    }


    @FXML
    void onClickBTN_Clear(ActionEvent event) {

        TF_Name.setText("");
        TF_UserName.setText("");
        TF_Medicine.setText("");
        TF_Date.getEditor().setText("");
        TF_Createdby.setText("");
        TF_Test.setText("");
        TF_Disease.setText("");
    }

    @FXML
    void onClickBTN_Delete(ActionEvent event) throws SQLException, ClassNotFoundException {
    String sql = "DELETE from pprescription WHERE `UserName` = '" + TF_UserName.getText() + "'";
        Connection connection = database.dbconnect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.stage.close();

    }

    public void showDialog(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(DialogController.class.getResource("aPrescriptionDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Prescription");
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        TF_Name.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        TF_Name.setBackground(Background.fill(Color.TRANSPARENT));
                        TF_Name.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
                    } else {
                        TF_Name.setBackground(Background.fill(Color.TRANSPARENT));
                        TF_Name.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");

                    }
                });
                    TF_UserName.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                    TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                    TF_UserName.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
                    } else {
                    TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                    TF_UserName.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");

                }
            });
            TF_Createdby.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.isEmpty()) {
                    TF_Createdby.setBackground(Background.fill(Color.TRANSPARENT));
                    TF_Createdby.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
                } else {
                    TF_Createdby.setBackground(Background.fill(Color.TRANSPARENT));
                    TF_Createdby.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");

                }
            });
        TF_Disease.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_Disease.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Disease.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
            } else {
                TF_Disease.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Disease.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");

            }
        });


        TF_Test.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_Test.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Test.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
            } else {
                TF_Test.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Test.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");

            }
        });
        TF_Medicine.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_Medicine.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Medicine.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
            } else {
                TF_Medicine.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Medicine.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");

            }

        });
    }


    void emptyFieldsCheck() {
        if (TF_Name.getText().isEmpty()) {
            TF_Name.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Name.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_Name.setPromptText("Name is Empty*");
        } else {
            TF_Name.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Name.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }
        if (TF_Test.getText().isEmpty()) {
            TF_Test.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Test.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_Test.setPromptText("Email is Empty*");
        } else {
            TF_Test.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Test.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }
        if (TF_Createdby.getText().isEmpty()) {
            TF_Createdby.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Createdby.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_Createdby.setPromptText("Created by is Empty*");
        } else {
            TF_Createdby.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Createdby.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }

        if (TF_Disease.getText().isEmpty()) {
            TF_Disease.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Disease.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_Disease.setPromptText("Disease is Empty*");
        } else {
            TF_Disease.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Disease.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }

        if (TF_Medicine.getText().isEmpty()) {
            TF_Medicine.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Medicine.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_Medicine.setPromptText("Email is Empty*");
        } else {
            TF_Medicine.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Medicine.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }
        if (TF_UserName.getText().isEmpty()) {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_UserName.setPromptText("UserName is Empty*");
        } else {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }
}
}



