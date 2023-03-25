package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class pDashboardController implements Initializable {
    @FXML
    private Circle userProfilePic;
    @FXML
    Pane DashboardPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Image image = new Image("F:\\java\\intellij\\practice\\projectMediLog\\src\\main\\resources\\images\\mypic.jpg");
        //userProfilePic.setFill(new ImagePattern(image));
    }

    public void onClickAppointment_btn(ActionEvent event) throws IOException {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("Appointment.fxml"));
        Parent root = loader.load();
        AppointmentController second = loader.getController();
        Stage secondStage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
        secondStage.setScene(new Scene(root));*/




    }
}
