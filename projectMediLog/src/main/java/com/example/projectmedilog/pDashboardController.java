package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class pDashboardController implements Initializable {

    @FXML
    private Pane HomePane2;
    @FXML
    private Label LB_BP;

    @FXML
    AnchorPane barPane;

    @FXML
    private Label LB_GL;

    @FXML
    private Label LB_HB;
    @FXML
    private Pane Pane_BP;

    @FXML
    private Pane Pane_HB;

    @FXML
    private Pane Pnae_GL;

    @FXML
    public Label userLabel;
    @FXML
    private BarChart<?, ?> barchart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    Integer BP_systolic;
    Integer BP_diastolic;
    Integer Glucose;
    Integer Heart_rate;
    ArrayList<Integer> BP_systolicList = new ArrayList<>();
    ArrayList<Integer> BP_diastolicList = new ArrayList<>();
    ArrayList<Integer> GlucoseList = new ArrayList<>();
    ArrayList<Integer> HeartBeatList = new ArrayList<>();

    @FXML
    void onClickedBP(MouseEvent event) {
        showBPChart();
    }

    void showBPChart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
//        xAxis.setLabel("X Axis");
        yAxis.setLabel("Pressure");
        xAxis.setTickUnit(1);

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Blood Pressure");

        // Add some styling to the chart
        lineChart.setStyle("-fx-background-color: white; -fx-border-color: white; -fx-border-width: 1px;");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Your Systolic Pressure");

        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        series3.setName("Normal Systolic Pressure");

        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Your Diastolic Pressure");

        XYChart.Series<Number, Number> series4 = new XYChart.Series<>();
        series4.setName("Normal Diastolic Pressure");

        for (int i = 0; i < BP_systolicList.size(); i++) {
            series.getData().add(new XYChart.Data<>(i + 1, BP_systolicList.get(i)));
            series3.getData().add(new XYChart.Data<>(i + 1, 120));
        }
        for (int i = 0; i < BP_diastolicList.size(); i++) {
            series2.getData().add(new XYChart.Data<>(i + 1, BP_diastolicList.get(i)));
            series4.getData().add(new XYChart.Data<>(i + 1, 80));
        }
        // Add the series to the chart
        lineChart.getData().add(series);
        lineChart.getData().add(series2);
        lineChart.getData().add(series3);
        lineChart.getData().add(series4);

        // Set the line chart to fill the AnchorPane
        AnchorPane.setTopAnchor(lineChart, 0.0);
        AnchorPane.setBottomAnchor(lineChart, 0.0);
        AnchorPane.setLeftAnchor(lineChart, 0.0);
        AnchorPane.setRightAnchor(lineChart, 0.0);

        //Customize the colors and width of the lines:
        series.getNode().setStyle("-fx-stroke: #0080FF; -fx-stroke-width: 3px;");
        series2.getNode().setStyle("-fx-stroke: red; -fx-stroke-width: 3px;");
        series3.getNode().setStyle("-fx-stroke: green; -fx-stroke-width: 2px; -fx-stroke-dash-array: 5 5;");
        series4.getNode().setStyle("-fx-stroke: green; -fx-stroke-width: 2px; -fx-stroke-dash-array: 5 5;");

        //Customize the label font and size:
        xAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 14));
        yAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 14));

        barPane.getChildren().clear();
        barPane.getChildren().add(lineChart);
    }


    @FXML
    void onMouseClicked_GL(MouseEvent event) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
//        xAxis.setLabel("X Axis");
        yAxis.setLabel("Glucose Level");
        xAxis.setTickUnit(1);

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Glucose");

        // Add some styling to the chart
        lineChart.setStyle("-fx-background-color: white; -fx-border-color: white; -fx-border-width: 1px;");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Your Glucose Level");

        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Normal Glucose Level");

        for (int i = 0; i < GlucoseList.size(); i++) {
            series.getData().add(new XYChart.Data<>(i + 1, GlucoseList.get(i)));
            series2.getData().add(new XYChart.Data<>(i + 1, 100));
        }
        //add series
        lineChart.getData().add(series);
        lineChart.getData().add(series2);

        // Set the line chart to fill the AnchorPane
        AnchorPane.setTopAnchor(lineChart, 0.0);
        AnchorPane.setBottomAnchor(lineChart, 0.0);
        AnchorPane.setLeftAnchor(lineChart, 0.0);
        AnchorPane.setRightAnchor(lineChart, 0.0);

        //Customize the colors and width of the lines:
        series.getNode().setStyle("-fx-stroke: #0080FF; -fx-stroke-width: 3px;");
        series2.getNode().setStyle("-fx-stroke: green; -fx-stroke-width: 2px; -fx-stroke-dash-array: 5 5;");

        //Customize the label font and size:
        xAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 14));
        yAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 14));

        // clear barPane before adding new chart
        barPane.getChildren().clear();
        barPane.getChildren().add(lineChart);
    }

    @FXML
    void onMouseClicked_HB(MouseEvent event) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
