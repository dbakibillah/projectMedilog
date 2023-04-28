package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class aDashboardController {
    @FXML
    private Label LB_App;
    @FXML
    private AnchorPane chartPane;

    @FXML
    private Label LB_Doc;

    @FXML
    private Label LB_Pat;

    @FXML
    private Pane Pane_Appointments;

    @FXML
    private Pane Pane_Doctors;

    @FXML
    private Pane Pane_Ppatients;
    Integer totalAppointments;
    Integer totalDoctors;
    Integer totalPatients;

    @FXML
    void onMouseClicked_App(MouseEvent event) {

    }

    @FXML
    void onMouseClicked_Doctors(MouseEvent event) {

    }

    @FXML
    void onMouseClicked_Patients(MouseEvent event) {

    }

    @FXML
    void onMouseEntered_App(MouseEvent event) {
        Pane_Appointments.setCursor(Cursor.HAND);
        Pane_Appointments.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEntered_Doctors(MouseEvent event) {
        Pane_Doctors.setCursor(Cursor.HAND);
        Pane_Doctors.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEntered_Patients(MouseEvent event) {
        Pane_Ppatients.setCursor(Cursor.HAND);
        Pane_Ppatients.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseExited_App(MouseEvent event) {
        Pane_Appointments.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExited_Doctors(MouseEvent event) {
        Pane_Doctors.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExited_Patients(MouseEvent event) {
        Pane_Ppatients.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    void showdApp() throws SQLException, ClassNotFoundException {
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from appointment");
        int AppCount = 0;

        while (resultSet.next()) {
            AppCount++;
        }
        this.totalAppointments = AppCount;
        LB_App.setText(String.valueOf(AppCount));
        connection.close();
    }

    void showdDoc() throws SQLException, ClassNotFoundException {
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from doctors");
        int DocCount = 0;
        while (resultSet.next()) {
            DocCount++;
        }
        this.totalDoctors = DocCount;
        LB_Doc.setText(String.valueOf(DocCount));
        connection.close();
    }

    void showdPat() throws SQLException, ClassNotFoundException {
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        int PatCount = 0;
        while (resultSet.next()) {
            PatCount++;
        }
        this.totalPatients = PatCount;
        LB_Pat.setText(String.valueOf(PatCount));
        connection.close();
    }

    void showGraph() throws SQLException, ClassNotFoundException {
        // Creating axes and bar chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barchart = new BarChart<>(xAxis, yAxis);

        // Setting properties of bar chart
        barchart.setTitle("Statistics");
        barchart.setLegendVisible(false);
        barchart.setAnimated(false);
        barchart.setCategoryGap(0);
        barchart.setBarGap(0.1);

        // Setting labels and data for series
        xAxis.setLabel("Category");
        yAxis.setLabel("Number");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Appointments");
        series1.getData().add(new XYChart.Data<>("Appointments", totalAppointments));
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Doctors");
        series2.getData().add(new XYChart.Data<>("Doctors", totalDoctors));
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Patients");
        series3.getData().add(new XYChart.Data<>("Patients", totalPatients));
        barchart.getData().addAll(series1, series2, series3);

        // Adding bar chart to pane and setting pane properties
        chartPane.getChildren().add(barchart);
        AnchorPane.setTopAnchor(barchart, 0.0);
        AnchorPane.setRightAnchor(barchart, 0.0);
        AnchorPane.setBottomAnchor(barchart, 0.0);
        AnchorPane.setLeftAnchor(barchart, 0.0);

        // Customizing bar colors and fonts
        xAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 14));
        yAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 14));
        barchart.setStyle("-fx-background-color: #F5F5F5");
    }


    public void initialize() throws SQLException, ClassNotFoundException {
        showdApp();
        showdDoc();
        showdPat();
        showGraph();
    }

}
