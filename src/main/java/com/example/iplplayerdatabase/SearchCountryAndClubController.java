package com.example.iplplayerdatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchCountryAndClubController implements Initializable {
    private String cClub,cCountry;

    private playerList database;


        @FXML
        private TextField Club;

        @FXML
        private Button backPrevious;

        @FXML
        private TableColumn<Player, String> ClubName;

        @FXML
        private TextField Country;

        @FXML
        private TableColumn<Player,String> PlayerName;

        @FXML
        private TableColumn<Player,Integer> PlayerSalary;

        @FXML
        private TableView<Player> TableViewIPL;

        @FXML
        private Button backButton;

        @FXML
        private TableColumn<Player,String> countryName;

        @FXML
        private TableColumn<Player,Integer> playerAge;

        @FXML
        private TableColumn<Player,Double> playerHeight;

        @FXML
        private TableColumn<Player,Integer> playerJurseyNumber;

        @FXML
        private TableColumn<Player,String> playerPosition;

        @FXML
        private Button sByCountryAndClub;

        @FXML
        private AnchorPane tableShowPane;

       @FXML
       private AnchorPane searchPane;

       @FXML
       private AnchorPane pane11;

       @FXML
       private AnchorPane pane21;

    @FXML
    private TextField showClub;

    @FXML
    private TextField showCountry;




    public SearchCountryAndClubController(){
        database=playerList.getInstance();
    }

    @FXML
    void backToMainMenu(ActionEvent event) {
        Parent root=null;
        if(searchPane.isVisible()){
        try {
            root= FXMLLoader.load(getClass().getResource("playerSearch.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
            Scene scene=new Scene(root);

            Stage stage=(Stage)backButton.getScene().getWindow();

            //loginBtn.getScene().getWindow().hide();

            stage.setScene(scene);
        }else if(tableShowPane.isVisible()){
            Country.clear();
            Club.clear();
            pane21.setVisible(false);
            tableShowPane.setVisible(false);
            pane11.setVisible(true);
            searchPane.setVisible(true);
        }

    }

    @FXML
    void searchByCountryAndClub(ActionEvent event) {
        cCountry=Country.getText().trim();
        cClub=Club.getText().trim();
        ArrayList<Player> sCountryC=database.searchByClubAndCountry(cCountry,cClub);
        ObservableList<Player> players= FXCollections.observableArrayList();
        //System.out.println("In Expected Function"+sCountryC);
        TableViewIPL.getItems().clear();
        players.clear();
        players.addAll(sCountryC);
        TableViewIPL.setItems(players);
        pane11.setVisible(false);
        searchPane.setVisible(false);
        pane21.setVisible(true);
        tableShowPane.setVisible(true);
        showCountry.setText(cCountry);
        showClub.setText(cClub);
    }

    @FXML
    void backToPreviousPage(ActionEvent event) {
        Country.clear();
        Club.clear();
        pane21.setVisible(false);
        tableShowPane.setVisible(false);
        pane11.setVisible(true);
        searchPane.setVisible(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClubName.setCellValueFactory(new PropertyValueFactory<>("club"));
        PlayerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryName.setCellValueFactory(new PropertyValueFactory<>("country"));
        playerAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        playerHeight.setCellValueFactory(new PropertyValueFactory<>("height"));
        playerPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        playerJurseyNumber.setCellValueFactory(new PropertyValueFactory<>("jurseyNumber"));
        PlayerSalary.setCellValueFactory(new PropertyValueFactory<>("weeklySalary"));

    }
}