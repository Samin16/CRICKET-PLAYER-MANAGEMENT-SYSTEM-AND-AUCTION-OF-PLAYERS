package com.example.iplplayerdatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class cWisePlayerInIPL implements Initializable {

    private playerList database;

    @FXML
    private Button backButton;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private Button chartButton;

    @FXML
    private TableColumn<countryPlayerData, String> countryName;

    @FXML
    private TableView<countryPlayerData> countryTable;

    @FXML
    private TableColumn<countryPlayerData,Integer> playerCount;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

//    public cWisePlayerInIPL(){
//        database=new playerList();
//        Map<String,Integer> counCount=database.countryWisePlayerCount();
//        ObservableList<countryPlayerData> players = FXCollections.observableArrayList();
//        for (Map.Entry<String, Integer> entry : counCount.entrySet()) {
//            players.add(new countryPlayerData(entry.getKey(), entry.getValue()));
//        }
//
//        countryTable.setItems(players);
//    }

    @FXML
    void backToMainMenu(ActionEvent event) {
        if(pane1.isVisible()) {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("playerSearch.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);

            Stage stage = (Stage) backButton.getScene().getWindow();

            //loginBtn.getScene().getWindow().hide();

            stage.setScene(scene);
        }else if(pane2.isVisible()){
            pane1.setVisible(true);
            pane2.setVisible(false);
            chartButton.setDisable(false);
        }
    }


    @FXML
    void showChart(ActionEvent event) {
        pane1.setVisible(false);
        pane2.setVisible(true);
        chartButton.setDisable(true);
        barChart.getData().clear();

        NumberAxis yAxis = (NumberAxis) barChart.getYAxis();
        yAxis.setTickUnit(5);
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(50);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Players per Country");

        Map<String, Integer> counCount = database.countryWisePlayerCount();

        for (Map.Entry<String, Integer> entry : counCount.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().add(series);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryName.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        playerCount.setCellValueFactory(new PropertyValueFactory<>("playerCount"));
        database = playerList.getInstance(); // Assuming playerList class is implemented
        Map<String, Integer> counCount = database.countryWisePlayerCount();

        ObservableList<countryPlayerData> players = FXCollections.observableArrayList();

        countryTable.getItems().clear();
        for (Map.Entry<String, Integer> entry : counCount.entrySet()) {
            players.add(new countryPlayerData(entry.getKey(), entry.getValue()));
        }

        countryTable.setItems(players);

        showChart(null);
    }

}
