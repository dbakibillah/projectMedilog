package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class aAppointmentsEditorController implements Initializable {
    @FXML
    private Button BTN_cancel;

    @FXML
    private Button BTN_delete;

    @FXML
    private Button BTN_save;

    @FXML
    private ChoiceBox<String> CB_doctor;

    @FXML
    private ChoiceBox<String> CB_time;

    @FXML
    private DatePicker DT_Date;

    @FXML
    private AnchorPane DialogPane;

    @FXML
    private TextField TF_UserName;

    @FXML
    private TextField TF_injury_or_condition;

    @FXML
    private TextField TF_name;

    @FXML
    private TextField TF_phone;
    Integer id;
    Connection conn;
    ResultSet rs;
    @FXML
    void onClickBTN_cancel(ActionEvent event) {
        //close the window
        Stage stage = (Stage) BTN_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onClickBTN_delete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String sql = "DELETE from appointment WHERE `id` = " + id;
        Connection connection = database.dbconnect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            gotoSuccessDialog("Appointment deleted successfully");
            //close the window
            Stage stage = (Stage) BTN_delete.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickBTN_save(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (TF_name.getText().isEmpty() || TF_UserName.getText().isEmpty() || TF_phone.getText().isEmpty() || TF_injury_or_condition.getText().isEmpty()) {
            if (TF_name.getText().isEmpty()) {
                TF_name.setBackground(Background.fill(Color.TRANSPARENT));
                TF_name.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
                TF_name.setPromptText("Field cannot be empty");
            }
            if (TF_UserName.getText().isEmpty()) {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
                TF_UserName.setPromptText("Field cannot be empty");
            }
            if (TF_phone.getText().isEmpty()) {
                TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
                TF_phone.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
                TF_phone.setPromptText("Field cannot be empty");
            }
            if (TF_injury_or_condition.getText().isEmpty()) {
                TF_injury_or_condition.setBackground(Background.fill(Color.TRANSPARENT));
                TF_injury_or_condition.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: red;");
                TF_injury_or_condition.setPromptText("Field cannot be empty");
            }
        } else {
            String Name = TF_name.getText();
            String UserName = TF_UserName.getText();
            LocalDate getDate = DT_Date.getValue();
            String Date = getDate.toString();
            String Time = CB_time.getValue();
            String Phone = TF_phone.getText();
            String Doctor = CB_doctor.getValue();
            String Injury_or_Condition = TF_injury_or_condition.getText();

            // Add to database
            Connection connection = database.dbconnect();
            Statement statement = connection.createStatement();

            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO appointment (Name, UserName, Date, Time, Phone, Doctor, Injury_or_Condition) VALUES (?, ?, ?, ?, ?, ?, ?)");) {
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, UserName);
                preparedStatement.setString(3, Date);
                preparedStatement.setString(4, Time);
                preparedStatement.setString(5, Phone);
                preparedStatement.setString(6, Doctor);
                preparedStatement.setString(7, Injury_or_Condition);
                preparedStatement.executeUpdate();
                gotoSuccessDialog("Appointment added successfully");

                //close the window
                Stage stage = (Stage) BTN_save.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    void showAppointment(Stage aAppointmentEditorStage, Integer id, String Name, String UserName, String Date, String Time, String Phone, String Doctor, String InjuryOrCondition) throws SQLException, ClassNotFoundException, IOException {
        this.id = id;
        TF_name.setText(Name);
        TF_UserName.setText(UserName);
        DT_Date.setValue(LocalDate.parse(Date));
        CB_time.setValue(Time);
        TF_phone.setText(Phone);
        CB_doctor.setValue(Doctor);
        TF_injury_or_condition.setText(InjuryOrCondition);

        FXMLLoader fxmlLoader = new FXMLLoader(DialogController.class.getResource("aAppointmentEditor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        aAppointmentEditorStage.setTitle("AppointmentEditor");
        aAppointmentEditorStage.setScene(scene);
        aAppointmentEditorStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CB_time.getItems().addAll("09:00 - 11:00", "11:00 - 13:00", "17:00 - 19:00", "19:00 - 21:00", "21:00 - 23:00");
        CB_time.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return (s == null) ? "Nothing selected" : s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }
        });
//CB_doctor value add from database

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmedilog","root","");
            // Execute the query and retrieve the results
            Statement stmt = conn.createStatement();
            rs = conn.createStatement().executeQuery("select * from doctors");
            List<String> values = new ArrayList<>();

            while (rs.next()) {
                values.add(rs.getString("UserName"));
            }
            // Populate the ChoiceBox with the results
            CB_doctor.getItems().addAll(values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        CB_doctor.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return (s == null) ? "Nothing selected" : s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }
        });


        TF_name.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_name.setBackground(Background.fill(Color.TRANSPARENT));
                TF_name.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_name.setBackground(Background.fill(Color.TRANSPARENT));
                TF_name.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #0080ff;");
            }
        });
        TF_UserName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #0080ff;");
            }
        });
        TF_phone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
                TF_phone.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
                TF_phone.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #0080ff;");
            }
        });
        TF_injury_or_condition.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_injury_or_condition.setBackground(Background.fill(Color.TRANSPARENT));
                TF_injury_or_condition.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_injury_or_condition.setBackground(Background.fill(Color.TRANSPARENT));
                TF_injury_or_condition.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #0080ff;");
            }
        });
    }
}
