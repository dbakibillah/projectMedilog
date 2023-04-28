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
    private TableColumn<AppointmentTable, String> TC_date;

    @FXML
    private TableColumn<AppointmentTable, String> TC_doctor;
    @FXML
    private TableColumn<AppointmentTable, String> TC_injury_or_condition;

    @FXML
    private TableColumn<AppointmentTable, String> TC_mobile;

    @FXML
    private TableColumn<AppointmentTable, String> TC_name;
    @FXML
    private TableColumn<AppointmentTable, String> TC_UserName;

    @FXML
    private TableColumn<AppointmentTable, String> TC_time;

    @FXML
    private TableView<AppointmentTable> aAppointmentTable;

    @FXML
    static AnchorPane Appointment_AnchorPane;
    ObservableList<AppointmentTable> aAppointmentList = FXCollections.observableArrayList();
    Integer index;
    Connection conn;
    ResultSet rs;

    @FXML
    void onCLickedBTN_NewAppointment(ActionEvent event) throws IOException { // throws IOException
        FXMLLoader EditorPage = new FXMLLoader(getClass().getResource("aAppointmentEditor.fxml"));
        Parent root = EditorPage.load();

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
                aAppointmentList.add(new AppointmentTable(rs.getInt("id"), rs.getString("Name"), rs.getString("UserName"), rs.getString("Date"), rs.getString("Time"), rs.getString("Phone"), rs.getString("Doctor"), rs.getString("Injury_or_Condition")));
            }
            //add to table
            TC_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            TC_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            TC_UserName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
            TC_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
            TC_time.setCellValueFactory(new PropertyValueFactory<>("Time"));
            TC_mobile.setCellValueFactory(new PropertyValueFactory<>("phone"));
            TC_doctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
            TC_injury_or_condition.setCellValueFactory(new PropertyValueFactory<>("injuryOrCondition"));

            TC_date.setStyle("-fx-alignment: CENTER;");
            TC_id.setStyle("-fx-alignment: CENTER;");
            TC_name.setStyle("-fx-alignment: CENTER;");
            TC_UserName.setStyle("-fx-alignment: CENTER;");
            TC_time.setStyle("-fx-alignment: CENTER;");
            TC_mobile.setStyle("-fx-alignment: CENTER;");
            TC_doctor.setStyle("-fx-alignment: CENTER;");
            TC_injury_or_condition.setStyle("-fx-alignment: CENTER;");
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

    public void getitem(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {

        index = aAppointmentTable.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }

        Integer id = TC_id.getCellData(index);
        String Name = TC_name.getCellData(index).toString();
        String UserName = TC_UserName.getCellData(index).toString();
        String Date = TC_date.getCellData(index).toString();
        String Time = TC_time.getCellData(index).toString();
        String Phone = TC_mobile.getCellData(index).toString();
        String Doctor = TC_doctor.getCellData(index).toString();
        String InjuryOrCondition = TC_injury_or_condition.getCellData(index).toString();
        gotoAppointmentTable(id, Name, UserName, Date, Time, Phone, Doctor, InjuryOrCondition);

    }

    @FXML
    void onMouseEnteredBTN_NewAppointment(MouseEvent event) throws IOException {
        BTN_NewAppointment.setCursor(Cursor.HAND);
    }

    void gotoAppointmentTable(Integer id, String Name, String Username, String Date, String Time, String Phone, String Doctor, String InjuryOrCondition) throws IOException, SQLException, ClassNotFoundException {
        Stage aAppointmentEditorStage = new Stage();
        aAppointmentEditorStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aAppointmentEditor.fxml"));
        Parent root = loader.load();
        aAppointmentsEditorController controller = loader.getController();
        controller.showAppointment(aAppointmentEditorStage, id, Name, Username, Date, Time, Phone, Doctor, InjuryOrCondition);
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
