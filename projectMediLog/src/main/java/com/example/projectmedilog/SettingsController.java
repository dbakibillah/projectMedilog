package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.image.ImageView;

import java.io.*;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SettingsController implements  Initializable {

    @FXML
    private Button BTN_ChooseFile;

    @FXML
    private Button BTN_Save;

    @FXML
    private Button BTN_SaveChange;

    @FXML
    private Button BTN_Upload;

    @FXML
    private ChoiceBox<String> CB_bloodgrp;


    @FXML
    public Circle ImageCIrcle;

    @FXML
    private TextField TF_address;

    @FXML
    private TextField TF_age;

    @FXML
    PasswordField TF_currentpass;

    @FXML
    private TextField TF_email;

    @FXML
    private TextField TF_firstname;

    @FXML
    private TextField TF_lastname;

    @FXML
    private TextField TF_mobile;

    @FXML
    private PasswordField TF_newpass;
    @FXML
    private ImageView imageView;
    ImageUpload imageUpload = new ImageUpload();
    @FXML
    void onCLickBTN_ChooseFile(ActionEvent event) throws SQLException {

        imageUpload.selectImage();

        ImageCIrcle.setFill(new ImagePattern(imageUpload.getImage()));
        //imageView.setImage(imageUpload.getImage());
        //imageUpload.displayImage();
    }

    @FXML
    void onCLickBTN_Upload(ActionEvent event) throws FileNotFoundException {

        //update image in database
        FileInputStream fileInputStream = new FileInputStream(imageUpload.getSelectedFile());
        try (
                Connection connection = database.dbconnect();
                PreparedStatement pst = connection.prepareStatement("UPDATE signup SET Image = ? WHERE Email = ?")
        ) {
            pst.setBinaryStream(1, fileInputStream, fileInputStream.available());
            pst.setString(2, TF_email.getText());

            pst.execute();

            System.out.println("Image Updated");

            // get image from database
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM signup WHERE Email = '" + TF_email.getText() + "'");
            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("Image");
                user.setImage(blob);
                InputStream inputStream = user.getImage().getBinaryStream();
                Image image = new Image(new ByteArrayInputStream(inputStream.readAllBytes()));

                // goto pHome screen
                Stage stage = (Stage) BTN_Upload.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("pHome.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
                gotoSuccessDialog("Image Updated");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickBTN_Save(ActionEvent event)throws SQLException, ClassNotFoundException, IOException, InterruptedException {


        if (TF_currentpass.getText().isEmpty() || TF_newpass.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }










        String Email = TF_email.getText();
        String CurrentPass = TF_currentpass.getText();
        String NewPass = TF_newpass.getText();

        //check if current password is correct
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM signup WHERE Email = '" + Email + "' AND Pass = '" + CurrentPass + "'");
        if (resultSet.next()) {
            //update password
            try (
                    PreparedStatement pst = connection.prepareStatement("UPDATE signup SET Pass = ? WHERE Email = ?")
            ) {
                pst.setString(1, NewPass);
                pst.setString(2, Email);

                pst.execute();

                //clear password fields
                TF_currentpass.clear();
                TF_newpass.clear();
                System.out.println("Password Updated");
                gotoSuccessDialog("Password Updated");
            }
        }
    }

    @FXML
    void onClickBTN_SaveChange(ActionEvent event) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        // if any field is empty
        if (TF_firstname.getText().isEmpty() || TF_lastname.getText().isEmpty() || TF_age.getText().isEmpty() || TF_mobile.getText().isEmpty() || TF_address.getText().isEmpty() || CB_bloodgrp.getValue().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
           alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }
        String FirstName = TF_firstname.getText();
        String LastName = TF_lastname.getText();
        String Email = TF_email.getText();
        String Age = TF_age.getText();
        String Phone = TF_mobile.getText();

        String Address = TF_address.getText();
        String BloodGroup = CB_bloodgrp.getValue().toString();

        //update data using database
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();


        try (
                PreparedStatement pst = connection.prepareStatement("UPDATE signup SET FirstName = ?, LastName = ?, Age = ?, Phone = ?, Address = ?, Blood_Group = ? WHERE Email = ?")
        ) {
            pst.setString(1, FirstName);
            pst.setString(2, LastName);
            pst.setString(3, Age);
            pst.setString(4, Phone);
            pst.setString(5, Address);
            pst.setString(6, BloodGroup);
            pst.setString(7, Email);

            pst.executeUpdate();

            //update user
            user.setFirstName(FirstName);
            user.setLastName(LastName);
            user.setEmail(Email);
            user.setAge(Age);
            user.setPhone(Phone);
            user.setAddress(Address);
            user.setBloodGroup(BloodGroup);

            //System.out.println("Data Updated");



        } catch (SQLException e) {
            System.out.println(e);
        }

        gotoSuccessDialog("Data Updated");



    }
//success dialog screen
    void gotoSuccessDialog(String message) throws IOException {
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("successDialog.fxml"));
        Parent root = loader.load();
        DialogController controller = loader.getController();
        controller.successDialog(dialogStage, message, 2);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.show();

    }
    void gotoErrorDialog(String fxml, String message) throws IOException {
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("errorDialog.fxml"));
        Parent root = loader.load();
        DialogController controller = loader.getController();
        controller.errorDialog(dialogStage, message, 3);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.show();

    }

    void getData(){}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CB_bloodgrp.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        CB_bloodgrp.setConverter(new StringConverter<String>() {
         @Override
         public String toString(String s) {
             return (s == null) ? "Nothing selected" : s;
         }
         @Override
         public String fromString(String s) {
             return null;
         }
     });
        TF_firstname.setText(user.getFirstName());
        TF_lastname.setText(user.getLastName());
        TF_email.setText(user.getEmail());
        TF_age.setText(user.getAge());
        TF_mobile.setText(user.getPhone());
        TF_address.setText(user.getAddress());
        CB_bloodgrp.setValue(user.getBloodGroup());

        //Image set
        try {
            //check if image is not null then not display default image
            if (user.getImage() != null) {
                InputStream inputStream = user.getImage().getBinaryStream();
                Image image = new Image(new ByteArrayInputStream(inputStream.readAllBytes()));
                ImageCIrcle.setFill(new ImagePattern(image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
