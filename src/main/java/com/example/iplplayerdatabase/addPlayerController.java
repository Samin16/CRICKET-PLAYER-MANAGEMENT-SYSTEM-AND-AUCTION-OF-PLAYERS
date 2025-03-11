package com.example.iplplayerdatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class addPlayerController implements Initializable {

    private playerList database;
    private String name;
    private String Country,Club,Position;
    private int Age,JurseyNumber,Salary;
    private Double Height;

    @FXML
    private Button backButton;
    @FXML
    private TextField Name;

    @FXML
    private Button backPrevious;

    @FXML
    private Button chPlayer;

    @FXML
    private Button nextPage;

    @FXML
    private AnchorPane pane11;

    @FXML
    private AnchorPane pane12;

    @FXML
    private AnchorPane pane22;

    @FXML
    private TextField playerAge;

    @FXML
    private TextField playerClub;

    @FXML
    private TextField playerCountry;

    @FXML
    private TextField playerHeight;

    @FXML
    private TextField playerJurseyNumber;

    @FXML
    private TextField playerName;

    @FXML
    private TextField playerPosition;

    @FXML
    private TextField playerSalary;

    @FXML
    private TextField returnMessage;

    @FXML
    private Button saveData;

    public addPlayerController(){
        database=playerList.getInstance();
    }

    @FXML
    void backToMainMenu(ActionEvent event) {
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource("ClientPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);

        Stage stage=(Stage)Name.getScene().getWindow();

        //loginBtn.getScene().getWindow().hide();

        stage.setScene(scene);
    }

    @FXML
    void backToPreviousPage(ActionEvent event) {
          pane22.setVisible(false);
          pane11.setVisible(true);
          pane12.setVisible(true);
          nextPage.setDisable(true);
          playerName.clear();
          returnMessage.clear();
          playerAge.clear();
          playerCountry.clear();
          playerHeight.clear();
          playerClub.clear();
          playerPosition.clear();
          playerJurseyNumber.clear();
          playerSalary.clear();
    }

    @FXML
    void checkPlayer(ActionEvent event) {
          name=playerName.getText();
          if(database.searchByName(name)==null){
              returnMessage.setText("Please click NEXT STEP");
              nextPage.setDisable(false);
          }else{
              returnMessage.setText("Player already exists");
              nextPage.setDisable(true);
          }
    }

    @FXML
    void goToNextPage(ActionEvent event) {
        //Name.requestFocus();
        pane22.setVisible(true);
        pane11.setVisible(false);
        pane12.setVisible(false);
        Name.setText(name);
        Name.setEditable(false);
        playerCountry.clear();
        //saveData.setDisable(true);
        backPrevious.requestFocus();
    }

    @FXML
    void saveToFile(ActionEvent event) {
        Stage stage=(Stage)pane22.getScene().getWindow();
        Alert.AlertType type=Alert.AlertType.CONFIRMATION;
        Alert alert=new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("Do you want to save?");
        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK){
            System.out.println("OK button");
            Age=Integer.valueOf(playerAge.getText());
            Country=playerCountry.getText();
            Height=Double.valueOf(playerHeight.getText());
            Club=playerClub.getText();
            Position=playerPosition.getText();
            JurseyNumber=Integer.valueOf(playerJurseyNumber.getText());
            Salary=Integer.valueOf(playerSalary.getText());
            Player p1=new Player(name, Country, Age, Height, Club, Position, JurseyNumber, Salary);
            database.addPlayer(p1);

            try{
                database.savePlayerListToDatabase();
                Name.setText(name + " saved Successfully");
            }catch (Exception e){
                Name.setText("Something went Wrong");
            }finally {
                saveData.setDisable(true);
            }
        }else if(result.get()==ButtonType.CANCEL){
            System.out.println("Cancel Button");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backPrevious.requestFocus();
    }
}
