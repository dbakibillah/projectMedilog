package com.example.projectmedilog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class pPrescriptionController implements Initializable {

    @FXML
    private TableColumn<pTable, String> createdTablecolumn;

    @FXML
    private TableColumn<pTable, String> dateTablecolumn;

    @FXML
    private TableColumn<pTable, String> diseaseTablecolumn;

    @FXML
    private TableColumn<pTable, String> nameTablecolumn;
    @FXML
    private TableColumn<pTable, String> TC_UserName;

    @FXML
    private TableColumn<pTable, String> medicineTablecolumn;
    @FXML
    private TableView<pTable> pTable;



    @FXML
    private TableColumn<pTable, String> testTablecolumn;

    ObservableList<pTable> listI = FXCollections.observableArrayList();
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    Integer index;


    @FXML
    void getitem(MouseEvent event) throws IOException {
        index = pTable.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }

        //getting table data
        String Name = nameTablecolumn.getCellData(index).toString();
        String UserName = TC_UserName.getCellData(index).toString();
        String CreatedBy = createdTablecolumn.getCellData(index).toString();
        String Date = dateTablecolumn.getCellData(index).toString();
        String Disease = diseaseTablecolumn.getCellData(index).toString();
        String Test = testTablecolumn.getCellData(index).toString();
        String Medicine = medicineTablecolumn.getCellData(index).toString();
        gotoPrescriptionDialog(Name, UserName, CreatedBy, Date, Disease, Test, Medicine);
    }

    void gotoPrescriptionDialog(String Name, String UserName, String Createdby, String Date, String Disease, String Test, String Medicine) throws IOException {
        //passing to dialog box
        Stage pdialogStage = new Stage();
        pdialogStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pPrescriptionDialog.fxml"));
        Parent root = loader.load();
        pPrescriptionDialogController controller = loader.getController();
        controller.showDialog(pdialogStage, Name, UserName, Createdby, Date, Disease, Test, Medicine);
        Scene scene = new Scene(root);
        pdialogStage.setScene(scene);
        pdialogStage.show();
    }


    public  void setPrescriptionTableData(){
        try {
            //bringin data from database
            conn = database.dbconnect();
            rs = conn.createStatement().executeQuery("select * from pprescription Where UserName = '" + user.getUserName() + "'");

            while (rs.next()) {
                listI.add(new pTable(rs.getString("name"), rs.getString("UserName"), rs.getString("createdby"), rs.getString("date"), rs.getString("disease"), rs.getString("test"), rs.getNString("medicine")));
            }
            nameTablecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            TC_UserName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
            createdTablecolumn.setCellValueFactory(new PropertyValueFactory<>("createdby"));
            dateTablecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            diseaseTablecolumn.setCellValueFactory(new PropertyValueFactory<>("disease"));
            testTablecolumn.setCellValueFactory(new PropertyValueFactory<>("test"));
            medicineTablecolumn.setCellValueFactory(new PropertyValueFactory<>("medicine"));


            nameTablecolumn.setStyle("-fx-alignment: CENTER;");
            TC_UserName.setStyle("-fx-alignment: CENTER;");
            createdTablecolumn.setStyle("-fx-alignment: CENTER;");
            dateTablecolumn.setStyle("-fx-alignment: CENTER;");
            diseaseTablecolumn.setStyle("-fx-alignment: CENTER;");
            testTablecolumn.setStyle("-fx-alignment: CENTER;");
            medicineTablecolumn.setStyle("-fx-alignment: CENTER;");
            pTable.setItems(listI);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPrescriptionTableData();

    }



}
