package com.example.iplplayerdatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;



    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;


    public void close() {
        System.exit(0);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    void LogInClientPage(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.
                    load(getClass().getResource("ClientPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

       Stage stage=(Stage)loginBtn.getScene().getWindow();

       //loginBtn.getScene().getWindow().hide();

       stage.setScene(scene);

    }

}