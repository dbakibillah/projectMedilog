package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class chatController {

    @FXML
    private Button BTN_Search;

    @FXML
    private ImageView BTN_Send;

    @FXML
    private TextField TF_MessageField;

    @FXML
    private TextField TF_Search;

    @FXML
    private AnchorPane chatArea;

    @FXML
    private AnchorPane inboxArea;

    @FXML
    void onClickedBTN_Search(ActionEvent event) {
        if (TF_Search.getText().equals("")) {
            TF_Search.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Search.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        } else {
            TF_Search.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Search.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }
    }

    @FXML
    void onClickedBTN_Send(MouseEvent event) {
        if (TF_MessageField.getText().equals("")) {
            TF_MessageField.setBackground(Background.fill(Color.TRANSPARENT));
            TF_MessageField.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
            TF_MessageField.setPromptText("Message Field is Empty");
        } else {
            TF_MessageField.setBackground(Background.fill(Color.TRANSPARENT));
            TF_MessageField.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }
    }

}
