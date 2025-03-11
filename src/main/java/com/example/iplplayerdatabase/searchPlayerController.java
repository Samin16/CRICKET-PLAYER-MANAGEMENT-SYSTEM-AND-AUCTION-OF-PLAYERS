package com.example.iplplayerdatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class searchPlayerController {

    @FXML
    private Button BToMainMenu;

    @FXML
    private Button cWisePlayerCount;

    @FXML
    private Button sByCountryClub;

    @FXML
    private Button sByPosition;

    @FXML
    private Button sBySalaryRange;

    @FXML
    private Button sPlayByName;

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

        Stage stage=(Stage)sPlayByName.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void countryWisePlayerCount(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.
                    load(getClass().getResource("counWisePlayerCount.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)sPlayByName.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void searchByCountryAndClub(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.
                    load(getClass().getResource("searchCountryAndClub.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)sPlayByName.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void searchBySalaryRange(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.
                    load(getClass().getResource("playerSearchSalaryRange.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)sPlayByName.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void searchPlayerByName(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.
                    load(getClass().getResource("searchName.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)sPlayByName.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void searchPlayerByPosition(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.
                    load(getClass().getResource("searchPlayerPosition.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)sPlayByName.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

}
