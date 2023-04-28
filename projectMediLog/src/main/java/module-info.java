module com.example.projectmedilog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
<<<<<<< HEAD
    requires java.desktop;
=======
    requires javafx.web;

>>>>>>> 2ccb2987851008e8d60db12cc4bfeb0fea5dff6f


    opens com.example.projectmedilog to javafx.fxml;
    exports com.example.projectmedilog;
}