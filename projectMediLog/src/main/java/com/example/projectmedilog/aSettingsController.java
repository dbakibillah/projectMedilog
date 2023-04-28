package com.example.projectmedilog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class aSettingsController implements Initializable {

    @FXML
    private Button BTN_ChooseFile;

    @FXML
    private Button BTN_Save;

    @FXML
    private Button BTN_SaveChange;

    @FXML
    private Button BTN_Upload;
    @FXML
    public Circle ImageCIrcle;
    @FXML
    PasswordField TF_currentpass;

    @FXML
    private TextField TF_UserName;

    @FXML
    private PasswordField TF_newpass;
    @FXML
    private ImageView imageView;
    ImageUpload imageUpload = new ImageUpload();
    String aName = aHomeController.aUserName;

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
                PreparedStatement pst = connection.prepareStatement("UPDATE admins SET Image = ? WHERE UserName = ?")
        ) {
            pst.setBinaryStream(1, fileInputStream, fileInputStream.available());
            pst.setString(2, TF_UserName.getText());

            pst.execute();

            System.out.println("Image Updated");

            // get image from database
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM admins WHERE UserName = '" + TF_UserName.getText() + "'");
            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("Image");
                admin.setImage(blob);
                InputStream inputStream = admin.getImage().getBinaryStream();
                Image image = new Image(new ByteArrayInputStream(inputStream.readAllBytes()));

                // goto pHome screen
                Stage stage = (Stage) BTN_Upload.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("aHome.fxml"));
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
    void onClickBTN_Save(ActionEvent event) throws SQLException, ClassNotFoundException, IOException, InterruptedException {

        //Showing Alert if any field is empty
        if (TF_currentpass.getText().isEmpty() || TF_newpass.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }

        String UserName = TF_UserName.getText();
        String CurrentPass = TF_currentpass.getText();
        String NewPass = TF_newpass.getText();

        //check if current password is correct
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM admins WHERE UserName = '" + UserName + "' AND Pass = '" + CurrentPass + "'");
        if (resultSet.next()) {
            //update password
            try (
                    PreparedStatement pst = connection.prepareStatement("UPDATE admins SET Pass = ? WHERE UserName = ?")
            ) {
                pst.setString(1, NewPass);
                pst.setString(2, UserName);

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
    void onClickBTN_SaveChange(ActionEvent event) throws SQLException, ClassNotFoundException, IOException ,InterruptedException {

        String UserName = TF_UserName.getText();



        //update data using database
        Connection connection = database.dbconnect();
        Statement statement = connection.createStatement();


        try (
                PreparedStatement pst = connection.prepareStatement("UPDATE admins SET UserName = ? WHERE UserName = ?")
        ) {

            pst.setString(1, UserName);
            pst.setString(2, admin.getUserName());
            pst.executeUpdate();

            //update user

            admin.setUserName(UserName);

            System.out.println("Data Updated"+UserName);


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TF_UserName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #008000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
            } else {
                TF_UserName.setBackground(Background.fill(Color.TRANSPARENT));
                TF_UserName.setStyle("-fx-border-color: #0080ff ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100; -fx-prompt-text-fill: #808080;");

            }
        });
//

        TF_UserName.setText(aName);
        System.out.println(aName);



        //Image set
        try {
            //check if image is not null then not display default image
            if (admin.getImage() != null) {
                InputStream inputStream =admin.getImage().getBinaryStream();
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
