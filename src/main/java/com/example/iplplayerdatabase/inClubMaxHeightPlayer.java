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

public class inClubMaxHeightPlayer implements Initializable {

    private String club;
    private playerList database;

    @FXML
    private Button backButton;

    @FXML
    private TextField clubName;

    @FXML
    private TableView<Player> heightView;

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
    private Button sMaxHeightInClub;

    @FXML
    private AnchorPane scrollPane;

    @FXML
    private AnchorPane searchPane;

    public inClubMaxHeightPlayer(){
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
        }else if(scrollPane.isVisible()){
            clubName.clear();
            scrollPane.setVisible(false);
            searchPane.setVisible(true);
        }

    }

    @FXML
    void playersWithMaxHeight(ActionEvent event) {
        club=clubName.getText();
        ArrayList<Player> maxHeightPlayers=database.searchByClubPlayerHeight(club);
        ObservableList<Player> players= FXCollections.observableArrayList();
        heightView.getItems().clear();
        players.clear();
        players.addAll(maxHeightPlayers);
        heightView.setItems(players);
        searchPane.setVisible(false);
        scrollPane.setVisible(true);
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
