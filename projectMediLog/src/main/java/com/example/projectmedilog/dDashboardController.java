package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dDashboardController {

    @FXML
    private Label LB_App;

    @FXML
    private Label LB_Pat;

    @FXML
    private Label LB_Task;

    @FXML
    private Pane Pane_App;

    @FXML
    private Pane Pane_Pat;

    @FXML
    private Pane Pane_Task;

    @FXML
    private AnchorPane chartPane;
    Integer totalAppointments;
    Integer totalpatients;
    Integer totalTasks;

    @FXML
    void onCLickedPane_App(MouseEvent event) {

    }

    @FXML
    void onCLickedPane_Pat(MouseEvent event) {

    }

    @FXML
    void onClickedPane_Task(MouseEvent event) {

    }

    @FXML
    void onMouseEnteredPane_App(MouseEvent event) {
        Pane_App.setCursor(Cursor.HAND);
        Pane_App.setBackground(new Background(new BackgroundFill(Color.web("95BDFF"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    void onMouseEnteredPane_Pat(MouseEvent event) {
        Pane_Pat.setCursor(Cursor.HAND);
        Pane_Pat.setBackground(new Background(new BackgroundFill(Color.web("95BDFF"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    void onMouseEnteredPane_Task(MouseEvent event) {
        Pane_Task.setCursor(Cursor.HAND);
        Pane_Task.setBackground(new Background(new BackgroundFill(Color.web("95BDFF"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    void onMouseExitedPane_App(MouseEvent event) {
        Pane_App.setBackground(new Background(new BackgroundFill(Color.web("FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    void onMouseExitedPane_Pat(MouseEvent event) {
        Pane_Pat.setBackground(new Background(new BackgroundFill(Color.web("FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    void onMouseExitedPane_Task(MouseEvent event) {
        Pane_Task.setBackground(new Background(new BackgroundFill(Color.web("FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));
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

    void showdPat() throws SQLException, ClassNotFoundException {
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        int PatCount = 0;

        while (resultSet.next()) {
            PatCount++;
        }
        this.totalpatients = PatCount;
        LB_Pat.setText(String.valueOf(PatCount));
        connection.close();
    }

    void showdTask() throws SQLException, ClassNotFoundException {
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from pprescription");
        int TaskCount = 0;

        while (resultSet.next()) {
            TaskCount++;
        }
        this.totalTasks = TaskCount;
        LB_Task.setText(String.valueOf(TaskCount));
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
        barchart.getData().add(series1);
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Patients");
        series2.getData().add(new XYChart.Data<>("Patients", totalpatients));
        barchart.getData().add(series2);
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Tasks");
        series3.getData().add(new XYChart.Data<>("Tasks", totalTasks));
        barchart.getData().add(series3);

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
        showdPat();
        showdTask();
        showGraph();
    }
}
