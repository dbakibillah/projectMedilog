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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class aPrescriptionController implements Initializable {
    @FXML
    private Button BTN_Add;

    @FXML
    private ImageView BTN_Refresh;


    @FXML
    private TableView<users> Table_user;
    @FXML
    private TableColumn<users, String> emailTablecolumn;
    @FXML
    private TableColumn<users, String> createdTablecolumn;

    @FXML
    private TableColumn<users, String> dateTablecolumn;

    @FXML
    private TableColumn<users, String> diseaseTablecolumn;



    @FXML
    private TableColumn<users, String> medicineTablecolumn;

    @FXML
    private TableColumn<users, String> nameTablecolumn;

    @FXML
    private TableColumn<users, String> testTablecolumn;




    ObservableList<users> listI = FXCollections.observableArrayList();

    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    Integer index;

    public void getItem(MouseEvent mouseEvent) throws IOException {
        index = Table_user.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;

        }
        String Name = nameTablecolumn.getCellData(index).toString();
        String UserName = emailTablecolumn.getCellData(index).toString();
        String CreatedBy = createdTablecolumn.getCellData(index).toString();
        String Date = dateTablecolumn.getCellData(index).toString();
        String Disease = diseaseTablecolumn.getCellData(index).toString();
        String Test = testTablecolumn.getCellData(index).toString();
        String Medicine = medicineTablecolumn.getCellData(index).toString();
        goto_aPrescriptionDialog(Name, UserName, CreatedBy, Date, Disease, Test, Medicine);
   }



    @FXML
    void onClickedBTN_Add(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {


          goto_aPrescriptionDialog();

    }
    @FXML
    void onCLickedBTN_Refresh(MouseEvent event) {
        //refresh table
       Table_user.refresh();
        //clear table
        listI.clear();
        // remove all data from AppointmentTable
        Table_user.setItems(null);
        setPrescriptionTableData();
    }

    void goto_aPrescriptionDialog(String Name, String Username, String Createdby, String Date, String Disease, String Test, String Medicine) throws IOException {
        Stage adialogStage = new Stage();
        adialogStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aPrescriptionDialog.fxml"));
        Parent root = loader.load();
        aPrescriptionDialogController controller = loader.getController();
        controller.showDialog(adialogStage,Name, Username, Createdby, Date, Disease, Test, Medicine);
        Scene scene = new Scene(root);
        adialogStage.setScene(scene);
        adialogStage.show();
    }
    void goto_aPrescriptionDialog() throws IOException {
        Stage adialogStage = new Stage();
        adialogStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aPrescriptionDialog.fxml"));
        Parent root = loader.load();
        aPrescriptionDialogController controller = loader.getController();
        controller.showDialog(adialogStage);
        Scene scene = new Scene(root);
        adialogStage.setScene(scene);
        adialogStage.show();
    }


    public void setPrescriptionTableData() {
    try {
        conn = database.dbconnect();
        rs = conn.createStatement().executeQuery("select * from pprescription");

        while (rs.next()) {
            listI.add(new users(rs.getString("name"),rs.getString("UserName"),rs.getString("createdby"),rs.getString("date"),rs.getString("disease"),rs.getString("test"),rs.getString("medicine")));
        }
        nameTablecolumn.setCellValueFactory(new PropertyValueFactory<users, String>("name"));
        emailTablecolumn.setCellValueFactory(new PropertyValueFactory<users, String>("UserName"));
        createdTablecolumn.setCellValueFactory(new PropertyValueFactory<users, String>("createdby"));

        dateTablecolumn.setCellValueFactory(new PropertyValueFactory<users, String>("date"));
        diseaseTablecolumn.setCellValueFactory(new PropertyValueFactory<users, String>("disease"));

        testTablecolumn.setCellValueFactory(new PropertyValueFactory<users, String>("test"));
        medicineTablecolumn.setCellValueFactory(new PropertyValueFactory<users, String>("medicine"));
        nameTablecolumn.setStyle("-fx-alignment: CENTER;");
        emailTablecolumn.setStyle("-fx-alignment: CENTER;");
        createdTablecolumn.setStyle("-fx-alignment: CENTER;");
        dateTablecolumn.setStyle("-fx-alignment: CENTER;");
        diseaseTablecolumn.setStyle("-fx-alignment: CENTER;");
        testTablecolumn.setStyle("-fx-alignment: CENTER;");
        medicineTablecolumn.setStyle("-fx-alignment: CENTER;");


        Table_user.setItems(listI);




    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPrescriptionTableData();
    }
    @FXML
    void onMouseEnteredBTN_Add(MouseEvent event) {
        BTN_Add.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEnteredBTN_Refresh(MouseEvent event) {
        BTN_Refresh.setCursor(Cursor.HAND);
    }


}




