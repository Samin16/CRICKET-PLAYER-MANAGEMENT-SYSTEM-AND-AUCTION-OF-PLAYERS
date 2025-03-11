module com.example.iplplayerdatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.iplplayerdatabase to javafx.fxml;
    exports com.example.iplplayerdatabase;
}