package com.example.iplplayerdatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application  {
    private double x=0;
    private double y=0;
    @Override
    public void start(Stage stage) throws IOException {
       Parent fxmlLoader=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ClientPage.fxml")));
       //Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Scene scene=new Scene(fxmlLoader);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}