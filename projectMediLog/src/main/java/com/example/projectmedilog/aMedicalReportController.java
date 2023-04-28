package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class aMedicalReportController {

    @FXML
    private Button BTN_Save;

    @FXML
    private Button BTN_Search;

    @FXML
    private Button BTN_Update;

    @FXML
    private DatePicker DTpicker;

    @FXML
    private TextArea TA_Comment;

    @FXML
    private TextArea TA_Conclusion;

    @FXML
    private TextField TF_BP;

    @FXML
    private TextField TF_GL;

    @FXML
    private TextField TF_HB;

    @FXML
    private TextField TF_Reference;

    @FXML
    private TextField TF_TestName;

    @FXML
    private TextField TF_TestResult;

    @FXML
    private TextField TF_UserName;

    @FXML
    void onActionDTpicker(ActionEvent event) {
        LocalDate getdate = DTpicker.getValue();
        String Date = getdate.toString();
        System.out.println("Selected Date: " + Date);
    }

    @FXML
    void onCLickBTN_Search(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (TF_UserName.getText().isEmpty()) {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
            TF_UserName.setPromptText("User Name is Empty*");
        } else {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #00ff00 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: green;");
        }

        //read database
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user_m_records");

        while (resultSet.next()) {
            if (resultSet.getString("UserName").equals(TF_UserName.getText())) {
                TF_BP.setText(resultSet.getString("BP_systolic") + "/" + resultSet.getString("BP_diastolic"));
                TF_GL.setText(resultSet.getString("Glucose"));
                TF_HB.setText(resultSet.getString("HeartBeat"));
            }
        }
    }

    @FXML
    void onClickBTN_Save(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (TF_UserName.getText().isEmpty() || TF_TestName.getText().isEmpty() || TF_TestResult.getText().isEmpty() || TF_Reference.getText().isEmpty() || TA_Comment.getText().isEmpty() || TA_Conclusion.getText().isEmpty()) {
            if (TF_UserName.getText().isEmpty()) {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
                TF_UserName.setPromptText("User Name is Empty*");
            }
            if (TF_TestName.getText().isEmpty()) {
                TF_TestName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_TestName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
                TF_TestName.setPromptText("Test Name is Empty*");
            }
            if (TF_TestResult.getText().isEmpty()) {
                TF_TestResult.setBackground(Background.fill(Color.TRANSPARENT));
                TF_TestResult.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
                TF_TestResult.setPromptText("Test Result is Empty*");
            }
            if (TF_Reference.getText().isEmpty()) {
                TF_Reference.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Reference.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
                TF_Reference.setPromptText("Reference is Empty*");
            }
            if (TA_Comment.getText().isEmpty()) {
                TA_Comment.setBackground(Background.fill(Color.TRANSPARENT));
                TA_Comment.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 5; -fx-prompt-text-fill: red;");
                TA_Comment.setPromptText("Comment is Empty*");
            }
            if (TA_Conclusion.getText().isEmpty()) {
                TA_Conclusion.setBackground(Background.fill(Color.TRANSPARENT));
                TA_Conclusion.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 5; -fx-prompt-text-fill: red;");
                TA_Conclusion.setPromptText("Conclusion is Empty*");
            }
        } else {
            String UserName = TF_UserName.getText();
            String TestName = TF_TestName.getText();
            String TestResult = TF_TestResult.getText();
            String Reference = TF_Reference.getText();
            String Comment = TA_Comment.getText();
            String Conclusion = TA_Conclusion.getText();
            LocalDate getdate = DTpicker.getValue();
            String Date = getdate.toString();

            //insert database
            Connection connection = database.dbconnect();
            Statement statement = connection.createStatement();

            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into medical_records(UserName, TestName, TestResult, Reference, Comment, Conclusion, Date) values(?, ?, ?, ?, ?, ?, ?)")) {
                preparedStatement.setString(1, UserName);
                preparedStatement.setString(2, TestName);
                preparedStatement.setString(3, TestResult);
                preparedStatement.setString(4, Reference);
                preparedStatement.setString(5, Comment);
                preparedStatement.setString(6, Conclusion);
                preparedStatement.setString(7, Date);

                preparedStatement.executeUpdate();

                gotoSuccessDialog("medicalReport.fxml", "Result saved Successfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void onClickBTN_Update(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (TF_BP.getText().isEmpty() || TF_GL.getText().isEmpty() || TF_HB.getText().isEmpty()) {
            if (TF_BP.getText().isEmpty()) {
                TF_BP.setBackground(Background.fill(Color.TRANSPARENT));
                TF_BP.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
                TF_BP.setPromptText("Empty*");
            }
            if (TF_GL.getText().isEmpty()) {
                TF_GL.setBackground(Background.fill(Color.TRANSPARENT));
                TF_GL.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
                TF_GL.setPromptText("Empty*");
            }
            if (TF_HB.getText().isEmpty()) {
                TF_HB.setBackground(Background.fill(Color.TRANSPARENT));
                TF_HB.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
                TF_HB.setPromptText("Empty*");
            }
        } else {
            String UserName = TF_UserName.getText();
            String BloodPressure[] = TF_BP.getText().split("/");
            String BP_systolic = BloodPressure[0];
            String BP_diastolic = BloodPressure[1];
            String Glucose = TF_GL.getText();
            String HeartBeat = TF_HB.getText();

            //update database
            Connection connection = database.dbconnect();
            Statement statement = connection.createStatement();
            String sql = "insert into user_m_records(UserName, BP_systolic, BP_diastolic, Glucose, HeartBeat) values(?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into user_m_records(UserName, BP_systolic, BP_diastolic, Glucose, HeartBeat) values(?, ?, ?, ?, ?)")) {
                preparedStatement.setString(1, UserName);
                preparedStatement.setInt(2, Integer.parseInt(BP_systolic));
                preparedStatement.setInt(3, Integer.parseInt(BP_diastolic));
                preparedStatement.setInt(4, Integer.parseInt(Glucose));
                preparedStatement.setInt(5, Integer.parseInt(HeartBeat));

                preparedStatement.executeUpdate();

                gotoSuccessDialog("medicalReport.fxml", "Update Successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    void gotoSuccessDialog(String fxml, String message) throws IOException {

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
    void onMouseEnteredBTN_Save(MouseEvent event) {
        BTN_Save.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEnteredBTN_Update(MouseEvent event) {
        BTN_Update.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouserEnteredBTN_Search(MouseEvent event) {
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
        TF_BP.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_BP.setBackground(Background.fill(Color.TRANSPARENT));
                TF_BP.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_BP.setBackground(Background.fill(Color.TRANSPARENT));
                TF_BP.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_GL.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_GL.setBackground(Background.fill(Color.TRANSPARENT));
                TF_GL.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_GL.setBackground(Background.fill(Color.TRANSPARENT));
                TF_GL.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_HB.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_HB.setBackground(Background.fill(Color.TRANSPARENT));
                TF_HB.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_HB.setBackground(Background.fill(Color.TRANSPARENT));
                TF_HB.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_TestName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_TestName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_TestName.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_TestName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_TestName.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_TestResult.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_TestResult.setBackground(Background.fill(Color.TRANSPARENT));
                TF_TestResult.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_TestResult.setBackground(Background.fill(Color.TRANSPARENT));
                TF_TestResult.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_Reference.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_Reference.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Reference.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_Reference.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Reference.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TA_Comment.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TA_Comment.setBackground(Background.fill(Color.TRANSPARENT));
                TA_Comment.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 5; -fx-prompt-text-fill: #008000;");
            } else {
                TA_Comment.setBackground(Background.fill(Color.TRANSPARENT));
                TA_Comment.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 5; -fx-prompt-text-fill: #808080;");
            }
        });
        TA_Conclusion.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TA_Conclusion.setBackground(Background.fill(Color.TRANSPARENT));
                TA_Conclusion.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 5; -fx-prompt-text-fill: #008000;");
            } else {
                TA_Conclusion.setBackground(Background.fill(Color.TRANSPARENT));
                TA_Conclusion.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 5; -fx-prompt-text-fill: #808080;");
            }
        });
    }

}
