package com.example.projectmedilog;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class ImageUpload {
    //private ImageView imageView;
    private byte[] imageBytes;
    private File selectedFile;

    void selectImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                imageBytes = fileInputStream.readAllBytes();
                Image image = new Image(new ByteArrayInputStream(imageBytes));
                //imageView.setImage(image);
//                SettingsController settingsController = new SettingsController();
//                if (settingsController.ImageCIrcle != null) {
//                    settingsController.ImageCIrcle.setFill(new ImagePattern(image));
//                }

                //uploadImage();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void uploadImage() throws SQLException, ClassNotFoundException {
        Connection connection = database.dbconnect();
        //Statement statement = connection.createStatement();

        String query = "INSERT INTO images (image) VALUES (?)"; //change the query

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setBytes(1, imageBytes);
        preparedStatement.executeUpdate();
    }

    void displayImage() throws SQLException, ClassNotFoundException {
        Connection connection = database.dbconnect();

        String query = "SELECT image FROM images WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, 1);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            byte[] imageBytes = resultSet.getBytes("image");
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            SettingsController settingsController = new SettingsController();
            settingsController.ImageCIrcle.setFill(new ImagePattern(image));
            //imageView.setImage(image);
            //userProfilePic.setFill(new ImagePattern(image));
        }
    }

    public Image getImage() {
        return new Image(new ByteArrayInputStream(imageBytes));
    }

    public File getSelectedFile() {
        return selectedFile;
    }
}
