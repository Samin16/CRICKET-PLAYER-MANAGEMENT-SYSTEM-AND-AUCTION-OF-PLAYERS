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

public class searchingPlayerPositionController implements Initializable {
    private playerList database;
    private String positionP;

    @FXML
    private TextField Position;

    @FXML
    private AnchorPane pane11;

    @FXML
    private AnchorPane pane21;

    @FXML
    private TableView<Player> PositionPlayerView;

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
    private Button sByPlayerPosition;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane srarchPane;

    @FXML
    private AnchorPane tablePane;

    @FXML
    private TextField positionShow;

    public searchingPlayerPositionController(){
        database=playerList.getInstance();
    }

    @FXML
    void backToMainMenu(ActionEvent event) {
        Parent root=null;
        if(srarchPane.isVisible()){
            try {
                root= FXMLLoader.load(getClass().getResource("playerSearch.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene=new Scene(root);

            Stage stage=(Stage)backButton.getScene().getWindow();

            //loginBtn.getScene().getWindow().hide();

            stage.setScene(scene);
        }else if(tablePane.isVisible()){
            Position.clear();
            pane21.setVisible(false);
            tablePane.setVisible(false);
            pane11.setVisible(true);
            srarchPane.setVisible(true);
        }

    }

    @FXML
    void searchByPlayerPosition(ActionEvent event) {
        positionP = Position.getText().trim();
        ArrayList<Player> sPositionC = database.searchByPosition(positionP);
        for(Player players:sPositionC){
            System.out.println(players);
        }
        ObservableList<Player> players= FXCollections.observableArrayList();
        //System.out.println(sCountryC);
        PositionPlayerView.getItems().clear();
        players.clear();
        players.addAll(sPositionC);
        PositionPlayerView.setItems(players);
        positionShow.setText(positionP);
        positionShow.setEditable(false);
        pane11.setVisible(false);
        srarchPane.setVisible(false);
        tablePane.setVisible(true);
        pane21.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerClub.setCellValueFactory(new PropertyValueFactory<>("club"));
        playerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        playerAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        playerHeight.setCellValueFactory(new PropertyValueFactory<>("height"));
        playerPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        playerJurseyNumber.setCellValueFactory(new PropertyValueFactory<>("jurseyNumber"));
        playerSalary.setCellValueFactory(new PropertyValueFactory<>("weeklySalary"));

    }

}
