module com.example.projectmedilog {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectmedilog to javafx.fxml;
    exports com.example.projectmedilog;
}