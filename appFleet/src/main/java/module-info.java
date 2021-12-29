module com.example.appfleet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.appfleet to javafx.fxml;
    exports com.example.appfleet;
    exports com.example.appfleet.controller;
    opens com.example.appfleet.controller to javafx.fxml;
}