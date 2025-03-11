package com.example.iplplayerdatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class searchingClubController {

    @FXML
    private Button backMainMenu;

    @FXML
    private Button pMaxHeight;

    @FXML
    private Button plaayerMaxAge;

    @FXML
    private Button playerWithSalary;

    @FXML
    private Button totalClubSalary;

    @FXML
    void backToMainMenu(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.
                    load(getClass().getResource("ClientPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)backMainMenu.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void playerWithMaximumAge(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.
                    load(getClass().getResource("playerWithMaxAgeClub.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)playerWithSalary.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void playerWithMaximumHeight(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.
                    load(getClass().getResource("playerWithMaxHeightClub.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)playerWithSalary.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void playerWithMaximumSalary(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.
                    load(getClass().getResource("playerWithMaxSalariesClub.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)playerWithSalary.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void totalSalaryOfAClub(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.
                    load(getClass().getResource("clubTotalSalary.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)playerWithSalary.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

}
