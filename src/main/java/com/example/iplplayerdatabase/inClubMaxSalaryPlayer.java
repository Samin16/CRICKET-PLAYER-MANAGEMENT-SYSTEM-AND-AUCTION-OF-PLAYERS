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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class inClubMaxSalaryPlayer implements Initializable {

    private String ClubName;
    private playerList database;

    @FXML
    private Button backButton;

    @FXML
    private TextField clubName;

    @FXML
    private TableView<Player> maxSalaryView;

    @FXML
    private TableColumn<String, Integer> playerAge;

    @FXML
    private TableColumn<String, String> playerClub;

    @FXML
    private TableColumn<String, String> playerCountry;

    @FXML
    private TableColumn<String, Double> playerHeight;

    @FXML
    private TableColumn<String, Integer> playerJurseyNumber;

    @FXML
    private TableColumn<String, String> playerName;

    @FXML
    private TableColumn<String, String> playerPosition;

    @FXML
    private TableColumn<String, Integer> playerSalary;

    @FXML
    private Button sWithMaxSalary;//String

    @FXML
    private AnchorPane searchPane;

    @FXML
    private AnchorPane tablePane;


    public inClubMaxSalaryPlayer(){
        database=playerList.getInstance();
    }


    @FXML
    void backToMainMenu(ActionEvent event) {
        Parent root=null;
        if(searchPane.isVisible()){
            try {
                root= FXMLLoader.load(getClass().getResource("clubSearch.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene=new Scene(root);

            Stage stage=(Stage)backButton.getScene().getWindow();

            //loginBtn.getScene().getWindow().hide();

            stage.setScene(scene);
        }else if(tablePane.isVisible()){
            clubName.clear();
            tablePane.setVisible(false);
            searchPane.setVisible(true);
        }

    }

    @FXML
    void searchWithMaximumSalary(ActionEvent event) {
        ClubName=clubName.getText().trim();
        ArrayList<Player> clubSalaryC=database.searchByClubSalary(ClubName);
//        for(Player players:clubSalaryC){
//            System.out.println(players);
//        }
        ObservableList<Player> players= FXCollections.observableArrayList();
        //System.out.println();
        maxSalaryView.getItems().clear();
        players.clear();
        players.addAll(clubSalaryC);
        maxSalaryView.setItems(players);
        searchPane.setVisible(false);
        tablePane.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        playerAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        playerHeight.setCellValueFactory(new PropertyValueFactory<>("height"));
        playerClub.setCellValueFactory(new PropertyValueFactory<>("club"));
        playerPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        playerJurseyNumber.setCellValueFactory(new PropertyValueFactory<>("jurseyNumber"));
        playerSalary.setCellValueFactory(new PropertyValueFactory<>("weeklySalary"));
    }
}
