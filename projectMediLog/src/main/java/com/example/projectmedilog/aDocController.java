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
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class aDocController implements Initializable {


    @FXML
    private TableColumn<adoctors, String> TC_age;


    @FXML
    private TableColumn<adoctors, String> TC_degree;

    @FXML
    private TableColumn<adoctors, String> TC_department;

    @FXML
    private TableColumn<adoctors, String> TC_UserName;

    @FXML
    private TableColumn<adoctors, String> TC_gender;
    @FXML
    private TableColumn<?, ?> TC_password;

    @FXML
    private TableColumn<adoctors, String> TC_name;

    @FXML
    private TableColumn<adoctors, String> TC_phone;
    @FXML
    private Button BTN_AddDoc;
    @FXML
    private TableView<adoctors> TB_DoctorTable;
    ObservableList<adoctors> DoctorList = FXCollections.observableArrayList();
    Connection conn;
    ResultSet rs;
    Integer index;

    @FXML
    void onCLickedBTN_AddDoc(ActionEvent event) throws IOException {

        FXMLLoader EditorPage = new FXMLLoader(getClass().getResource("aDocEditor.fxml"));
        Parent root = EditorPage.load();
        //   aDocEditorController aDocEditorController = EditorPage.getController();
        //   aAppointmentsEditorController.setAppointmentTableData();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setDoctorTableData() {
        try {
            conn = database.dbconnect();
            //get all appointments from database
            rs = conn.createStatement().executeQuery("select * from doctors");
            while (rs.next()) {
                DoctorList.add(new adoctors(rs.getString("UserName"), rs.getString("FullName"), rs.getString("Gender"), rs.getString("Age"), rs.getString("Phone"), rs.getString("Degree"), rs.getString("Department"),rs.getBlob("image")));
            }
                //add to table
                TC_name.setCellValueFactory(new PropertyValueFactory<>("FullName"));
                TC_UserName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
                TC_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                TC_age.setCellValueFactory(new PropertyValueFactory<>("age"));
                TC_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
                TC_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));
                TC_department.setCellValueFactory(new PropertyValueFactory<>("department"));


            TC_UserName.setStyle("-fx-alignment: CENTER;");
            TC_department.setStyle("-fx-alignment: CENTER;");
            TC_degree.setStyle("-fx-alignment: CENTER;");
            TC_gender.setStyle("-fx-alignment: CENTER;");
            TC_phone.setStyle("-fx-alignment: CENTER;");
            TC_age.setStyle("-fx-alignment: CENTER;");
            TC_name.setStyle("-fx-alignment: CENTER;");
            //refresh table

            //new use in pAppointmenttable
            TB_DoctorTable.setItems(DoctorList);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void onMouseEnteredBTN_AddDoc(MouseEvent event) {
        BTN_AddDoc.setCursor(Cursor.HAND);
        BTN_AddDoc.setBackground(Background.fill(Color.WHITE));
    }

    @FXML
    void getItem(MouseEvent event) throws IOException {
        index = TB_DoctorTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        String Name = TC_name.getCellData(index).toString();
        String UserName = TC_UserName.getCellData(index).toString();
        String Gender = TC_gender.getCellData(index).toString();
        String Age = TC_age.getCellData(index).toString();
        String Mobile = TC_phone.getCellData(index).toString();
        String Degree = TC_degree.getCellData(index).toString();
        String Department = TC_department.getCellData(index).toString();

        gotoDoctorDialog(Name, UserName, Gender, Age, Mobile, Degree, Department);


    }

    void gotoDoctorDialog(String Name, String UserName, String Gender, String Age, String Mobile, String Degree, String Department) throws IOException {
        //passing to dialog box
        Stage adialogStage = new Stage();
        adialogStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aDocDialog.fxml"));
        Parent root = loader.load();
        aDocDialogController controller = loader.getController();
        controller.showDialog(adialogStage, Name, UserName, Gender, Age, Mobile, Degree, Department);
        Scene scene = new Scene(root);
        adialogStage.setScene(scene);
        adialogStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set Table Data
        setDoctorTableData();
    }
}
