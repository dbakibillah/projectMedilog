package com.example.projectmedilog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class pAppointmentController implements Initializable {

    @FXML
    private AnchorPane AppointmentPane;

    @FXML
    private Button BTN_cancel;
    @FXML
    private Button BTN_Search;

    @FXML
    private Button BTN_confirm;

    @FXML
    private ChoiceBox<String> CB_doctor;

    @FXML
    private ChoiceBox<String> CB_time;

    @FXML
    private TableView<AppointmentTable> pAppointmentTable;
    @FXML
    private TableColumn<AppointmentTable, String> TC_date;

    @FXML
    private TableColumn<AppointmentTable, String> TC_doctor;

    @FXML
    private TableColumn<AppointmentTable, String> TC_injury_or_condition;

    @FXML
    private TableColumn<AppointmentTable, String> TC_mobile;

    @FXML
    private TextField TF_Name;

    @FXML
    private TextField TF_UserName;
    @FXML
    private TableColumn<AppointmentTable, String> TC_time;
    @FXML
    private TableColumn<AppointmentTable, String> TC_UserName;
    @FXML
    private TableColumn<AppointmentTable, String> TC_Name;

    @FXML
    private DatePicker DT_date;


    @FXML
    private TextField TF_injury_or_condition;

    @FXML
    private TextField TF_phone;


    ObservableList<AppointmentTable> pAppointmentList = FXCollections.observableArrayList();
    private int count = 0;

    Connection conn;
    ResultSet rs;

    @FXML
    void onMouseENteredBTN_Search(MouseEvent event) {
        BTN_Search.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEnteredBTN_cancel(MouseEvent event) {
        BTN_cancel.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEnteredBTN_confirm(MouseEvent event) {
        BTN_confirm.setCursor(Cursor.HAND);
    }

    @FXML
    void onClickBTN_Search(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (TF_UserName.getText().isEmpty()) {
            TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
            TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #ff0000;");
        } else {
            Connection connection = database.dbconnect();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM signup WHERE UserName = '" + TF_UserName.getText() + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if (resultSet.getString("UserName").equals(TF_UserName.getText())) {
                    TF_Name.setText(resultSet.getString("FullName"));
                    TF_phone.setText(resultSet.getString("Phone"));
                }
            }
        }
    }

    @FXML
    void onClickBTN_cancel(ActionEvent event) {
        // clear all the fields
        TF_Name.clear();
        TF_UserName.clear();
        TF_phone.clear();
        TF_injury_or_condition.clear();
        DT_date.setValue(null);
        CB_doctor.setValue(null);
        CB_time.setValue(null);
    }

    @FXML
    void onClickBTN_confirm(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        // Error handling
        if (TF_Name.getText().isEmpty() || TF_UserName.getText().isEmpty() || TF_phone.getText().isEmpty() || TF_injury_or_condition.getText().isEmpty() || CB_doctor.getValue().isEmpty() || CB_time.getValue().isEmpty()) {

            if (TF_Name.getText().isEmpty()) {
                TF_Name.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Name.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #ff0000;");
            }
            if (TF_UserName.getText().isEmpty()) {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #ff0000;");
            }
            if (TF_phone.getText().isEmpty()) {
                TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
                TF_phone.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #ff0000;");
            }
            if (TF_injury_or_condition.getText().isEmpty()) {
                TF_injury_or_condition.setBackground(Background.fill(Color.TRANSPARENT));
                TF_injury_or_condition.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #ff0000;");
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();

        } else {

            String Name = TF_Name.getText();
            String UserName = TF_UserName.getText();
            String Time = CB_time.getValue().toString();
            String Phone = TF_phone.getText();
            String Doctor = CB_doctor.getValue().toString();
            String InjuryOrCondition = TF_injury_or_condition.getText();
            LocalDate date = DT_date.getValue();
            String Date = date.toString();

            //add to database
            Connection connection = database.dbconnect();
            Statement statement = connection.createStatement();

            try (
                    PreparedStatement pst = connection.prepareStatement("insert into appointment(Name, UserName, Date, Time, Phone, Injury_or_Condition, Doctor) values(?, ?, ?, ?, ?, ?, ?)")
            ) {
                pst.setString(1, Name);
                pst.setString(2, user.getEmail());
                pst.setString(3, Date);
                pst.setString(4, Time);
                pst.setString(5, Phone);
                pst.setString(6, InjuryOrCondition);
                pst.setString(7, Doctor);

                pst.executeUpdate();

//                System.out.println("Appointment added");

                //clear fields
                TF_Name.clear();
                TF_UserName.clear();
                TF_phone.clear();
                TF_injury_or_condition.clear();
                DT_date.setValue(null);
                CB_doctor.setValue(null);
                CB_time.setValue(null);

                gotoSuccessDialog("Appointment added successfully");
                //clear table
                pAppointmentList.clear();
                //refresh table
                setAppointmentTableData();


            } catch (SQLException e) {

                System.out.println(e);
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

    public void setAppointmentTableData() {
        try {
            conn = database.dbconnect();
            //get all appointments from database
            rs = conn.createStatement().executeQuery("select * from appointment where UserName = '" + user.getUserName() + "'");
            while (rs.next()) {
                pAppointmentList.add(new AppointmentTable(rs.getInt("id"), rs.getString("Name"), rs.getString("UserName"), rs.getString("Date"), rs.getString("Time"), rs.getString("Phone"), rs.getString("Doctor"), rs.getString("Injury_or_Condition")));

                //add to table
                TC_UserName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
                TC_Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
                TC_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
                TC_time.setCellValueFactory(new PropertyValueFactory<>("Time"));
                TC_mobile.setCellValueFactory(new PropertyValueFactory<>("Phone"));
                TC_doctor.setCellValueFactory(new PropertyValueFactory<>("Doctor"));
                TC_injury_or_condition.setCellValueFactory(new PropertyValueFactory<>("injuryOrCondition"));

                pAppointmentTable.setItems(pAppointmentList);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set Table Data
        setAppointmentTableData();

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

        CB_doctor.getItems().addAll("Dr. A", "Dr. B", "Dr. C", "Dr. D", "Dr. E");
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


        TF_UserName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_Name.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_Name.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Name.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_Name.setBackground(Background.fill(Color.TRANSPARENT));
                TF_Name.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_phone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
                TF_phone.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_phone.setBackground(Background.fill(Color.TRANSPARENT));
                TF_phone.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
        TF_injury_or_condition.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_injury_or_condition.setBackground(Background.fill(Color.TRANSPARENT));
                TF_injury_or_condition.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #008000;");
            } else {
                TF_injury_or_condition.setBackground(Background.fill(Color.TRANSPARENT));
                TF_injury_or_condition.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");
            }
        });
    }
}
