
package com.example.iplplayerdatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class clubTotalSalaryController {

    private String club;
    private playerList database;

    @FXML
    private Button backButton;

    @FXML
    private TextField clubName;

    @FXML
    private TextField clubNameField;

    @FXML
    private TextField clubSalaryField;

    @FXML
    private AnchorPane inputPane;

    @FXML
    private Button showButton;

    @FXML
    private AnchorPane showPane;

    public clubTotalSalaryController(){
        database=playerList.getInstance();
    }

    @FXML
    void backToMainMenu(ActionEvent event) {
        Parent root=null;
        if(inputPane.isVisible()){
            try {
                root= FXMLLoader.load(getClass().getResource("clubSearch.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene=new Scene(root);

            Stage stage=(Stage)backButton.getScene().getWindow();

            //loginBtn.getScene().getWindow().hide();

            stage.setScene(scene);
        }else if(showPane.isVisible()){
            clubName.clear();
            showPane.setVisible(false);
            inputPane.setVisible(true);
        }

    }

    @FXML
    void showTotalSalaryOfClub(ActionEvent event) {
        club=clubName.getText().trim();
        int totalSalary= database.totalClubSalary(club);
        inputPane.setVisible(false);
        showPane.setVisible(true);
        clubNameField.setText(club);
        clubSalaryField.setText(String.valueOf(totalSalary));
        clubSalaryField.setEditable(false);
    }
}