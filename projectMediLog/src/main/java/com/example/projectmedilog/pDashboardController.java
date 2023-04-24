package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class pDashboardController implements Initializable {

    @FXML
    private Pane HomePane2;
    @FXML
    private Label LB_BP;

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
    private Pane barPane;
    @FXML
    private NumberAxis yAxis;
    Integer BP_systolic;
    Integer BP_diastolic;
    Integer Glucose;
    Integer Heart_rate;

    @FXML
    void onClickedBP(MouseEvent event) {
        showBPChart();
    }

    void showBPChart() {
        Integer Normal_systolic = 120;
        Integer Normal_diastolic = 80;
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();

        barchart = new BarChart<>(xAxis, yAxis);
        barchart.setTitle("Blood Pressure");
        barchart.setAnimated(true);

        XYChart.Series data = new XYChart.Series();
        data.setName("Systolic");
        data.getData().add(new XYChart.Data("Normal Systolic", Normal_systolic));
        data.getData().add(new XYChart.Data("Systolic", this.BP_systolic));
        barchart.getData().add(data);
        XYChart.Series data2 = new XYChart.Series();
        data2.setName("Diastolic");
        data2.getData().add(new XYChart.Data("Normal Diastolic", Normal_diastolic));
        data2.getData().add(new XYChart.Data("Diastolic", this.BP_diastolic));
        barchart.getData().add(data2);

//        //set first bar color
//        for(Node n:barchart.lookupAll(".default-color0.chart-bar")) {
//            n.setStyle("-fx-bar-fill: red;");
//        }
//        //second bar color
//        for(Node n:barchart.lookupAll(".default-color1.chart-bar")) {
//            n.setStyle("-fx-bar-fill: green;");
//        }

        barPane.getChildren().add(barchart);
    }

    @FXML
    void onMouseClicked_GL(MouseEvent event) {
        Integer Normal_Glucose = 100;
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        barchart = new BarChart<>(xAxis, yAxis);
        barchart.setTitle("Glucose");
        barchart.setAnimated(true);
        XYChart.Series data = new XYChart.Series();
        data.setName("Glucose");
        data.getData().add(new XYChart.Data("Normal Glucose", Normal_Glucose));
        data.getData().add(new XYChart.Data("Glucose", this.Glucose));
        barchart.getData().add(data);

        barPane.getChildren().add(barchart);
    }

    @FXML
    void onMouseClicked_HB(MouseEvent event) {
        Integer Normal_Heart_rate = 72;
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        barchart = new BarChart<>(xAxis, yAxis);
        barchart.setTitle("Heart Rate");
        barchart.setAnimated(true);
        XYChart.Series data = new XYChart.Series();
        data.setName("Heart Rate");
        data.getData().add(new XYChart.Data("Normal Heart Rate", Normal_Heart_rate));
        data.getData().add(new XYChart.Data("Heart Rate", this.Heart_rate));
        barchart.getData().add(data);

        barPane.getChildren().add(barchart);
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
        ResultSet resultSet = statement.executeQuery("select * from medical_records");

        while (resultSet.next()) {
            if (resultSet.getString("UserName").equals(user.getUserName())) {
                Integer BP_systolic = Integer.parseInt(resultSet.getString("BP_systolic"));
                Integer BP_diastolic = Integer.parseInt(resultSet.getString("BP_diastolic"));
                Integer Glucose = Integer.parseInt(resultSet.getString("Glucose"));
                Integer HeartBeat = Integer.parseInt(resultSet.getString("HeartBeat"));
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
