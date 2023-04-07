package com.example.projectmedilog;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class pDashboardController implements Initializable{

    @FXML
    private Pane HomePane2;

    @FXML
    private Pane Pane_BP;

    @FXML
    private Pane Pane_HB;

    @FXML
    private Pane Pnae_GL;

    @FXML
    public Label userLabel;

    @FXML
    void onClickedBP(MouseEvent event) {

    }

    @FXML
    void onMouseClicked_GL(MouseEvent event) {

    }

    @FXML
    void onMouseClicked_HB(MouseEvent event) {

    }

    @FXML
    void onMouseEntered_BP(MouseEvent event) {
        Pane_BP.setCursor(Cursor.HAND);
        Pane_BP.setBackground(Background.fill(Color.web("95BDFF")));
    }

    @FXML
    void onMouseEntered_GL(MouseEvent event) {

    }

    @FXML
    void onMouseEntered_HB(MouseEvent event) {

    }

    @FXML
    void onMouseExited_BP(MouseEvent event) {
        Pane_BP.setBackground(Background.fill(Color.web("#FFFFFF")));
    }

    @FXML
    void onMouseExited_GL(MouseEvent event) {

    }

    @FXML
    void onMouseExited_HB(MouseEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resource) {
        userLabel.setText(user.getFirstName());
    }

}
