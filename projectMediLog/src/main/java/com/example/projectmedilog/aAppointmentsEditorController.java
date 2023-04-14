package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.projectmedilog.user.gender;

public class aAppointmentsEditorController implements Initializable {

    @FXML
    private Button BTN_cancel;

    @FXML
    private Button BTN_delete;

    @FXML
    private Button BTN_save;

    @FXML
    private ChoiceBox<String> CB_time;

    @FXML
    private RadioButton RB_female;

    @FXML
    private RadioButton RB_male;

    @FXML
    private RadioButton RB_others;
    @FXML
    private TextField TF_age;
    @FXML
    private TextField TF_date;
    @FXML
    private ChoiceBox<String> CB_doctor;

    @FXML
    private TextField TF_email;

    @FXML
    private TextField TF_injury_or_condition;

    @FXML
    private TextField TF_phone;
    @FXML
    private TextField TF_name;
    @FXML
    private AnchorPane anchorPane;

    Stage stage;

    Integer id;

    private String Gender;
    private int count = 0;

    @FXML
    void selectGender(ActionEvent event) {
        //Collecting gender information
        if (RB_male.isSelected())
            this.Gender = "Male";
        else if (RB_female.isSelected())
            this.Gender = "Female";
        else if (RB_others.isSelected())
            this.Gender = "Others";
    }

    @FXML
    void onClickBTN_save(ActionEvent event) throws SQLException, ClassNotFoundException, IOException, InterruptedException {


        if (TF_name.getText().isEmpty() || TF_email.getText().isEmpty() ||  this.Gender == null || TF_date.getText().isEmpty() || TF_phone.getText().isEmpty() || TF_injury_or_condition.getText().isEmpty() || TF_age.getText().isEmpty() || CB_doctor.getValue().isEmpty() || CB_time.getValue().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }
        String Name = TF_name.getText();
        String Email = TF_email.getText();
        String Gender = this.Gender;
        String Date = TF_date.getText();
        String Time = CB_time.getValue().toString();
        String Phone = TF_phone.getText();
        String Doctor = CB_doctor.getValue().toString();
        String InjuryOrCondition = TF_injury_or_condition.getText();
        String Age = TF_age.getText();



        // add to database

        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        try (
                PreparedStatement pst = connection.prepareStatement("insert into appointment(Name, Email, Gender, Date, Time, Phone, Injury_or_Condition, Doctor, Age) values(?, ?, ?, ?, ?, ?, ?, ?, ?)")
        ) {

            pst.setString(1, Name);
            pst.setString(2, Email);
            pst.setString(3, Gender);
            pst.setString(4, Date);
            pst.setString(5, Time);
            pst.setString(6, Phone);
            pst.setString(7, InjuryOrCondition);
            pst.setString(8, Doctor);
            pst.setString(9, Age);
            pst.executeUpdate();

            System.out.println("Appointment added");

            //clear fields
            TF_name.clear();
            TF_email.clear();
            TF_date.clear();
            TF_phone.clear();
            TF_injury_or_condition.clear();
            TF_age.clear();
            CB_doctor.setValue(null);
            CB_time.setValue(null);
            TF_age.clear();
            this.Gender = null;
            RB_male.setSelected(false);
            RB_female.setSelected(false);
            RB_others.setSelected(false);

            gotoSuccessDialog("Appointment added successfully");
            Stage stage = (Stage) BTN_save.getScene().getWindow();
            stage.close();


        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    void showAppointment(Stage aAppointmentEditorStage, Integer id, String Name, String Email, String Gender, String Date, String Time,String Phone, String InjuryOrCondition, String Doctor,String Age) throws IOException {
        this.stage = aAppointmentEditorStage;
        this.id = id;
        TF_name.setText(Name);
        TF_email.setText(Email);
  if (Gender.equals("Male"))RB_male.setSelected(true);else if (Gender.equals("Female"))RB_female.setSelected(true);else RB_others.setSelected(true);



        CB_time.setValue(Time);
        TF_age.setText(Age);
        TF_date.setText(Date);
        TF_phone.setText(Phone);
        TF_injury_or_condition.setText(InjuryOrCondition);
        CB_doctor.setValue(Doctor);



       FXMLLoader fxmlLoader = new FXMLLoader(DialogController.class.getResource("aAppointmentEditor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        aAppointmentEditorStage.setTitle("AppointmentEditor");
        aAppointmentEditorStage.setScene(scene);
        aAppointmentEditorStage.show();
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
    void onClickBTN_cancel(ActionEvent event) throws IOException {
        //close the window
        Stage stage = (Stage) BTN_cancel.getScene().getWindow();
        stage.close();
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

    }

}
