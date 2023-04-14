package com.example.projectmedilog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class aAppointmentsController implements Initializable {

    @FXML
    private Button BTN_NewAppointment;
    @FXML
    private TableColumn<AppointmentTable, Integer> TC_id;
    @FXML
    private TableColumn<AppointmentTable,String> TC_date;

    @FXML
    private TableColumn<AppointmentTable, String> TC_doctor;

    @FXML
    private TableColumn<AppointmentTable, String> TC_email;

    @FXML
    private TableColumn<AppointmentTable, String> TC_gender;

    @FXML
    private TableColumn<AppointmentTable, String> TC_age;


    @FXML
    private TableColumn<AppointmentTable, String> TC_injury_or_condition;

    @FXML
    private TableColumn<AppointmentTable, String> TC_mobile;

    @FXML
    private TableColumn<AppointmentTable, String> TC_name;

    @FXML
    private TableColumn<AppointmentTable, String> TC_time;

    @FXML
    private TableView<AppointmentTable> aAppointmentTable;

    @FXML
    private AnchorPane anchorPane;
    ObservableList<AppointmentTable> aAppointmentList = FXCollections.observableArrayList();

    Integer index;
    Connection conn;
    ResultSet rs;

    @FXML
    void onCLickedBTN_NewAppointment(ActionEvent event)  throws IOException { // throws IOException

        FXMLLoader EditorPage = new FXMLLoader(getClass().getResource("aAppointmentEditor.fxml"));
        Parent root = EditorPage.load();
      //  aAppointmentsEditorController aAppointmentsEditorController = EditorPage.getController();
     //   aAppointmentsEditorController.setAppointmentTableData();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        //refresh table
        aAppointmentTable.refresh();

    }

    //get data from database

    public void setAppointmentTableData() {
        try {
            conn = database.dbconnect();
            //get all appointments from database
            rs = conn.createStatement().executeQuery("select * from appointment");
            while (rs.next()) {
                aAppointmentList.add(new AppointmentTable(rs.getInt("id"), rs.getString("Name"), rs.getString("Email"), rs.getString("Gender"), rs.getString("Age"), rs.getString("Date"), rs.getString("Time"), rs.getString("Phone"), rs.getString("Doctor"), rs.getString("Injury_or_Condition")));
            }
            //add to table
            TC_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            TC_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            TC_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            TC_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            TC_age.setCellValueFactory(new PropertyValueFactory<>("age"));
            TC_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            TC_time.setCellValueFactory(new PropertyValueFactory<>("time"));
            TC_mobile.setCellValueFactory(new PropertyValueFactory<>("phone"));
            TC_doctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
            TC_injury_or_condition.setCellValueFactory(new PropertyValueFactory<>("injuryOrCondition"));
//refresh table
            aAppointmentTable.refresh();


            //new use in pAppointmenttable
            aAppointmentTable.setItems(aAppointmentList);


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void onCLickedBTN_Refresh(ActionEvent event) {
        //refresh table
        aAppointmentTable.refresh();
        //clear table
        aAppointmentList.clear();
        // remove all data from AppointmentTable
        aAppointmentTable.setItems(null);
        setAppointmentTableData();

    }



    public void getitem(MouseEvent event)  throws IOException {

        index = aAppointmentTable.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }

        Integer id = TC_id.getCellData(index);
        String Name = TC_name.getCellData(index).toString();
        String Email = TC_email.getCellData(index).toString();
        String Gender = TC_gender.getCellData(index).toString();
        String Age = TC_age.getCellData(index).toString();
        String Date = TC_date.getCellData(index).toString();
        String Time = TC_time.getCellData(index).toString();
        String Phone = TC_mobile.getCellData(index).toString();
        String Doctor = TC_doctor.getCellData(index).toString();
        String InjuryOrCondition = TC_injury_or_condition.getCellData(index).toString();
        gotoAppointmentTable(id, Name, Email, Gender, Date, Time, Phone, InjuryOrCondition, Doctor, Age);

    }

    @FXML
    void onMouseEnteredBTN_NewAppointment(MouseEvent event)  throws IOException {



    }
    void gotoAppointmentTable(Integer id, String Name, String Email, String Gender, String Date, String Time,String Phone, String InjuryOrCondition, String Doctor,String Age) throws IOException {
        Stage aAppointmentEditorStage = new Stage();
        aAppointmentEditorStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aAppointmentEditor.fxml"));
        Parent root = loader.load();
        aAppointmentsEditorController controller = loader.getController();
        controller.showAppointment(aAppointmentEditorStage, id, Name, Email, Gender, Date, Time, Phone, InjuryOrCondition, Doctor, Age);
        Scene scene = new Scene(root);
        aAppointmentEditorStage.setScene(scene);
        aAppointmentEditorStage.show();
    }

    @FXML
    void onMouseExitedBTN_NewAppointment(MouseEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resource) {


        setAppointmentTableData();
//        aAppointmentTable.setItems(aAppointmentList);
    }


}
