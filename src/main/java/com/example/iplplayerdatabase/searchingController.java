package com.example.iplplayerdatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class searchingController{
    //Searching
    @FXML
    private Button SPlayer;

    @FXML
    private Button aPlayer;

    @FXML
    private Button ext;

    @FXML
    private Button sClub;

    @FXML
    void addPlayer(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource("addPlayerPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)SPlayer.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void close(ActionEvent event) {
         System.exit(0);
    }

    @FXML
    void searchClub(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource("ClubSearch.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)SPlayer.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void searchPlayer(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource("playerSearch.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)SPlayer.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

//    void searchPlayer(ActionEvent event) {
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("playerSearch.fxml"));
//
//            if (root == null) {
//                System.out.println("Failed to load playerSearch.fxml");
//                return;
//            }
//
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) SPlayer.getScene().getWindow();
//            stage.setScene(scene);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}