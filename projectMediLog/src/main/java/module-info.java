module com.example.projectmedilog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires javafx.web;



    opens com.example.projectmedilog to javafx.fxml;
    exports com.example.projectmedilog;
}