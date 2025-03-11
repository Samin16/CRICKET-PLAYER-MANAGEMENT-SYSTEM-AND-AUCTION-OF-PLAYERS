module com.example.serverclientjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.serverclientjava to javafx.fxml;
    exports com.example.serverclientjava;
    exports com.example.iplplayerdatabase;
    opens com.example.iplplayerdatabase to javafx.fxml;
}