package com.example.iplplayerdatabase;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class searchByPlayerName {
    //Player Database
    private playerList database;

    // Stores name of the player
    private String name;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button searchPlayers;

    @FXML
    private Button Back;

    @FXML
    private Button sAgain;



    // Player details
    @FXML
    private Text countryName;

    @FXML
    private Text nameText;


    @FXML
    private Text playerAge;

    @FXML
    private Text playerClub;

    @FXML
    private Text playerHeight;

    @FXML
    private Text playerJursey;

    @FXML
    private Text playerPosition;

    @FXML
    private Text playerSalary;

    @FXML
    private Text yesNO;


    // Actions
    public searchByPlayerName(){
        database=playerList.getInstance();
    }

    @FXML
    void handleSubmittedData(ActionEvent event) {


    }

    @FXML
    void searchPlayerByNameIn(ActionEvent event) {
        name=nameTextField.getText().trim();
        //System.out.println("Entered name : "+name);
        Player p=database.searchByName(name);
        if(p==null){
            yesNO.setText("NO");
            nameText.setText("N/A");
            countryName.setText("N/A");
            playerAge.setText("N/A");
            playerHeight.setText("N/A");
            playerClub.setText("N/A");
            playerPosition.setText("N/A");
            playerJursey.setText("N/A");
            playerSalary.setText("N/A");
        }else {
            yesNO.setText("YES");
            //System.out.println(p);
            nameText.setText(p.getName());
            countryName.setText(p.getCountry());
            playerAge.setText(String.valueOf(p.getAge()));
            playerHeight.setText(String.valueOf(p.getHeight()));
            playerClub.setText(p.getClub());
            playerPosition.setText(p.getPosition());
            playerJursey.setText(String.valueOf(p.getJurseyNumber()));
            playerSalary.setText(String.valueOf(p.getWeeklySalary()));
        }
    }

    @FXML
    void backToPlayerSearch(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource("playerSearch.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)searchPlayers.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void searchPlayerAgain(ActionEvent event) {
        nameTextField.clear();
        //yesNO.setText("YES");
        //System.out.println(p);
        yesNO.setText("");
        //System.out.println(p);
        nameText.setText("");
        countryName.setText("");
        playerAge.setText("");
        playerHeight.setText("");
        playerClub.setText("");
        playerPosition.setText("");
        playerJursey.setText("");
        playerSalary.setText("");
    }
}
