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
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class searchingPlayerSalaryRangeController implements Initializable {

    private playerList database;
    private double lowerSalary,upperSalary;

    @FXML
    private Button backPrevious;

    @FXML
    private AnchorPane pane11;

    @FXML
    private AnchorPane pane21;

    @FXML
    private TextField rangeShow;

    @FXML
    private Button backButton;

    @FXML
    private TextField lowerRangeSalary;

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
    private TableView<Player> playerSalaryView;

    @FXML
    private Button sBySalaryRange;

    @FXML
    private AnchorPane searchPane;

    @FXML
    private AnchorPane tablePane;

    @FXML
    private TextField upperRangeSalary;

    public searchingPlayerSalaryRangeController(){
        database=playerList.getInstance();
    }

    @FXML
    public void backToMainMenu(ActionEvent event) {
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
        }else if(tablePane.isVisible()){
            lowerRangeSalary.clear();
            upperRangeSalary.clear();
            tablePane.setVisible(false);
            searchPane.setVisible(true);
            pane21.setVisible(false);
            pane11.setVisible(true);
        }

    }

    @FXML
    void backToPreviousPage(ActionEvent event) {
        lowerRangeSalary.clear();
        upperRangeSalary.clear();
        tablePane.setVisible(false);
        searchPane.setVisible(true);
        pane21.setVisible(false);
        pane11.setVisible(true);
        rangeShow.clear();
    }

    @FXML
    public void searchBySalaryRange(ActionEvent event) {
        BigDecimal lowerSalary1=new BigDecimal(lowerRangeSalary.getText().trim());
        BigDecimal upperSalary1=new BigDecimal(upperRangeSalary.getText().trim());
        lowerSalary=Double.valueOf(lowerRangeSalary.getText().trim());
        upperSalary=Double.valueOf(upperRangeSalary.getText().trim());
        ArrayList<Player> players=database.searchBySalaryRange(lowerSalary,upperSalary);
        ObservableList<Player> players1= FXCollections.observableArrayList();
        playerSalaryView.getItems().clear();
        players1.clear();
        players1.addAll(players);
        playerSalaryView.setItems(players1);
        tablePane.setVisible(true);
        searchPane.setVisible(false);
        pane21.setVisible(true);
        pane11.setVisible(false);
        //rangeShow.setText((lowerSalary.toPlainString+"-"+String.valueOf(upperSalary));
        rangeShow.setText(lowerSalary1.toPlainString() + "-" + upperSalary1.toPlainString());
        rangeShow.setEditable(false);
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