//        xAxis.setLabel("X Axis");
        yAxis.setLabel("Heart Beat");
        xAxis.setTickUnit(1);

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Heart Beat");

        // Add some styling to the chart
        lineChart.setStyle("-fx-background-color: white; -fx-border-color: white; -fx-border-width: 1px;");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Your Heart Beat");

        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Normal Heart Beat");

        for (int i = 0; i < GlucoseList.size(); i++) {
            series.getData().add(new XYChart.Data<>(i + 1, HeartBeatList.get(i)));
            series2.getData().add(new XYChart.Data<>(i + 1, 72));
        }
        //add series
        lineChart.getData().add(series);
        lineChart.getData().add(series2);

        // Set the line chart to fill the AnchorPane
        AnchorPane.setTopAnchor(lineChart, 0.0);
        AnchorPane.setBottomAnchor(lineChart, 0.0);
        AnchorPane.setLeftAnchor(lineChart, 0.0);
        AnchorPane.setRightAnchor(lineChart, 0.0);

        //Customize the colors and width of the lines:
        series.getNode().setStyle("-fx-stroke: #0080FF; -fx-stroke-width: 3px;");
        series2.getNode().setStyle("-fx-stroke: green; -fx-stroke-width: 2px;-fx-stroke-dash-array: 5 5;");

        //Customize the label font and size:
        xAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 14));
        yAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 14));

        // clear barPane before adding new chart
        barPane.getChildren().clear();
        barPane.getChildren().add(lineChart);
    }


    @FXML
    void onMouseEntered_BP(MouseEvent event) {
        Pane_BP.setCursor(Cursor.HAND);
        Pane_BP.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEntered_GL(MouseEvent event) {
        Pnae_GL.setCursor(Cursor.HAND);
        Pnae_GL.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEntered_HB(MouseEvent event) {
        Pane_HB.setCursor(Cursor.HAND);
        Pane_HB.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseExited_BP(MouseEvent event) {
        Pane_BP.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExited_GL(MouseEvent event) {
        Pnae_GL.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExited_HB(MouseEvent event) {
        Pane_HB.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    void showChart(Integer BP_systolic, Integer BP_diastolic, Integer Glucose, Integer HeartBeat) {
        this.BP_systolic = BP_systolic;
        this.BP_diastolic = BP_diastolic;
        this.Glucose = Glucose;
        this.Heart_rate = HeartBeat;
    }


    void getBasicInfo() throws SQLException, ClassNotFoundException {
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user_m_records");

        while (resultSet.next()) {
            if (resultSet.getString("UserName").equals(user.getUserName())) {
                Integer BP_systolic = Integer.parseInt(resultSet.getString("BP_systolic"));
                BP_systolicList.add(BP_systolic);
                Integer BP_diastolic = Integer.parseInt(resultSet.getString("BP_diastolic"));
                BP_diastolicList.add(BP_diastolic);
                Integer Glucose = Integer.parseInt(resultSet.getString("Glucose"));
                GlucoseList.add(Glucose);
                Integer HeartBeat = Integer.parseInt(resultSet.getString("HeartBeat"));
                HeartBeatList.add(HeartBeat);
                showChart(BP_systolic, BP_diastolic, Glucose, HeartBeat);

                LB_BP.setText(resultSet.getString("BP_systolic") + "/" + resultSet.getString("BP_diastolic"));
                LB_GL.setText(resultSet.getString("Glucose"));
                LB_HB.setText(resultSet.getString("HeartBeat"));
            } else {
                LB_BP.setText("Not Set yet");
                LB_GL.setText("Not Set yet");
                LB_HB.setText("Not Set yet");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        userLabel.setText(user.getFullName());
        try {
            getBasicInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        showBPChart();
    }

}
