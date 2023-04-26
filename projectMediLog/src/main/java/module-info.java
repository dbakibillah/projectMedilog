module com.example.projectmedilog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires javafx.media;


    opens com.example.projectmedilog to javafx.fxml;
    exports com.example.projectmedilog;
}