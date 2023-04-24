package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
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
    private TextField TF_Test;
    @FXML
    private TextField TF_Email;

    @FXML
    private TextField TF_Name;
    @FXML
    private TextField TF_Createdby;

    @FXML
    private TextField TF_Date;

    @FXML
    private TextField TF_Disease;



    @FXML
    private TextField TF_Medicine;



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

        if (TF_Name.getText().isEmpty() || TF_Email.getText().isEmpty() || TF_Createdby.getText().isEmpty() || TF_Date.getText().isEmpty() || TF_Disease.getText().isEmpty() || TF_Test.getText().isEmpty() || TF_Medicine.getText().isEmpty()) {
            emptyFieldsCheck();
        } else {
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

    public void initialize() {
        TF_Name.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        TF_Name.setBackground(Background.fill(Color.TRANSPARENT));
                        TF_Name.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
                    } else {
                        TF_Name.setBackground(Background.fill(Color.TRANSPARENT));
                        TF_Name.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");

                    }
                });
                    TF_Email.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                    TF_Email.setBackground(Background.fill(Color.TRANSPARENT));
                    TF_Email.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
                    } else {
                    TF_Email.setBackground(Background.fill(Color.TRANSPARENT));
                    TF_Email.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");

                }
            });
            TF_Createdby.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.isEmpty()) {
                    TF_Createdby.setBackground(Background.fill(Color.TRANSPARENT));
                    TF_Createdby.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
                } else {
                    TF_Createdby.setBackground(Background.fill(Color.TRANSPARENT));
                    TF_Createdby.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");

                }
            });
        TF_Disease.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_Disease.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Disease.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_Disease.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Disease.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");

            }
        });
        TF_Date.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_Date.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Date.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_Date.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Date.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");

            }
        });
        TF_Test.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_Test.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Test.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_Test.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Test.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");

            }
        });
        TF_Medicine.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_Medicine.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Medicine.setStyle("-fx-border-color: #008000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
            } else {
                TF_Medicine.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Medicine.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: #808080;");

            }

        });
    }


    void emptyFieldsCheck() {
        if (TF_Name.getText().isEmpty()) {
            TF_Name.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Name.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_Name.setPromptText("Name is Empty*");
        } else {
            TF_Name.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Name.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (TF_Test.getText().isEmpty()) {
            TF_Test.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Test.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_Test.setPromptText("Email is Empty*");
        } else {
            TF_Test.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Test.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (TF_Createdby.getText().isEmpty()) {
            TF_Createdby.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Createdby.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_Createdby.setPromptText("Created by is Empty*");
        } else {
            TF_Createdby.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Createdby.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (TF_Date.getText().isEmpty()) {
            TF_Date.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Date.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_Date.setPromptText("Date is Empty*");
        } else {
            TF_Date.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Date.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (TF_Disease.getText().isEmpty()) {
            TF_Disease.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Disease.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_Disease.setPromptText("Disease is Empty*");
        } else {
            TF_Disease.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Disease.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }

        if (TF_Medicine.getText().isEmpty()) {
            TF_Medicine.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Medicine.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_Medicine.setPromptText("Email is Empty*");
        } else {
            TF_Medicine.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Medicine.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
        if (TF_Email.getText().isEmpty()) {
            TF_Email.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Email.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00; -fx-prompt-text-fill: red;");
            TF_Email.setPromptText("Email is Empty*");
        } else {
            TF_Email.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Email.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 0px 0px 2px 0px; -fx-border-radius: 00;");
        }
}
}



