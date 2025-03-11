package com.example.serverclientjava;
import com.example.iplplayerdatabase.Player;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EnterPage1 implements Initializable {
    private ObjectInputStream input;
    private ObjectOutputStream output;

    @FXML
    private Button BUY, SELL, BuyPlayer, LogOut, SellPlayer, ShowPlayer;

    @FXML
    private Text clubName;

    @FXML
    private TextField PlayerName, showMessage;

    @FXML
    private AnchorPane messagePane, pane11, pane12, pane22;

    @FXML
    private TableView<Player> playerView, playerView1;

    @FXML
    private TableColumn<Player, String> playerName, playerCountry, playerPosition, playerClub, playerName1, playerCountry1, playerPosition1, playerClub1;

    @FXML
    private TableColumn<Player, Integer> playerAge, playerJurseyNumber, playerSalary, playerAge1, playerJurseyNumber1, playerSalary1;

    @FXML
    private TableColumn<Player, Double> playerHeight, playerHeight1;


    private Player selectedPlayerToBuy = null;
    private Player selectedPlayerToSell = null;

    @FXML
    void buyPlayer(ActionEvent event) {
        try {
            output.writeObject("buyPlayer");
        } catch (IOException e) {
            e.printStackTrace();
        }
        switchToBuyPane();
    }

    @FXML
    void buyPlayerConfirm(ActionEvent event) {
        if (selectedPlayerToBuy != null) {
            try {
                output.writeObject("selectForBuy");
                output.writeObject(selectedPlayerToBuy);
               // playerView.getItems().add(selectedPlayerToBuy);
            } catch (IOException e) {
                e.printStackTrace();
            }
            selectedPlayerToBuy = null;
            PlayerName.setText("");
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        if (LogOut.getText().trim().equals("LogOut")) {
            navigateToLogin();
        } else if (LogOut.getText().trim().equals("BACK")) {
            switchToMainPane();
        }
    }

    @FXML
    void sellPlayer(ActionEvent event) {
        switchToSellPane();
    }

    @FXML
    void sellPlayerConfirm(ActionEvent event) {
        if (selectedPlayerToSell != null) {
            try {
                output.writeObject("selectForSell");
                output.writeObject(selectedPlayerToSell);
            } catch (IOException e) {
                e.printStackTrace();
            }
            selectedPlayerToSell = null;
            PlayerName.setText("");
        }
    }

    @FXML
    void showPlayer(ActionEvent event) {
        switchToShowPane();
    }

    public void setClubName(String name){
        clubName.setText(name);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableColumns();
        setupTableListeners();
        new ReadPlayer();
    }

    private void setupTableColumns() {
        playerClub.setCellValueFactory(new PropertyValueFactory<>("club"));
        playerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        playerAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        playerHeight.setCellValueFactory(new PropertyValueFactory<>("height"));
        playerPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        playerJurseyNumber.setCellValueFactory(new PropertyValueFactory<>("jurseyNumber"));
        playerSalary.setCellValueFactory(new PropertyValueFactory<>("weeklySalary"));

        playerClub1.setCellValueFactory(new PropertyValueFactory<>("club"));
        playerName1.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerCountry1.setCellValueFactory(new PropertyValueFactory<>("country"));
        playerAge1.setCellValueFactory(new PropertyValueFactory<>("age"));
        playerHeight1.setCellValueFactory(new PropertyValueFactory<>("height"));
        playerPosition1.setCellValueFactory(new PropertyValueFactory<>("position"));
        playerJurseyNumber1.setCellValueFactory(new PropertyValueFactory<>("jurseyNumber"));
        playerSalary1.setCellValueFactory(new PropertyValueFactory<>("weeklySalary"));
    }

    private void setupTableListeners() {
        playerView1.getSelectionModel().selectedItemProperty().addListener((observableValue, player, t1) -> {
            if (t1 != null) {
                PlayerName.setText(t1.getName());
                selectedPlayerToBuy = t1;
            }
        });

        playerView.getSelectionModel().selectedItemProperty().addListener((observableValue, player, t1) -> {
            if (t1 != null) {
                PlayerName.setText(t1.getName());
                selectedPlayerToSell = t1;
            }
        });
    }

    private void switchToBuyPane() {
        pane12.setVisible(false);
        pane22.setVisible(true);
        playerView.setVisible(false);
        playerView1.setVisible(true);
        LogOut.setText("BACK");
        BuyPlayer.setDisable(true);
        SellPlayer.setDisable(false);
        ShowPlayer.setDisable(false);
        BUY.setVisible(true);
        SELL.setVisible(false);
    }

    private void switchToSellPane() {
        pane12.setVisible(false);
        pane22.setVisible(true);
        playerView1.setVisible(false);
        playerView.setVisible(true);
        LogOut.setText("BACK");
        SellPlayer.setDisable(true);
        BuyPlayer.setDisable(false);
        ShowPlayer.setDisable(false);
        SELL.setVisible(true);
        BUY.setVisible(false);
    }

    private void switchToShowPane() {
        pane12.setVisible(false);
        pane22.setVisible(true);
        playerView1.setVisible(false);
        playerView.setVisible(true);
        LogOut.setText("BACK");
        ShowPlayer.setDisable(true);
        BuyPlayer.setDisable(false);
        SellPlayer.setDisable(false);
        BUY.setVisible(false);
        SELL.setVisible(false);
    }

    private void switchToMainPane() {
        pane22.setVisible(false);
        pane12.setVisible(true);
        LogOut.setText("LogOut");
        ShowPlayer.setDisable(false);
        BuyPlayer.setDisable(false);
    }

    private void navigateToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) LogOut.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ReadPlayer implements Runnable {
        public ReadPlayer() {
            ServerConnection s = ServerConnection.getInstance();
            input = s.getInput();
            output = s.getOutput();
            try {
                output.writeObject("showPlayer");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            new Thread(this).start();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Object obj=input.readObject();
                    if(obj instanceof  String){

                    String msg = (String) obj;
                    handleServerMessage(msg);

                    }
                    else
                    {
                        Player x=(Player)obj;
                        System.out.println(x);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleServerMessage(String msg) throws IOException, ClassNotFoundException {
            switch (msg) {
                case "playerList":
                    updatePlayerList();
                    break;
                case "buyablePlayer":
                    updateBuyablePlayerList();
                    break;
                case "clubChanged":
                    updateClubChange();
                    break;
                case "removePlayerFromSellList":
                    removePlayerFromSellList();
                    break;
                case "addPlayerToSellList":
                    addPlayerToSellList();
                    break;
                case "removeFromClubList":

                    Player x=(Player)input.readObject();

                    Platform.runLater(()-> {
                            playerView.getItems().remove(x);
                    });
                    break;
            }
        }



        private void updatePlayerList() throws IOException, ClassNotFoundException {
            Player[] arr = (Player[]) input.readObject();
            Platform.runLater(() -> {
                ObservableList<Player> players = FXCollections.observableArrayList(arr);
                playerView.setItems(players);
            });
        }

        private void updateBuyablePlayerList() throws IOException, ClassNotFoundException {
            String club = (String) input.readObject();
            Player[] arr = (Player[]) input.readObject();
            Platform.runLater(() -> {
                ObservableList<Player> players1 = FXCollections.observableArrayList();
                for (Player p : arr) {
                    if (!p.getClub().equals(club)) {
                        players1.add(p);
                    }
                }
                playerView1.setItems(players1);
            });
        }

        private void updateClubChange() throws IOException, ClassNotFoundException {
            Player updatedPlayer = (Player) input.readObject();

            Platform.runLater(() -> {
                playerView1.getItems().remove(updatedPlayer);
                playerView.getItems().add(updatedPlayer);
                playerView.refresh();
                System.out.println(updatedPlayer+"   "+"In Club changed");
                playerView1.refresh();
            });
        }

        private void removePlayerFromSellList() throws IOException, ClassNotFoundException {
            Player p = (Player) input.readObject();
            playerView1.getItems().remove(p);

        }

        private void addPlayerToSellList() throws IOException, ClassNotFoundException {
            Player sellRequestPlayer = (Player) input.readObject();
            Platform.runLater(() -> {
                ObservableList<Player> buyablePlayers = playerView1.getItems();
                ObservableList<Player> clubPlayers = playerView.getItems();
                if (!buyablePlayers.contains(sellRequestPlayer)&&!clubPlayers.contains(sellRequestPlayer)) {
                    buyablePlayers.add(sellRequestPlayer);
                    playerView1.refresh();
                }
            });
        }
    }
}
